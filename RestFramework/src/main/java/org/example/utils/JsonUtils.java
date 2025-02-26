package org.example.utils;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    @SuppressWarnings("unchecked")
    public static Object[] convertJsonTestDataToObject(String path, String methodName) {
        File file = new File(path);

        ObjectMapper ob = new ObjectMapper();
        Map<String, List<Map<String, Object>>> convertedData;
        Object[] finalData;
        try {
            convertedData = ob.readValue(file, Map.class);
            List<Map<String, Object>> testData = convertedData.get(methodName);
            finalData = new Object[testData.size()][];
            for(int i=0; i<testData.size(); i++) {
                finalData[i] = new Object[] { testData.get(i) };
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalData;
    }

    public static <T> String convertPOJOClassToJsonString(T clazz) {
        ObjectMapper ob = new ObjectMapper();
        JsonNode jsonNode = ob.valueToTree(clazz);
        return jsonNode.toString();
    }
}
