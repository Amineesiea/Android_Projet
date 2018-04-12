package com.example.ilyes.android_projet;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ilyes on 01/04/2018.
 */

public class secondaire extends AppCompatActivity {


    private String id;
    private String titres;
    private String types;
    private String urlImages;
    private String date_publication;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondaire);
        Bundle s = getIntent().getExtras();
        this.id = s.getString("id");
        this.titres = s.getString("Titre");
        this.types = s.getString("Type");
        this.urlImages = s.getString("urlImage");
        this.date_publication=s.getString("Date_publication");



        TextView titre = findViewById(R.id.titre_film);

        titre.setText((CharSequence) titres);


        TextView datePublication = findViewById(R.id.date_publication);
        datePublication.setText("Date de publication : " + date_publication);


        TextView type = findViewById(R.id.type_film);
        type.setText("Type du film : " + types);


        ImageView imgUrl = findViewById(R.id.MiniatureFilm);
        Picasso.with(this).load(urlImages).into(imgUrl);







    }







    }












