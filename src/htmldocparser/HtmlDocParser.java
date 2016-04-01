
package htmldocparser;

import htmldocparser.converter.Converter;
import htmldocparser.parser.HTMLParser;
import java.io.File;
import java.util.Arrays;


public class HtmlDocParser {


    public static void main(String[] args) {
        //System.out.println(Arrays.toString("\u0041".getBytes()));
        Converter converter = new Converter();
        //converter.windowsConvert("D:\\converting\\input2.doc","input2.odt","D:\\converting","odt");
        String htmlDocPath = converter.windowsConvert("D:\\converting","input.doc","D:\\converting","html");
        //String htmlDocPath = converter.poiDocToHtml("D:\\converting", "input.doc", "D:\\converting");
        //converter.windowsConvert("D:\\converting","input.html","D:\\converting","docx");
        HTMLParser htmlParser = new HTMLParser();
        htmlParser.parse(htmlDocPath);
        converter.windowsConvert("D:\\converting","input.html","D:\\converting","docx");
    }
    
}
