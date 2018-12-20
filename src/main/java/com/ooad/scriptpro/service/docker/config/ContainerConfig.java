package com.ooad.scriptpro.service.docker.config;

public class ContainerConfig {
    public String Image;
    public String[] Cmd;
    public HostConfigCls HostConfig;


    class HostConfigCls {
        public String[] Binds;

        public HostConfigCls(String[] binds) {
            this.Binds = binds;
        }
    }

    public ContainerConfig(String image, String[] cmd, String[] binds) {
        this.Image = image;
        this.Cmd = cmd;
        this.HostConfig = new HostConfigCls(binds);
    }
}
