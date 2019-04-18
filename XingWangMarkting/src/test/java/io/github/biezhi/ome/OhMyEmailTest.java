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
    private static final String FROM_EMAIL = "343063471@qq.com";
    private static final String FROM_NAME = "星王传奇";
    
    @Before
    public void before() {
        OhMyEmail.config(OhMyEmail.SMTP_QQ(false), FROM_EMAIL, "hxoyqhxixuajbief");
    }
    
    @Test
    public void testPebble() throws IOException, PebbleException, SendMailException {
        PebbleEngine   engine           = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("register.html");

        Map<String, Object> context = new HashMap<String, Object>();
//        context.put("username", "XXX");
//        context.put("email", "YYY");

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("星王传奇☆首战新区☆")
                .from(FROM_NAME)
                .to(TO_EMAIL)
                .html(output)
                .send();
        Assert.assertTrue(true);
    }

}