/**
 * Created By Subhradip Sinha
 * Date: 9/16/2024
 * Project Name: jatango
 */

package demo.pageObject;

import demo.DriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ReadTestImageOCR extends DriverManager {
    public static String ImageToText(String path){
        ITesseract image = new Tesseract();
        image.setDatapath("tessdata");
        image.setLanguage("eng");
        String str1=null;
        try {
            str1=image.doOCR(new File(path));
        }catch (TesseractException e){
            e.printStackTrace();
        }
        System.out.println(str1);
        return str1;
    }
}
