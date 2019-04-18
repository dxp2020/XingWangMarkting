package io.github.biezhi.ome;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;


/**
 * 发送邮件测试
 */
public class OhMyEmailTest {

    // 该邮箱修改为你需要测试的邮箱地址
    private static final String TO_EMAIL = "804407779@qq.com";
    private static final String FROM_EMAIL = "ywmafb@yeah.net";
    private static final String FROM_NAME = "星王传奇";
    
    @Before
    public void before() {
        OhMyEmail.config(OhMyEmail.SMTP_YEAT(false), FROM_EMAIL, "uwpebcoi");
    }
    
    @Test
    public void testPebble() throws IOException, PebbleException, SendMailException {
        for (int i = 0; i < 10; i++) {
			PebbleEngine engine = new PebbleEngine.Builder().build();
			PebbleTemplate compiledTemplate = engine.getTemplate("register.html");
			Map<String, Object> context = new HashMap<String, Object>();
			//        context.put("username", "XXX");
			//        context.put("email", "YYY");
			Writer writer = new StringWriter();
			compiledTemplate.evaluate(writer, context);
			String output = writer.toString();
			System.out.println(output);
			OhMyEmail.subject("星王传奇☆首战新区☆").from(FROM_NAME).to(TO_EMAIL).html(output).send();
			Assert.assertTrue(true);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

}