package util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOFiles {

    private static final String testScript =
        "with open('/data/output.txt', 'w') as f:\n" +
        "    f.write('hello')\n";

//    private static final String testScript =
//            "import time\n" + "time.sleep(30)\n";

    private static final String jsScript =
            "const fs = require('fs');\n" +
            "fs.writeFile(\"/data/output.txt\", \"Hey there!\", function(err) {\n" +
            "if(err) {\n" +
            "return console.log(err);\n" +
            "}" +
            "console.log(\"The file was saved!\");" +
            "});";

    public static void place(int scriptID, String filePath) throws IOException {
//        String script = getScript(scriptID);  // TODO
        String script = jsScript;
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8));
        writer.write(script);
        writer.close();
    }

    public static String read(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath)));
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }
}
