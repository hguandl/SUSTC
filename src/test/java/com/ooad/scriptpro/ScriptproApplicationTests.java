package com.ooad.scriptpro;

import com.ooad.scriptpro.model.Script;
import com.ooad.scriptpro.service.ScriptService;
import com.ooad.scriptpro.service.docker.DockerLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptproApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void dockerLogParse() {
		// "hello\nworld\n"
		byte[] raw = {1, 0, 0, 0, 0, 0, 0, 12, 104, 101, 108, 108, 111, 10, 119, 111, 114, 108, 100, 10,
			2, 0, 0, 0, 0, 0, 0, 2, 104, 10};
		DockerLog log = new DockerLog(raw);
		System.out.println("output: " + log.getStdOut());
		System.out.println("error: " + log.getStdErr());
	}

	@Autowired
	ScriptService scriptService;
	@Test
	public void testRetrieveScript() throws Exception {
		assert scriptService.getScriptContentById(11) != null;
        System.out.println(scriptService.getScriptContentById(11));
	}
}

