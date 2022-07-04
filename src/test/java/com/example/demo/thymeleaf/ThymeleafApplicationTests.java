package com.example.demo.thymeleaf;

import com.example.demo.thymeleaf.util.PdfUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ThymeleafApplicationTests {

    @Test
    void contextLoads() throws Exception {
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "标题");
        variables.put("no", "Rp100000002");
        variables.put("priceType", "标题");
        variables.put("name", "标题");
        variables.put("sex", "女");
        variables.put("age", "18");
        variables.put("date", "标题");
        variables.put("diagnosis", "描述");

        PdfUtil.buildPdf("demo", variables);
    }


}
