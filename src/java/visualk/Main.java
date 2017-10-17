/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk;

////firma amb gif animat
//import java.io.IOException;
//import java.net.URL;
//import java.util.Enumeration;
//import java.util.jar.Attributes;
//import java.util.jar.Manifest;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author lamaken
 */
public class Main {

    private static final long serialVersionUID = 0x1a47a56c77L;

    //public final static String HOST_NAME = "http://localhost:8081";
    //public final static String HOST_NAME = "http://192.168.0.192:8081";
    //public final static String HOST_NAME="http://localhost:8084";    
    public final static String HOST_NAME = "http://alkasoft.org";

    //public final static String IMG_HOST_NAME = "http://localhost:8081";
    public final static String IMG_HOST_NAME = "http://alkasoft.org:8080";

    public final static String PORT = ":8080";

    public final static String HOST_VISUALK = "/visualk";

    public final static String VISUALK_VERSION = "4.2b";

//    public void SetVersion(String ver) {
//        String impVersion = ver;
//
//        Enumeration<URL> resources = null;
//        try {
//            resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        while (resources.hasMoreElements()) {
//            try {
//                Manifest manifest = new Manifest(resources.nextElement().openStream());
//                // check that this is your manifest and do what you need or get the next one
//                Attributes attributes = manifest.getMainAttributes();
//                impVersion = attributes.getValue("Implementation-Version");
//                System.err.printf(impVersion);
//            } catch (IOException E) {
//                // handle
//                System.err.printf("Error while reading version: " + E.getMessage());
//            }
//        }
//        VISUALK_VERSION=impVersion;
//
//    }
}
