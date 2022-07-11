package com.example.demo.thymeleaf;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.parser.XMLDocumentFilter;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.cyberneko.html.HTMLConfiguration;
import org.cyberneko.html.filters.DefaultFilter;
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

//@SpringBootTest
class ThymeleafToImg1Tests {


    /**
     * https://my.oschina.net/u/214777/blog/5269412
     *
     * @throws Exception
     */
    @Test
    void html2img() throws Exception {
        Map<String, Object> variables = new HashMap<>();

        variables.put("title", "通用123");
        variables.put("aUrl", "https://hospital-1252497236.cos.ap-beijing.myqcloud.com/yfz_dev/avatar/1650262118338");


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

        Document document = loadHtml(example);
        Graphics2DRenderer g2r = new Graphics2DRenderer();
        g2r.setDocument(document, document.getDocumentURI());
        Dimension dim = new Dimension(670, 1000);
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

        variables.put("title", "通用123");
        variables.put("aUrl", "https://hospital-1252497236.cos.ap-beijing.myqcloud.com/yfz_dev/avatar/1650262118338");


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

        XMLDocumentFilter noop = new DefaultFilter();
        XMLDocumentFilter[] filters = { noop };

        var configuration = new HTMLConfiguration();
        configuration.setFeature("http://cyberneko.org/html/features/augmentations", true);
        configuration.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
        configuration.setProperty("http://cyberneko.org/html/properties/filters", filters);
        DOMParser parser = new DOMParser(configuration);
        //使用NekoHTML解析，不然就不能解析css样式
//        //是否允许命名空间
//        parser.setFeature("http://xml.org/sax/features/namespaces", false);
//        //是否允许增补缺失的标签。如果要以XML方式操作HTML文件，此值必须为真
//        parser.setFeature("http://cyberneko.org/html/features/balance-tags", true);
//        //是否剥掉<script>元素中的<!-- -->等注释符
//        parser.setFeature("http://cyberneko.org/html/features/scanner/script/strip-comment-delims", true);
//        parser.setFeature("http://cyberneko.org/html/features/balance-tags/ignore-outside-content", false);
//        parser.setFeature("http://cyberneko.org/html/features/augmentations", true);
//        parser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");

        parser.parse(new InputSource(new StringReader(example)));
        Document document = parser.getDocument();
        Graphics2DRenderer g2r = new Graphics2DRenderer();
        g2r.setDocument(document, document.getDocumentURI());
        Dimension dim = new Dimension(670, 1500);
        BufferedImage buff = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) buff.getGraphics();
        g2r.layout(g, dim);
        g2r.render(g);
        g.dispose();
        ImageIO.write(buff, "png", new File("C:\\tmp\\abc2.png"));
    }


    @Test
    public void cssboxTest2() throws Exception {
        String cmd = "C:\\temp\\wkhtmltopdf\\bin\\wkhtmltoimage " +
                "-amd64 --transparent --format png --crop-w 670 --quality 75 http://localhost:8080/share C:\\tmp\\3.png";
        Runtime.getRuntime().exec(cmd);

    }


}
