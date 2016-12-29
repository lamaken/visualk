package visualk.gallery.objects;

import java.util.ArrayList;

public class Artist {
    private Integer idartist;
    private String alias;
    private String name;    
    private String surname;  
    private String telef;
    private String email;
    private String address;
    
    private ArrayList<Work> obres = new ArrayList<>();  //obres fetes per aquest artista

    public void setIdartist(Integer idartist) {
        this.idartist = idartist;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIdartist() {
        return idartist;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelef() {
        return telef;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    
}
