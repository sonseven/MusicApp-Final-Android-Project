package com.example.hotmusic.Service;

public class APIService {

    private static String base_url = "https://lengocson.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
