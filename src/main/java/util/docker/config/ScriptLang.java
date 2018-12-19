package util.docker.config;

public enum ScriptLang {
    PYTHON("python", "python3", "/data/run.py"),
    JAVASCRIPT("javascript", "node", "/data/run.js");

    private String name;
    private String bin;
    private String file;

    ScriptLang(String name, String bin, String file) {
        this.name = name;
        this.bin = bin;
        this.file = file;
    }

    public String[] getCmd(String[] args) {
        if (args == null) {
            String[] cmd = {bin, file};
            return cmd;
        }
        String[] cmd = new String[2 + args.length];
        cmd[0] = bin;
        cmd[1] = file;
        System.arraycopy(args, 0, cmd, 2, args.length);
        return cmd;
    }

    public String getName() {
        return this.name;
    }

    public String getFilePath() {
        return this.file;
    }
}
