package com.shopelago.utils;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopelago.models.Response;

public class Responses {
    public static Response parser(JsonElement jsonElement){
        Log.d("Responses", "parser: " + jsonElement.toString());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Log.d("Responses", "jsonObject: " + jsonObject.toString());
        String response = jsonObject.get("response") != null ? jsonObject.get("response").toString() : "";
        Log.d("Responses", "status: " + response);
        String message = jsonObject.get("message") != null ? jsonObject.get("message").toString() : "";
        Log.d("Responses", "message: " + message);
        String code = jsonObject.get("code") != null ? jsonObject.get("code").toString() : "";
        Log.d("Responses", "code: " + code);
        String token = jsonObject.get("token") != null ? jsonObject.get("token").toString() : "";
        Log.d("Responses", "token: " + token);
        String data = jsonObject.get("data") != null ? jsonObject.get("data").toString() : "";
        Log.d("Responses", "data: " + data);
        Response model = new Response();
        model.setResponse(Boolean.parseBoolean(response));
        model.setMessage(message);
        model.setCode(code);
        model.setToken(token);
        model.setData(data);

        return model;
    }
}
