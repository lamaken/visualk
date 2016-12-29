/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.gallery.objects;

import java.util.ArrayList;

/**
 *
 * @author lamaken
 */
public class Work {
    
    private Integer idwork;
    private String title;
    private String description;
    private String price;
    
    private ArrayList<Resource> resources = new ArrayList<>();             // tecniques d'aquesta obra   
    private ArrayList<Tecnica> tecniques = new ArrayList<>();             // tecniques d'aquesta obra
    private ArrayList<Artist> artists = new ArrayList<>();             // autors d'aquesta obra
    private ArrayList<User> likes = new ArrayList<>();       //usuaris que han fet like a a obra

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }
    
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public Integer getIdwork() {
        return idwork;
    }

    public void setIdwork(Integer idwork) {
        this.idwork = idwork;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    

}

