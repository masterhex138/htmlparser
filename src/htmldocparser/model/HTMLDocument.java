
package htmldocparser.model;

import java.util.List;


public class HTMLDocument {
    private String style;
    private List<String> paragraphs;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }
    
    
}
