package com.example.demo.thymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HtmlToImg4_wkhtmltoimage_Tests {




    @Test
    public void cssboxTest2() throws Exception{
        String cmd = "C:\\temp\\wkhtmltopdf\\bin\\wkhtmltoimage " +
                " --width 670 --quality 75 http://localhost:8080/share C:\\tmp\\3.png";
        Runtime.getRuntime().exec(cmd);

    }




}
