# Java html生成PDF

## 1. 使用 Thymeleaf+openhtmltopdf

更多使用方法请参考 [openhtmltopdf文档](https://github.com/danfickle/openhtmltopdf/wiki/Integration-Guide)
### 原理
使用Thymeleaf模板引擎，动态生成html，再使用openhtmltopdf将html转化为pdf。

* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [openhtmltopdf](https://github.com/danfickle/openhtmltopdf)

## 2. 使用 FreeMarker+itextpdf

iText是著名的开放源码的站点sourceforge一个项目，是用于生成PDF文档的一个java类库。通过iText不仅可以生成PDF或rtf的文档，而且可以将XML、Html文件转化为PDF文件。 