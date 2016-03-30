
package htmldocparser;

import htmldocparser.converter.Converter;


public class HtmlDocParser {


    public static void main(String[] args) {
        Converter converter = new Converter();
        String htmlDocPath = converter.windowsConvert("D:\\converting\\input.doc","output.html","D:\\converting","html");
        
   
    }
    
}
