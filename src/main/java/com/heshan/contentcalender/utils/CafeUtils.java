package com.heshan.contentcalender.utils;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
}
