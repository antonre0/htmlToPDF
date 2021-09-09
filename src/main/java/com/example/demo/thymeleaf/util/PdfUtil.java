package com.example.demo.thymeleaf.util;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileOutputStream;
import java.util.Map;

public class PdfUtil {
    /**
     * @param templateName 模板名称
     * @param variables    模板变量
     */
    public static void buildPdf( String templateName, Map<String, Object> variables) throws Exception {
        FileOutputStream os = new FileOutputStream("D:\\temp\\a.pdf");
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("thymeleaf/templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("templateName", templateName);
//        context.setVariable("pdfFileName", pdfFileName);
        context.setVariables(variables);
        //渲染模板
        String example = templateEngine.process(templateName, context);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFont(ResourceUtils.getFile("classpath:thymeleaf/fonts/simsun.ttf"), "simsun");
        builder.useFastMode();
        String baseUrl = ResourceUtils.getURL("classpath:thymeleaf/templates/").toString();
        //第一个参数是html页面  第二个参数 是一个全空的文件 里面什么都没有 但是后缀一定要是。html  作用类似于一个画板 如果不添加这个参数或者置为null 则html文件中的图片 不会转化 所以一定需要加
        builder.withHtmlContent(example, baseUrl);
        builder.toStream(os);
        builder.run();
    }





}
