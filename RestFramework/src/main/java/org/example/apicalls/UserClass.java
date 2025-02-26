package org.example.apicalls;

import io.restassured.response.Response;
import org.example.endpoints.UserEndPoints;
import org.example.pojo.response.getallusers.RootGetAllUsersResponse;
import org.example.utils.Config;
import org.example.utils.RestUtils;

public class UserClass {

    public void getUsers(String page) {
        Config.getInstance().setMethod("GET");
        Config.getInstance().setRelURL(UserEndPoints.GET_USERS.replace("{pageNo}", page));
        Config.getInstance().setHeaders(RestUtils.getHeaders());
        RootGetAllUsersResponse response = RestUtils.callMethod().as(RootGetAllUsersResponse.class);
    }
}
