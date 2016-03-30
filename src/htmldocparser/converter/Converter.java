package htmldocparser.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Converter {

    //EXAMPLE convert("D:\\converting\\input.doc","D:\\converting","html")
    public String windowsConvert(String inputFileName, String outputFileName,String outputPath, String convertTo) {
        String s = null;
        try {
            // Execute command
            String command = "\"C:\\Program Files (x86)\\LibreOffice 5\\program\\soffice.exe\" --headless --convert-to "+convertTo+" "+outputFileName+" --outdir \""+outputPath+"\" \""+inputFileName+"\"";
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
        return outputPath+File.pathSeparator+outputFileName;
    }
}
