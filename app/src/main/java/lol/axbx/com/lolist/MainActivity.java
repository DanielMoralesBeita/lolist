package lol.axbx.com.lolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import static lol.axbx.com.lolist.ApiRequest.Runas_Precision;

public class MainActivity extends AppCompatActivity {
    private ApiRequest request;
    private RequestQueue queue;
    public static ListView list;
    public static Button lista_juegos;
    public static ArrayList<String> listItems=new ArrayList<String>();
    public static ArrayAdapter<String> adapter;

    public static GridView gridView;
    public static GridView gridView2;
    public static GridviewAdapter gridAdapter;


    public static ArrayList<String> Runas = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NukeSSLCerts().nuke();

        list = (ListView) findViewById(R.id.listView);
        lista_juegos=(Button) findViewById(R.id.leerjuegos);



        queue = MySingleton.getInstance(this).getRequestQueue();

        request = new ApiRequest(queue,this);

        request.getPlayers();


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);

        request.getGames();

        request.getRunas("precision");
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        //Obtenemos la referencia de la vista
        gridView = (GridView) findViewById(R.id.gridView);
        gridView2=(GridView)findViewById(R.id.gridView1);

    }

    public void abrir_detalle(View v){

        Intent myIntent=new Intent(this,Juegos.class);
        startActivity(myIntent);

    }
}
