package com.example.demo.thymeleaf;

import com.aspose.html.HTMLDocument;
import com.aspose.html.Url;
import com.aspose.html.converters.Converter;
import com.aspose.html.rendering.image.ImageFormat;
import com.aspose.html.saving.ImageSaveOptions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HtmlToImg3_asposeHtml_Tests {


    /**
     * https://my.oschina.net/u/214777/blog/5269412
     *
     * @throws Exception
     */
    @Test
    void html2img() throws Exception {
        Map<String, Object> variables = new HashMap<>();

        variables.put("title", "标题123456");
        variables.put("name", "张三");
        variables.put("image", "https://www.w3school.com.cn/i/photo/flower-2.jpg");


        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("templateName", "card");
        context.setVariables(variables);
        //渲染模板
        String example = templateEngine.process("card", context);

        System.out.println(example);
        Files.writeString(Path.of("C:\\tmp\\t.html"),example);

        var htmlDocument = new HTMLDocument("C:\\tmp\\t.html");
        var saveOptions = new ImageSaveOptions(ImageFormat.Png);
        Converter.convertHTML(htmlDocument,saveOptions,"C:\\tmp\\a.png");
        htmlDocument.dispose();

    }








}
