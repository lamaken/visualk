package visualk.gallery.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualk.db.MysqlLayer;
import visualk.gallery.objects.Artist;
import visualk.gallery.objects.Resource;
import visualk.gallery.objects.Work;

public class DbWorks extends MysqlLayer {
    
    private static final String dbUser = "hrzmkr_user";
    private static final String dbPassword = "hrzmkr_password";
    private static final String dbDb = "hrzmkr_db";
    
    

    public DbWorks() {
        super(dbUser, dbPassword, dbDb);
    }

    public Work getWorkById(Integer id) throws SQLException {
        Work work = new Work();
        ResultSet myResult=null;
        try {
            myResult = queryDB("SELECT * FROM works where idwork = " + id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbWorks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (myResult != null) {
            while (myResult.next()) {
                work.setIdwork(myResult.getInt("idwork"));
                work.setDescription(myResult.getString("description"));
                work.setTitle(myResult.getString("title"));

                ArrayList<Artist> artists = new ArrayList<>();
                artists.addAll(getArtistsFromWorkId(id));
                work.setArtists(artists);

                ArrayList<Resource> resources = new ArrayList<>();
                resources.addAll(getResourcesFromWorkId(id));
                work.setResources(resources);
            }
            myResult.close();
        }
        return work;
    }

    private ArrayList<Artist> getArtistsFromWorkId(Integer id) throws SQLException {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        ResultSet myResult = null;
        try {
            myResult = queryDB("SELECT * FROM artists,artists_works where artists.idartist=artists_works.idartist AND artists_works.idwork = " + id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbWorks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (myResult != null) {
            while (myResult.next()) {
                Artist artist = new Artist();
                artist.setIdartist(myResult.getInt("idartist"));
                artist.setAlias(myResult.getString("alias"));
                artist.setName(myResult.getString("name"));
                artist.setSurname(myResult.getString("surname"));
                artist.setTelef(myResult.getString("telf"));
                artist.setEmail(myResult.getString("email"));
                artist.setAddress(myResult.getString("address"));
                artists.add(artist);
            }
            myResult.close();
        }
        return artists;
    }

    private ArrayList<Resource> getResourcesFromWorkId(Integer id) throws SQLException {
        ArrayList<Resource> resources = new ArrayList<>();
        ResultSet myResult=null;
        try {
            myResult = queryDB("SELECT * FROM resources WHERE idwork = " + id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbWorks.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (myResult != null) {
            while (myResult.next()) {
                Resource resource = new Resource();
                resource.setIdres(myResult.getInt("idres"));
                resource.setIdwork(myResult.getInt("idwork"));
                resource.setMimetype(myResult.getString("mimetype"));
                resource.setUrl(myResult.getString("url"));
                resources.add(resource);
            }
            myResult.close();
        }
        return resources;
    }
}
