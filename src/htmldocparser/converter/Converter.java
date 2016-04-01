package htmldocparser.converter;

import htmldocparser.utils.MyPictureManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;

public class Converter {
    
    private String filter="";

    //EXAMPLE convert("D:\\converting\\input.doc","D:\\converting","html")
    public String windowsConvert(String inputFilePath,String inputFileName,String outputPath, String convertTo) {
        System.out.println(inputFileName);
        String outputfileName = inputFileName.split("\\.")[0];
        String s = null;
        try {
            // Execute command

            if(convertTo.equals("docx")){
                filter=":\"Office Open XML Text\"";
            } else if(convertTo.equals("html")){
                filter=":\"HTML\"";
            }
            String command = "\"C:\\Program Files (x86)\\LibreOffice 5\\program\\soffice.exe\" --headless --convert-to "+convertTo+filter+" --outdir \""+outputPath+"\" \""+inputFilePath+File.separator+inputFileName+"\"";
            Process p = Runtime.getRuntime().exec(command);
            // Get output stream to write from it
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
            
        } catch (IOException e) {
        }
        return outputPath+File.separator+outputfileName+"."+convertTo;
    }
    
    public String poiDocToHtml(String inputFilePath,String inputFileName,String outputPath){
        String outputfileName = inputFileName.split("\\.")[0];
        try {
        //HWPFDocumentCore wordDocument = WordToHtmlUtils.loadDoc(new FileInputStream("D:\\input.doc"));
            //InputStream input = new FileInputStream (path + file);
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(inputFilePath+File.separator+inputFileName));
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .newDocument());
            
            MyPictureManager pictureManager = new MyPictureManager();
            pictureManager.setId(44444);
            wordToHtmlConverter.setPicturesManager(pictureManager);
            
            wordToHtmlConverter.processDocument(wordDocument);
            List<?> pics = wordDocument.getPicturesTable().getAllPictures();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    Picture pic = (Picture) pics.get(i);
                    try {
                        pic.writeImageContent(new FileOutputStream("D:\\44444\\" + pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            StyleSheet style = wordDocument.getStyleSheet();
            
            Document htmlDocument = wordToHtmlConverter.getDocument();
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            
            StreamResult streamResult = new StreamResult(out);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            out.close();
            ///////////////////////////////////////
            File file = new File(outputPath+outputfileName+".html");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            String result = new String(out.toByteArray());
            
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(result);
            bw.close();

            System.out.println("Done");
            //Files.write("",out);
            //System.out.println(result);

        } catch (TransformerException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outputPath+outputfileName+".html";
    }
    
    
    
}
