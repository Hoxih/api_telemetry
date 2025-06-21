package com.example.esp32_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/telemetria")
    Call<List<DatoSensor>> obtenerTelemetria();
}
