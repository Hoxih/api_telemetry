package com.example.esp32_api;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelemetriaActivity extends AppCompatActivity {

    RecyclerView recyclerTelemetria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telemetria);

        recyclerTelemetria = findViewById(R.id.recyclerTelemetria);
        recyclerTelemetria.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")  // 
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<DatoSensor>> call = apiService.obtenerTelemetria();

        call.enqueue(new Callback<List<DatoSensor>>() {
            @Override
            public void onResponse(Call<List<DatoSensor>> call, Response<List<DatoSensor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AdapterDatos adapter = new AdapterDatos(response.body());
                    recyclerTelemetria.setAdapter(adapter);
                } else {
                    Toast.makeText(TelemetriaActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DatoSensor>> call, Throwable t) {
                Toast.makeText(TelemetriaActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("API", "Fallo: ", t);
            }
        });
    }
}
