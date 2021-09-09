package com.example.demo.freemarker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FreeMarkerTests {

    @Test
    void contextLoads() throws Exception {
        final String file = "target/FREEMARKER2.pdf";
        final String temp = "freemarker/template_freemarker.html";
        Map<String, Object> data = new HashMap();
        data.put("name", "张三");
        data.put("title", "标题");
        String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(data, temp);
        JavaToPdfHtmlFreeMarker.createPdf(content, file);
    }

}
