package org.example.dataprovider;

import org.example.utils.JsonUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider(name = "test")
    public Object[] getData(Method m) {
        return JsonUtils.convertJsonTestDataToObject("src/test/java/testdata/TestData.json", m.getName());
    }

}
