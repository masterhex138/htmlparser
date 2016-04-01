/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmldocparser.utils;

import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.usermodel.PictureType;

/**
 *
 * @author NMoldabe
 */
public class MyPictureManager implements PicturesManager {
    private int id;
    @Override
    public String savePicture(byte[] bytes, PictureType pt, String suggestedName, float f, float f1) {
        System.out.println(suggestedName);
        return "D:\\pics\\"+id+"\\"+suggestedName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
