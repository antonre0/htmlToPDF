package com.example.demo.thymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest
class ThymeleafToImg2_cssbox_Tests {








    @Test
    public void cssboxTest() {
        try {
            MyImageRenderer render = new MyImageRenderer();
            var dimension = new org.fit.cssbox.layout.Dimension();
            dimension.setSize(670, 2000);
            render.setWindowSize(dimension,false);
            System.out.println("start");
            String url = "http://localhost:8080/share";/*网络链接的html*/
            FileOutputStream out = new FileOutputStream("C:\\tmp"+File.separator+"html.png");/*生成文件的路径*/
            render.renderURL(url, out );/*将url网页写入生成文件中*/
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void cssboxTest2() {
        try {
            ImageRenderer render = new ImageRenderer();
            var dimension = new org.fit.cssbox.layout.Dimension();
            dimension.setSize(670, 2000);
            render.setWindowSize(dimension,false);
            System.out.println("start");
            String url = "http://localhost:8080/share";/*网络链接的html*/
            FileOutputStream out = new FileOutputStream("C:\\tmp"+File.separator+"html.png");/*生成文件的路径*/
            render.renderURL(url, out ,ImageRenderer.Type.PNG);/*将url网页写入生成文件中*/
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
