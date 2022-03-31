package com.example.menutest.network.data;

import android.content.Context;

public class ApiUtils {
    public static ApplicationService appService(Context context) {
        return RetrofitService.client(context).create(ApplicationService.class);
    }
}
