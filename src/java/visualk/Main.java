/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk;

//firma amb gif animat
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 *
 * @author lamaken
 */
public class Main {

    private static final long serialVersionUID = 102831173239L;

    //public final static String HOST_NAME = "http://localhost:8081";
    //public final static String HOST_NAME="http://localhost:8084";
    public final static String HOST_NAME="http://alkasoft.org";
    public final static String HOST_VISUALK = "/visualk";

    public final static String VISUALK_VERSION = getVersion();

    private static String getVersion() {

        String v = "notfound";
        try {
            Enumeration<URL> resources = Main.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                try {
                    Manifest manifest = new Manifest(resources.nextElement().openStream());
                    Attributes attr = manifest.getMainAttributes();
                    v = attr.getValue("Manifest-Version");

                } catch (IOException E) {
                    // handle
                    v = "error1";
                }
            }
        } catch (Exception e) {
            v = "error2";
        }
        return v;
    }

}
