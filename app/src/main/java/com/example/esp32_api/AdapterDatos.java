package com.example.esp32_api;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolder> {

    private List<DatoSensor> lista;

    public AdapterDatos(List<DatoSensor> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dato, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatoSensor dato = lista.get(position);
        holder.txtTemp.setText("Temp: " + dato.getTemperatura() + " °C");
        holder.txtHum.setText("Humedad: " + dato.getHumedad() + " %");
        holder.txtHora.setText(dato.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTemp, txtHum, txtHora;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            txtHum = itemView.findViewById(R.id.txtHum);
            txtHora = itemView.findViewById(R.id.txtHora);
        }
    }
}
