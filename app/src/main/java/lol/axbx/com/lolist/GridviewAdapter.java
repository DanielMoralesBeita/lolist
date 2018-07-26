package lol.axbx.com.lolist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class GridviewAdapter extends BaseAdapter{

    private Context context;
    private String[] items;

    //Constructor de dos parámetros
    public GridviewAdapter(Context context, String[] items){
        super();
        this.context = context;
        this.items = items;
    }

    //Obetenemos la cantidad de imágenes
    @Override
    public int getCount() {
        return items.length;
    }

    //Obtenemos el objeto a partir de su posición
    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Generamos la vista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declaramos el ImageView
        ImageView img = null;
        if (convertView == null) {
            //Referenciamos el ImageView
            img = new ImageView(context);
            //Referenciamos el ImageView al convertView
            convertView = img;
            img.setPadding(5, 5, 5, 5);
        } else {
            img = (ImageView) convertView;
        }


        Picasso.get().load(items[position]).resize(100,100).into(img);

        return convertView;
    }

}