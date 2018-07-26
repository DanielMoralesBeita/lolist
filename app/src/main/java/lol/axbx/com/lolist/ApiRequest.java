package lol.axbx.com.lolist;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


import static lol.axbx.com.lolist.MainActivity.adapter;
import static lol.axbx.com.lolist.MainActivity.gridAdapter;
import static lol.axbx.com.lolist.MainActivity.listItems;


public class ApiRequest {
    private RequestQueue queue;
    private Context context;
    private static final String API_KEY = "RGAPI-cc6b4e0c-0c2c-4581-83a5-950a0c4df732";
    private String region = "euw";
    public static ArrayList<String> listItemsA=new ArrayList<String>();
    public static ArrayList<String> Runas = new ArrayList<String>();
    public static ArrayList<String> RunasValor = new ArrayList<String>();

    public static String[] Runas_Precision;
    public static String[] Runas_Valor;




    public ApiRequest(RequestQueue queue, Context context){
        this.queue = queue;
        this.context = context;

    }

    public void checkPlayerName(final String name){

        //String url = "https://"+region+".api.pvp.net/api/lol/"+region+"/v1.4/summoner/by-name/"+name+"?api_key="+API_KEY;

        String url="https://la1.api.riotgames.com/lol/spectator/v3/featured-games?api_key="+API_KEY;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("APP", response.toString());

                try {
                    JSONObject json = response.getJSONObject(name.toLowerCase());
                    String name = json.getString("name");
                    long id = json.getLong("id");

                } catch (JSONException e) {
                    Log.d("APP", "EXCEPTION =" + e);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("APP", "ERROR = " + error);

            }
        });

        queue.add(request);


    }

    public void getGames(){

        String url="https://la1.api.riotgames.com/lol/spectator/v3/featured-games?api_key="+API_KEY;
        //String url="http://www.xolotlcl.com/games.json";


        JsonObjectRequest response =new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("##################################");

                if (response.length() > 0) {



try {
    JSONArray Juegos = response.getJSONArray("gameList");

 int numero_juegos=Juegos.length();
    for (int y = 0; y < Juegos.length(); y++) {



        JSONObject players=Juegos.getJSONObject(y);

        JSONObject xx=Juegos.getJSONObject(y);

        String gameMode = xx.getString("gameMode");

        System.out.println("GAMEMODE:"+gameMode);


        Iterator<?> keys = players.keys();


        String Nombre_participante = "";
        while (keys.hasNext()) {

            String key = (String) keys.next();
            if (players.get(key) instanceof JSONObject) {



                JSONArray participantes = xx.getJSONArray("participants");
                int numero_participantes = participantes.length();

                for (int j = 0; j < numero_participantes; j++) {
                    JSONObject Participante = participantes.getJSONObject(j);
                    Nombre_participante = Nombre_participante + Participante.getString("summonerName")+"-";

                }


                System.out.println("Numero de Juegos:"+numero_juegos+" GameMode:" + gameMode.toString() + " Numero de participantes" + numero_participantes);
                System.out.println(Nombre_participante);
                Clase_Juegos gamex = new Clase_Juegos(Integer.toString(numero_participantes), gameMode.toString(),Nombre_participante);

                lol.axbx.com.lolist.Juegos.juegosList.add(gamex);



            }


        }

        MainActivity.lista_juegos.setText(numero_juegos+ " | Leer Juegos");
    }
}catch (JSONException e){
    e.printStackTrace();
}


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(response);


}

    public void getPlayers(){

        String url="https://la1.api.riotgames.com/lol/static-data/v3/champions?locale=en_US&tags=image&dataById=false&api_key="+API_KEY;
        System.out.println("///////////////////////////");
        //String url="http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json";

        JsonObjectRequest response =new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("///////////////////////////");
                if (response.length() > 0) {
                    try {
                        JSONObject players = response.getJSONObject("data");

                        Iterator<?> keys = players.keys();

                        //for(int i=0;i<players.length();i++){
                        while(keys.hasNext()){

                            String key = (String)keys.next();
                            if ( players.get(key) instanceof JSONObject ) {
                                JSONObject xx = new JSONObject(players.get(key).toString());

                                String name=xx.getString("name");
                                String title=xx.getString("title");
                                JSONObject image=xx.getJSONObject("image");
                                String full="http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/"+image.getString("full");

                                 System.out.println("name:"+name);
                                 System.out.println("title:"+title);
                                System.out.println("image:"+full);


                                MainActivity.listItems.add(name+ " | " + title);

                                MainActivity.list.setAdapter(adapter);



                            }



                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(response);
    }


    public void getRunas(String tipo){

        System.out.println("///////////////////////////");
        String url="https://la1.api.riotgames.com/lol/static-data/v3/reforged-runes?api_key="+API_KEY;

        JsonArrayRequest response =new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override

            public void onResponse(JSONArray response) {
                System.out.println("*****************************");
                if (response.length() > 0) {
                    try {

                        for(int j=0;j<response.length();j++) {
                            JSONObject players = response.getJSONObject(j);

                            String Tipo_Runa=players.getString("runePathName");

                            String icon= players.getString("icon");

                            if(Tipo_Runa.equals("Precisión")) {
                                System.out.println("Tipo de runa: " + Tipo_Runa + " " + " Icono:" + icon);
                               Runas.add("https://ddragon.leagueoflegends.com/cdn/img/"+icon);


                            }

                            if(Tipo_Runa.equals("Valor")){
                                RunasValor.add("https://ddragon.leagueoflegends.com/cdn/img/"+icon);


                            }

                        }

                        System.out.println("USX "+ Runas.size());
                       Runas_Precision = new String[Runas.size()];
                       Runas_Precision = Runas.toArray(Runas_Precision);

                        Runas_Valor = new String[RunasValor.size()];
                        Runas_Valor = RunasValor.toArray(Runas_Valor);

                       // System.out.println("zzzz"+Runas_Precision[0]);

                        gridAdapter = new GridviewAdapter(MainActivity.adapter.getContext(),ApiRequest.Runas_Precision);
                        MainActivity.gridView.setAdapter(gridAdapter);

                        gridAdapter = new GridviewAdapter(MainActivity.adapter.getContext(),ApiRequest.Runas_Valor);

                        //Especificamos el adaptador

                         MainActivity.gridView2.setAdapter(gridAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(response);
        }

}
