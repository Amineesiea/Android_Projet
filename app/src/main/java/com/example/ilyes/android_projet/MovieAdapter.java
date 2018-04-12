package com.example.ilyes.android_projet;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.content.Context;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by ilyes on 08/04/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.FilmHolder>{

    private ArrayList<Film> data = null;
    private Context mContext = null;


    public MovieAdapter(Context mContext, ArrayList<Film> data) {

        this.data = data;
        this.mContext = mContext;



    }

    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_film_element,parent,false);
        return new FilmHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmHolder holder, int position) {

        holder.bind(data.get(position).getTitre());
    }



    public int getItemCount() {

        if(data != null) {
            return data.size();
        }
        else {
            return 0;
        }
    }

    public void setNewData(ArrayList<Film> liste){
        this.data = liste;
        notifyDataSetChanged();
    }




    public class FilmHolder extends RecyclerView.ViewHolder implements OnClickListener{
        private TextView name;

        public FilmHolder(final View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            this.name = (TextView) itemView.findViewById(R.id.rv_film_element_name);


        }

        public void bind (String name) {

            this.name.setText(name);

        }



        public void onClick(View v)
        {

            int position = getAdapterPosition();

            Film f = data.get(position);
            Intent intent = new Intent(mContext, secondaire.class);
            intent.putExtra("id",f.getId());
            intent.putExtra("Titre",f.getTitre());
            intent.putExtra("Type",f.getType());
            intent.putExtra("urlImage",f.getUrl_image());
            intent.putExtra("Date_publication",f.getDate_publication());


            mContext.startActivity(intent);

        }

    }








}
