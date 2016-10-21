/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.gallery.objects;

import java.util.ArrayList;
import visualk.gallery.db.DbAuthors;

/**
 *
 * @author lamaken
 */
public class Obra {
    private String uuid;
    private String title;
    private String type;
    private String images[];
    private String price;
    private String stars;
    
    private ArrayList<Tecnica> tecniques = new ArrayList<>();             // autors d'aquesta obra
    private ArrayList<Author> authors = new ArrayList<>();             // autors d'aquesta obra
    
    private DbAuthors dbAuthors;

    public Obra(){
        dbAuthors=new DbAuthors();
    }
    
    public void populateDb(){
        authors.clear();
        authors.addAll(dbAuthors.getFromObra(uuid));
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public ArrayList<Tecnica> getTecniques() {
        return tecniques;
    }

    public void setTecniques(ArrayList<Tecnica> tecniques) {
        this.tecniques = tecniques;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public DbAuthors getDbAuthors() {
        return dbAuthors;
    }

    public void setDbAuthors(DbAuthors dbAuthors) {
        this.dbAuthors = dbAuthors;
    }
    
    
    
    
}

