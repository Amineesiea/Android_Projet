package com.example.ilyes.android_projet;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import java.io.*;


public class MovieInformation extends IntentService {

    private static final String ACTION_GET_ALL_MOVIE = "com.example.ilyes.android_projet.action.GETALLMOVIE";
    private static String url;

    public static void setUrl(String url2){
        url = url2;

    }
    public MovieInformation(){
        super("MovieInformation");
    }


    public static void startActionMovie(Context context) {



        Intent intent = new Intent(context, MovieInformation.class);
        intent.setAction(ACTION_GET_ALL_MOVIE);
        context.startService(intent);
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent!=null){
            final String action = intent.getAction();
            handleActionMovie();
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(principale.MOVIE_UPDATE));
        }
    }

    private void handleActionMovie() {
        Log.d("ok web service","Thread service :"+Thread.currentThread().getName());
        JSONMovie getJSON = new JSONMovie();
        Log.d("URL",this.url);


        String MovieInformations = getJSON.getJSONFromUrl(this.url);

        BufferedWriter bufferCache = null;
        try {
            bufferCache = new BufferedWriter(new FileWriter(new File(getCacheDir(),"films.json")));
            bufferCache.write(MovieInformations);

        }catch (IOException e){
            Log.d("Cache","IOException"+e.getMessage());
        }finally {
            try{
                if(bufferCache !=null){
                    bufferCache.close();
                }
            }catch (IOException e){
                Log.d("Cache","IOException"+e.getMessage());
            }
        }
    }

}
