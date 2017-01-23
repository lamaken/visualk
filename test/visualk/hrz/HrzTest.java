/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.hrz;

import java.util.Hashtable;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import visualk.hrz.objects.Horizon;
import visualk.html5.UniqueName;
//import static org.mockito.Mockito.*;

/**
 *
 * @author lamaken
 */
public class HrzTest {

    private static HttpServletRequest request;
    private static HttpServletResponse response;

    Hrz mainInstance = new Hrz();
    private final Hashtable<String, Horizon> hrzns = new Hashtable<String, Horizon>();

    public HrzTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Create
     */
    @Test
    public void addingTwoHorizonsCheckSessioId() {
        addHorizon();
        assertEquals(hrzns.get(Hrz.sessionId).getNameHrz(), Hrz.sessionId);
        String main=Hrz.sessionId;
        addHorizon();
        assertEquals(hrzns.get(Hrz.sessionId).getNameHrz(), Hrz.sessionId);
        String result=Hrz.sessionId;
        assertNotEquals(main,result);
         
    
    }
    
    
    /**
     * adding 4 horizons
     */
    @Test
    public void addingHorizons() {
        addHorizon();//1
        addHorizon();//2
        addHorizon();//2
        addHorizon();//2
        
        
        
        assertEquals(hrzns.size(),4);
    }
    
    /**
     * remove one horizon
     */
    public void removeHorizon() {
        hrzns.remove(Hrz.sessionId);
    }

    /**
     * get current horizon from Hrz session
     */
    public Horizon getHorizon() {
        String sessionId = Hrz.sessionId;
        if (hrzns.contains(sessionId)) {
            assertTrue(hrzns.contains(Hrz.sessionId));
            return hrzns.get(sessionId);
        };
        return new Horizon(Hrz.sessionId);
    }

    public void addHorizon() {

        String sessionId = Hrz.sessionId;
        if (hrzns.containsKey(sessionId)) {     
            mainInstance.openNewSession();
            sessionId = Hrz.sessionId;
        }
        hrzns.put(sessionId, new Horizon(sessionId));
        

    }

}
