package com.ooad.scriptpro.service.docker;

import com.google.gson.Gson;
import com.ooad.scriptpro.service.docker.config.ContainerInfo;
import com.ooad.scriptpro.service.docker.config.ContainerConfig;
import com.ooad.scriptpro.service.docker.config.ScriptLang;
import com.ooad.scriptpro.service.docker.config.WaitInfo;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.UUID;

import com.ooad.scriptpro.util.Http;
import com.ooad.scriptpro.util.IOFiles;
import org.springframework.stereotype.Service;


public class Container {
    private static Gson gson = new Gson();
    private static Http http = new Http();

    private static final String apiPrefix = <API>;

    private ScriptLang type;
    private String uuid;
    private String id;

    private ContainerConfig config = null;

    public Container(ScriptLang type, int scriptId, String[] args) throws IOException {
        this.type = type;
        this.init(scriptId, args);
    }

    private void init(int scriptId, String[] args) throws IOException {
        uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

        File directory = new File("/tmp/" + this.uuid + "/");
        if (!directory.exists()){
            directory.mkdirs();
        }

        IOFiles.place(scriptId, type.getFilePath().replace("/data", "/tmp/" + this.uuid));
        String[] cmd = type.getCmd(args);
        String[] bind = {"/tmp/" + this.uuid + ":/data:rw"};
        config = new ContainerConfig("sustc-" + type.getName(), cmd, bind);
    }

    private String createUrl() {
        return apiPrefix + "create";
    }

    private String startUrl(String id) {
        return apiPrefix + id + "/start";
    }

    private String waitUrl(String id) {
        return apiPrefix + id + "/wait";
    }

    private String logUrl(String id) { return apiPrefix + id + "/logs?stdout=1"; }

    public String execCreateContainer() throws IOException {
        String postBody = gson.toJson(config);
        String body = http.post(createUrl(), postBody);
        ContainerInfo container = gson.fromJson(body, ContainerInfo.class);
        this.id = container.Id;
        return container.Id;
    }

    public int execRunContainer() throws SocketTimeoutException {
        http.post(startUrl(this.id), null);
        String wait = http.post(waitUrl(this.id), null);
        WaitInfo waitInfo = gson.fromJson(wait, WaitInfo.class);
        return waitInfo.StatusCode;
    }

    public String getResult(String filename) throws IOException {
        return IOFiles.read("/tmp/" + this.uuid + "/" + filename);
    }

    public String getOutput() throws IOException {
        return (new DockerLog(http.get(logUrl(this.id)))).toString();
    }

    private static void prune() {
        System.out.println("cleanup");
        try {
            http.post(apiPrefix + "prune", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Container container = new Container(ScriptLang.JAVASCRIPT, 10086, args);
            container.execCreateContainer();
            int ret = container.execRunContainer();
            if (ret == 0) {
//				System.out.println(container.getResult("output.txt"));
                System.out.println(container.getOutput());
            } else {
                System.out.println("err");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
