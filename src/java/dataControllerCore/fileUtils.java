/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataControllerCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author u27brvz04
 */
public class fileUtils {

    private static final Map<String, String> CONTENT_TYPES = new HashMap<String, String>() {
        {
            put("png", "image/png");
            put("jpg", "image/jpeg");
            put("jpeg", "image/jpeg");
            put("bmp", "image/bmp");
            put("ico", "image/vnd.microsoft.icon");
            put("pdf", "application/pdf");
            put("doc", "application/msword");
            put("xls", "application/vnd.ms-excel");
            put("txt", "text/plain");
        }
    };
    private static final ArrayList<String> EXTENSIONS_FIT_TO_THE_BROWSER = new ArrayList<String>() {
        {
            add("png");
            add("jpg");
            add("jpeg");
            add("bmp");
            add("ico");
            add("pdf");
            add("txt");
        }
    };

    public static String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static String getContentType(String fileName) {
        return CONTENT_TYPES.get(getExtension(fileName));
    }

    public static boolean canBeShowInBrowser(String fileName) {
        return EXTENSIONS_FIT_TO_THE_BROWSER.contains(getExtension(fileName));
    }
}
