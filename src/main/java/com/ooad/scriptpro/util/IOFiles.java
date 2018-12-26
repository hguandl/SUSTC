package com.ooad.scriptpro.util;

import com.ooad.scriptpro.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Service
public class IOFiles {
    private static ScriptService scriptService;

    @Autowired
    public IOFiles(ScriptService scriptService) {
        IOFiles.scriptService = scriptService;
    }

    public static void place(int scriptID, String filePath) throws IOException, SQLException {
        String script = scriptService.getScriptContentById(scriptID);
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
