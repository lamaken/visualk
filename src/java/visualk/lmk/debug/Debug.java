/*
 * Debug.java
 *
 * Creat el 28 / juliol / 2006, 19:09
 *
 * Aquest codi ha estat implementat per
 * http://lamaken.free-bsd.org
 *
 */

package visualk.lmk.debug;
/**
 *
 * @autor alex
 */
public class Debug {
    
    private boolean debugging=true;
    
    
    /** Crea la inst�ncia de Debug */
    public Debug(boolean dbg) {
        debugging=dbg;
    }
    public void outText(int type,String dbTxt){
        if(debugging){
            if(type==-1)System.out.println(dbTxt);
            if(type==0)System.out.println(dbTxt);
        }
    }
    
}
