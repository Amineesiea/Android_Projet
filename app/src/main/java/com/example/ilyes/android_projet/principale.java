package com.example.ilyes.android_projet;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class principale extends AppCompatActivity {
    public RecyclerView rv = null;
    private String value_titre = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);
        final Button button_recherche = findViewById(R.id.button_recherche);
        this.rv = findViewById(R.id.list_films);
        button_recherche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int duration = Toast.LENGTH_LONG;

                        EditText titre_film = findViewById(R.id.editText_titre);
                        value_titre = titre_film.getText().toString();





                        String url = "http://www.omdbapi.com/?s="+ value_titre.replaceAll(" ","+")+"&plot=full&apikey=ece2c8b7";

                        MovieInformation.setUrl(url);

                        MovieInformation.startActionMovie(principale.this);
                        IntentFilter intentFilter = new IntentFilter(MOVIE_UPDATE);
                        LocalBroadcastManager.getInstance(principale.this).registerReceiver(new MovieUpdate(),intentFilter);


                        rv.setLayoutManager(new GridLayoutManager(principale.this, 1));


                        initData();



                    }
                }
        );
        MovieAdapter ma = new MovieAdapter(principale.this, MovieFinder.getInstance().getFilmsList());

        rv.setAdapter(ma);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.item1:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        principale.this);

                // set title
                //alertDialogBuilder.setTitle("Redirection vers le site web de l'API");

                alertDialogBuilder
                        .setMessage("Voulez-vous être redirigé vers le site internet de l'API ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                String url = "http://www.omdbapi.com/";
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Non",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                return true;

            default: return super.onOptionsItemSelected(menuItem);
        }
    }




    public JSONObject extractJSON()  {
        try {

            JSONMovie jsonMovie = new JSONMovie();
            InputStream is = new FileInputStream(getCacheDir() + "/" + "films.json");

            JSONObject j = new JSONObject(jsonMovie.copyInputStream(is));

            return j;

        }catch (IOException e){
            e.printStackTrace();
            return new JSONObject();

        }catch (JSONException e){
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public void initData () {



        JSONObject jsonObject = extractJSON();

        JSONArray result ;
        MovieFinder finder  = MovieFinder.getInstance();

        if (!(jsonObject.equals(null)) && jsonObject.length() > 0) {

            if(finder.getFilmsList().isEmpty() ==  false) {
                finder.getFilmsList().clear();
            }

            try {


                result = jsonObject.getJSONArray("Search");



                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonFilm = result.getJSONObject(i);
                    finder.addFilm(
                            new Film(
                                    jsonFilm.getString("imdbID"),
                                    jsonFilm.getString("Title"),
                                    jsonFilm.getString("Poster"),
                                    jsonFilm.getString("Type"),
                                    jsonFilm.getString("Year")

                            )


                    );
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static final String MOVIE_UPDATE = "com.octip.cours.inf4042_11.MOVIE_UPDATE";


    public class MovieUpdate extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            Log.d("TAG",getIntent().getAction());
            if(MovieFinder.getInstance().getFilmsList() != null || !(MovieFinder.getInstance().getFilmsList().isEmpty())) {
                Toast.makeText(principale.this,"Le telechargement des films terminé !",Toast.LENGTH_SHORT).show();            }

            MovieAdapter ba = (MovieAdapter) rv.getAdapter();
            ba.setNewData(MovieFinder.getInstance().getFilmsList());
        }


    }


}
