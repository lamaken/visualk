/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.gallery.objects;

import java.util.ArrayList;
import visualk.gallery.db.DbWorks;

/**
 *
 * @author lamaken
 */
public class Work {

    public String idwork;
    public String title;
    public String description;
    public String price;

    public ArrayList<Resource> resources = new ArrayList<>();             // tecniques d'aquesta obra   
    public ArrayList<Tecnica> tecniques = new ArrayList<>();             // tecniques d'aquesta obra
    public ArrayList<Artist> artists = new ArrayList<>();             // autors d'aquesta obra
    public ArrayList<User> likes = new ArrayList<>();       //usuaris que han fet like a a obra

    public Work() {
        title = "";
        description = "";
        price = "";
    }

    static public Work getWorkByIdWork(String idWork) {
        Work work = new Work();
        try {
            work = new DbWorks().getWorkById(idWork);
        } catch (Exception e) {
            work = null;    
        }
        return work;
    }
}
