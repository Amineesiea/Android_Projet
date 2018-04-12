package com.example.ilyes.android_projet; /**
 * Created by ilyes on 01/04/2018.
 */

import android.util.Log;

import org.json.*;

import java.io.*;
import java.net.*;

public class JSONMovie {
    public JSONMovie() {

    }

    public String getJSONFromUrl(String urlLink) {
        String reponse = "";
        try {
            URL url = new URL(urlLink);
            HttpURLConnection reqHttp = (HttpURLConnection) url.openConnection();
            reqHttp.setRequestMethod("GET");

            if (HttpURLConnection.HTTP_OK == reqHttp.getResponseCode()) {
                InputStream fluxLecture = new BufferedInputStream(reqHttp.getInputStream());
                reponse = copyInputStream(fluxLecture);
                Log.d("JSONMovie", "Les Informations ont été recupérées.");

            }


        } catch ( MalformedURLException e ) {
            Log.d("JSONMovie", "MalformedURLException :" + e.getMessage());
        } catch ( ProtocolException e ) {
            Log.d("JSONMovie", "ProtocolException :" + e.getMessage());
        } catch ( IOException e ) {
            Log.d("JSONMovie", "IOException :" + e.getMessage());
        } catch ( Exception e ) {
            Log.d("JSONMovie", "Exception :" + e.getMessage());
        }
        return reponse;
    }


    public static String copyInputStream(InputStream inputStream) {

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;


        try {
            while ((line = streamReader.readLine() ) != null) {

                stringBuilder.append(line);
                Log.d("JSONMovie", line);
            }
        } catch ( IOException e ) {
            Log.d("CopyInputStream", "ERREUR ajout ligne au string builder :" + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch ( IOException e ) {
                Log.d("CopyInputStream", "ERREUR fermeture stream :" + e.getMessage());
            }
        }

        return stringBuilder.toString();

    }
}

