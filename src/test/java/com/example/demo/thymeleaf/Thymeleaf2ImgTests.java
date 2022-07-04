package com.example.demo.thymeleaf;

import org.apache.xerces.parsers.DOMParser;
import org.cyberneko.html.HTMLConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.dom.Document;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Thymeleaf2ImgTests {


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
        resolver.setPrefix("thymeleaf/templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("templateName", "demo2");
        context.setVariables(variables);
        //渲染模板
        String example = templateEngine.process("demo2", context);

        System.out.println(example);

        Document document = loadHtml(example);
        Graphics2DRenderer g2r = new Graphics2DRenderer();
        g2r.setDocument(document, document.getDocumentURI());
        Dimension dim = new Dimension(400, 1000);
        BufferedImage buff = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) buff.getGraphics();
        g2r.layout(g, dim);
        g.dispose();

        //计算图片的高度，实现自动伸缩高度
        Rectangle rect = g2r.getMinimumSize();
        buff = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) buff.getGraphics();
        //设置消除锯齿的代码
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2r.render(g);
        g.dispose();
        ImageIO.write(buff, "png", new File("C:\\tmp\\abc1.png"));
    }

    public static Document loadHtml(String html) throws IOException, SAXException {
        DOMParser domParser = new DOMParser(new HTMLConfiguration());
        //使用NekoHTML解析，不然就不能解析css样式
        domParser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
        domParser.parse(new InputSource(new StringReader(html)));
        return domParser.getDocument();
    }


    @Test
    void html2img2() throws Exception {
        Map<String, Object> variables = new HashMap<>();

        variables.put("title", "标题123456");
        variables.put("name", "张三");
        variables.put("image", "https://www.w3school.com.cn/i/photo/flower-2.jpg");


        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("thymeleaf/templates/");//模板所在目录，相对于当前classloader的classpath。
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        context.setVariable("templateName", "demo2");
        context.setVariables(variables);
        //渲染模板
        String example = templateEngine.process("demo2", context);

        System.out.println(example);

        DOMParser domParser = new DOMParser(new HTMLConfiguration());
        //使用NekoHTML解析，不然就不能解析css样式
        domParser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
        domParser.parse(new InputSource(new StringReader(example)));
        Document document = domParser.getDocument();
        Graphics2DRenderer g2r = new Graphics2DRenderer();
        g2r.setDocument(document, document.getDocumentURI());
        Dimension dim = new Dimension(400, 200);
        BufferedImage buff = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) buff.getGraphics();
        g2r.layout(g, dim);
        g2r.render(g);
        g.dispose();
        ImageIO.write(buff, "png", new File("C:\\tmp\\abc2.png"));
    }
}
