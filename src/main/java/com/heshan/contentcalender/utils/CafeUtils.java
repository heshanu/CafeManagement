package com.heshan.contentcalender.utils;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.heshan.contentcalender.constant.CafeConstant;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class CafeUtils {
    private CafeUtils() {
    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\t}", httpStatus);
    }
    public static String getUUID(){
        Date data=new Date();
        long time=data.getTime();
        return "BILL"+time;
    }
    public static JSONArray getJSONArrayFromString(String data) throws JSONException {
        JSONArray jsonArray=new JSONArray(data);
        return jsonArray;
    }
    public static Map<String,Object> getMapFromJSON(String data) throws JSONException {
        if(!Strings.isNullOrEmpty(data)){
            return new Gson().fromJson(data,new TypeToken<Map<String,Object>>(){}.getType());
        }
        return new HashMap<>();
    }

    public static boolean isFileExist(String filePath){
        try{
            log.info("File Path : "+filePath);
            File file=new File(filePath);
            return file.exists() && file!=null ? Boolean.TRUE : Boolean.FALSE;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public static boolean fileExitsInPC(String filePath,String fileName) {
        try {

            return new File(filePath+fileName+".pdf").isFile()? Boolean.TRUE : Boolean.FALSE;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
