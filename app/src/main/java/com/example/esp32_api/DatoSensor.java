package com.example.esp32_api;

public class DatoSensor {
    private String id;
    private String deviceId;
    private double temperatura;
    private double humedad;
    private String timestamp;

    public DatoSensor(String id, String deviceId, double temperatura, double humedad, String timestamp) {
        this.id = id;
        this.deviceId = deviceId;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getDeviceId() { return deviceId; }
    public double getTemperatura() { return temperatura; }
    public double getHumedad() { return humedad; }
    public String getTimestamp() { return timestamp; }
}
