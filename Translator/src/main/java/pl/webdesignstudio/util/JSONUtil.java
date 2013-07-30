package pl.webdesignstudio.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Sebastian Owodziń on 7/29/13.
 * Copyright (c) 2013 WEB DESIGN STUDIO Sebastian Owodziń. All rights reserved.
 */

public class JSONUtil {

    public static HashMap<String, String> jsonToHashMap(String jsonString) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonString);
        HashMap<String, String> map = new HashMap<String, String>();
        Iterator<?> keys = jsonObj.keys();
        while ( keys.hasNext() ) {
            String key = (String)keys.next();
            map.put(key, jsonObj.getString(key));
        }

        return map;
    }

    public static String getJsonString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (( line = reader.readLine() ) != null) {
            stringBuilder.append( line );
        }

        return stringBuilder.toString();
    }

}
