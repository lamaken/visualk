/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.gallery.objects;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import visualk.gallery.db.DbAuthors;

/**
 *
 * @author lamaken
 */
public class ObraTest {
    
    public ObraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of populateDb method, of class Obra.
     */
    @Test
    public void testPopulateDb() {
        System.out.println("populateDb");
        Obra instance = new Obra();
        instance.populateDb();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUuid method, of class Obra.
     */
    @Test
    public void testGetUuid() {
        System.out.println("getUuid");
        Obra instance = new Obra();
        String expResult = "";
        String result = instance.getUuid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUuid method, of class Obra.
     */
    @Test
    public void testSetUuid() {
        System.out.println("setUuid");
        String uuid = "";
        Obra instance = new Obra();
        instance.setUuid(uuid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Obra.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Obra instance = new Obra();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Obra.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Obra instance = new Obra();
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Obra.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Obra instance = new Obra();
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Obra.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        Obra instance = new Obra();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImages method, of class Obra.
     */
    @Test
    public void testGetImages() {
        System.out.println("getImages");
        Obra instance = new Obra();
        String[] expResult = null;
        String[] result = instance.getImages();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImages method, of class Obra.
     */
    @Test
    public void testSetImages() {
        System.out.println("setImages");
        String[] images = null;
        Obra instance = new Obra();
        instance.setImages(images);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Obra.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Obra instance = new Obra();
        String expResult = "";
        String result = instance.getPrice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Obra.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        String price = "";
        Obra instance = new Obra();
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStars method, of class Obra.
     */
    @Test
    public void testGetStars() {
        System.out.println("getStars");
        Obra instance = new Obra();
        String expResult = "";
        String result = instance.getStars();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStars method, of class Obra.
     */
    @Test
    public void testSetStars() {
        System.out.println("setStars");
        String stars = "";
        Obra instance = new Obra();
        instance.setStars(stars);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTecniques method, of class Obra.
     */
    @Test
    public void testGetTecniques() {
        System.out.println("getTecniques");
        Obra instance = new Obra();
        ArrayList<Tecnica> expResult = null;
        ArrayList<Tecnica> result = instance.getTecniques();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTecniques method, of class Obra.
     */
    @Test
    public void testSetTecniques() {
        System.out.println("setTecniques");
        ArrayList<Tecnica> tecniques = null;
        Obra instance = new Obra();
        instance.setTecniques(tecniques);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthors method, of class Obra.
     */
    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        Obra instance = new Obra();
        ArrayList<Author> expResult = null;
        ArrayList<Author> result = instance.getAuthors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthors method, of class Obra.
     */
    @Test
    public void testSetAuthors() {
        System.out.println("setAuthors");
        ArrayList<Author> authors = null;
        Obra instance = new Obra();
        instance.setAuthors(authors);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDbAuthors method, of class Obra.
     */
    @Test
    public void testGetDbAuthors() {
        System.out.println("getDbAuthors");
        Obra instance = new Obra();
        DbAuthors expResult = null;
        DbAuthors result = instance.getDbAuthors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDbAuthors method, of class Obra.
     */
    @Test
    public void testSetDbAuthors() {
        System.out.println("setDbAuthors");
        DbAuthors dbAuthors = null;
        Obra instance = new Obra();
        instance.setDbAuthors(dbAuthors);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
