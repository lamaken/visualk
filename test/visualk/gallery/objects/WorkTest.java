/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.gallery.objects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lamaken
 */
public class WorkTest {

    public WorkTest() {
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

    @Test
    public void newWorkNotNull() {
        System.out.println("newWorkNotNull");
        Work work = new Work();
        assertNotNull(work);
    }

    @Test
    public void newWorkHasDefaultDataPicture() {
        System.out.println("newWorkHasDefaultDataPicture");
        Work work = new Work();

        boolean allVoid = work.title.equals("")
                && work.description.equals("")
                && work.price.equals("")
                && (work.artists.size() == 0)
                && (work.likes.size() == 0)
                && (work.resources.size() == 0)
                && (work.tecniques.size() == 0);

        assertTrue(allVoid);
    }

    @Test
    public void getWorkFromDB() {
        System.out.println("newWorkPutData");
        String expected = "1";
        Work work = Work.getWorkByIdWork(expected);
        assertNotNull(work);
        assertEquals(work.idwork, expected);
    }

}
