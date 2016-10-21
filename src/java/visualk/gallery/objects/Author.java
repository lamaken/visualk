package visualk.gallery.objects;

import java.util.ArrayList;

public class Author {
    String uuid;
    String email;
    String telef;    
    String bio;  
    String name;
    String surname;
    
    ArrayList<Obra> obres = new ArrayList<>();  
    ArrayList<User> user = new ArrayList<>();  // users can manage authors

}
