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
public class Work {
    private String uuid;
    private String title;
    private String description;
    private String price;
    private Integer stars;
    private ArrayList<Picture> fotos = new ArrayList<>();             // tecniques d'aquesta obra   
    private ArrayList<Tecnica> tecniques = new ArrayList<>();             // tecniques d'aquesta obra
    private ArrayList<Author> authors = new ArrayList<>();             // autors d'aquesta obra
    private ArrayList<User> likes = new ArrayList<>();       //usuaris que han fet like a a obra

}

