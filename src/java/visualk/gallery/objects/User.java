package visualk.gallery.objects;

import java.util.ArrayList;

public class User {
    private String uuid;
    private String email;
    private String password;
    private String secret;
    private String estrelles;    
    private Resource photo;
    private ArrayList<Artist> autors = new ArrayList<>();  //autors que gestiona aquest usuari
}
