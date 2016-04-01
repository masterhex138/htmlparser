/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmldocparser.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

    private String style;

    public void test(String htmlPath) {
            //String text = new String(Files.readAllBytes(Paths.get(htmlPath)), StandardCharsets.UTF_8);
           /* String text = readFile(htmlPath);
            System.out.println(text);
            FileUtils.writeByteArrayToFile(new File(htmlPath), text.getBytes("Cp1251"));*/


    }

    public void parse(String htmlPath) {
        try {
           // String text = readFile(htmlPath);
            String text = new String(Files.readAllBytes(Paths.get(htmlPath)), "Cp1251");
            //System.out.println(text);
            Document doc = Jsoup.parseBodyFragment(text);

            //Element el = doc.select("style").first();
            //style = el.html();
            /*Pattern pattern = Pattern.compile("<style type=\"text/css\">[^<]+</style>");
             Matcher matcher = pattern.matcher(text);
             if (matcher.find()) {
             System.out.println(matcher.group(0));
             style=matcher.group(0);
             }*/
            //System.out.println(style);
            Elements paragraphs = doc.select("p");
            for (Element p : paragraphs) {
                System.out.println("p :" + p.html());
            }
            Elements images = doc.select("img");
            int i = 0;
            for (Element image : images) {
                //System.out.println("img :"+image.attr("src"));
                String base64Image = image.attr("src").split(",")[1];
                byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
                String imagePath = "D:" + File.separator + "pics" + File.separator + i + ".png";
                FileUtils.writeByteArrayToFile(new File(imagePath), imageBytes);
                image.attr("src", imagePath);

                //Files.write(Paths.get(imagePath), imageBytes,new OpenOption() {});
                i++;
            }
            //System.out.println(doc.html());

            /*File file = new File(htmlPath);

             // if file doesnt exists, then create it
             if (!file.exists()) {
             file.createNewFile();
             }

             FileWriter fw = new FileWriter(file.getAbsoluteFile());
             String result = new String(doc.html().getBytes("UTF-8"));

             BufferedWriter bw = new BufferedWriter(fw);
             bw.write(result);
             bw.close();*/
            //System.out.println("doc " + doc.html());
            FileUtils.writeByteArrayToFile(new File(htmlPath), doc.html().getBytes("Cp1251"));
        } catch (IOException ex) {
            Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
