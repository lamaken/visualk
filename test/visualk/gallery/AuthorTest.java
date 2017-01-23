package visualk.gallery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static junit.framework.TestCase.assertEquals;

import org.junit.After;
import org.junit.AfterClass;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorTest {

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

 

    private static HttpServletRequest request;// = mock(HttpServletRequest.class);
    private static HttpServletResponse response;// = mock(HttpServletResponse.class);
    
        
   /**
     * Test of doPost method, of class Hrz.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");

        Gallery instance = new Gallery();
        // TODO review the generated test code and remove the default call to fail.
        String idWork=String.valueOf(instance.idWork);
        assertEquals(idWork,"0");

    }
}
