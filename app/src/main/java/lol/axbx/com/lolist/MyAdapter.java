package lol.axbx.com.lolist;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Clase_Juegos> JuegosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TipoJuego, NumeroParticipantes, Participantes;

        public MyViewHolder(View view) {
            super(view);
            TipoJuego = (TextView) view.findViewById(R.id.TipoJuego);
            Participantes=(TextView)view.findViewById(R.id.Participantes);
            NumeroParticipantes=(TextView)view.findViewById(R.id.NumeroParticipantes);

        }
    }


    public MyAdapter(List<Clase_Juegos> JuegosList) {
        this.JuegosList = JuegosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vistacard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Clase_Juegos juegox = JuegosList.get(position);
        holder.TipoJuego.setText(juegox.getTipo_juego());
        holder.Participantes.setText(juegox.getParticipantes());
        holder.NumeroParticipantes.setText(juegox.getNumeroJugadores());
    }

    @Override
    public int getItemCount() {
        return JuegosList.size();
    }
}