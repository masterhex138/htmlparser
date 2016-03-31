
package htmldocparser;

import htmldocparser.converter.Converter;
import htmldocparser.parser.HTMLParser;
import java.io.File;


public class HtmlDocParser {


    public static void main(String[] args) {
        Converter converter = new Converter();
        converter.windowsConvert("D:\\converting\\input.doc","input.odt","D:\\converting","odt");
        String htmlDocPath = converter.windowsConvert("D:\\converting\\input.odt","input.html","D:\\converting","html");
        HTMLParser htmlParser = new HTMLParser();
        htmlParser.test(htmlDocPath);
   
    }
    
}
