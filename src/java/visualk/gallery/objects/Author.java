package visualk.gallery.objects;

import java.util.ArrayList;

public class Author {
    private String uuid;
    private String email;
    private String telef;    
    private String bio;  
    private String name;
    private String surname;
    private String curriculum;
    
    private ArrayList<Work> obres = new ArrayList<>();  //obres fetes per aquest artista
}
