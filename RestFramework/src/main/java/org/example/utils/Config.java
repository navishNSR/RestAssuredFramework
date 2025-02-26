package org.example.utils;

import io.restassured.response.Response;
import lombok.Data;

import java.util.Map;

@Data
public class Config {
    private static Config instance;
    private Config() {}

    public String baseURI;
    public String relURL;
    public String method;
    public String reqBody;
    public Map<String, String> headers;
    public Response response;


    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
}
