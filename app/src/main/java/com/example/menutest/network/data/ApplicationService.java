package com.example.menutest.network.data;

import com.example.menutest.model.DataForUser;
import com.example.menutest.model.DataWrapper;
import com.example.menutest.model.VenuesList;
import com.example.menutest.network.dto.LocationDto;
import com.example.menutest.network.dto.UserDto;
import com.example.menutest.network.dto.WrapperDto;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApplicationService {

    @POST("api/customers/login")
    Call<WrapperDto<DataForUser>> loginUser(
            @Body UserDto userDto
    );

    @POST("api/directory/search")
    Call<WrapperDto<VenuesList>> getVenuesList(
            @Body LocationDto locationDto
    );
}
