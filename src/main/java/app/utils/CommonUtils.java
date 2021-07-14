package app.utils;

public class CommonUtils {

    public static String getMimeType(String filePath) throws Exception {
        if(filePath.substring(filePath.lastIndexOf(".")+1).equals("pdf")){
            return "application/pdf";
        } else {
            throw new Exception("Unsupported Extension");
        }
    }
}
