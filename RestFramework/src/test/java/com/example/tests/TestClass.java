package com.example.tests;

import org.example.apicalls.UserClass;
import org.example.dataprovider.DataProviderClass;
import org.example.utils.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class TestClass {

    @BeforeMethod
    public void setPrerequisite() {
        Config.getInstance().setBaseURI("https://reqres.in/");
    }

    @SuppressWarnings("unchecked")
    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "test")
    public void test1(Object data) {
        Map<String, Object> testData = (Map<String, Object>) data;
        UserClass userClass = new UserClass();
        userClass.getUsers(testData.get("page").toString());
    }


}
