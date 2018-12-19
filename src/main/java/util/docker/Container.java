package util.docker;

import com.google.gson.Gson;
import util.docker.config.ContainerConfig;
import util.docker.config.ContainerInfo;
import util.docker.config.ScriptLang;
import util.docker.config.WaitInfo;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.UUID;

import util.Http;
import util.IOFiles;

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

    public String getResult() throws IOException {
        return IOFiles.read("/tmp/" + this.uuid + "/output.txt");
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
            Container container = new Container(ScriptLang.PYTHON, 10086, null);
            container.execCreateContainer();
            int ret = container.execRunContainer();
            if (ret == 0) {
                System.out.println(container.getResult());
            } else {
                System.out.println("err");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
