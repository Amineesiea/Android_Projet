package com.example.ilyes.android_projet;

/**
 * Created by ilyes on 09/04/2018.
 */

public class Film {

    private String id;
    private String titre;
    private String url_image;
    private String date_publication;
    private String type;


    public Film(String id,String titre, String url_image,String type,String date_publication){
        this.id = id;
        this.titre =titre;
        this.url_image = url_image;
        this.type = type;
        this.date_publication = date_publication;

    }




    public String getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getUrl_image() {
        return url_image;
    }

    public String getType() {
        return type;
    }
    public String getDate_publication() {
        return date_publication;
    }



}

