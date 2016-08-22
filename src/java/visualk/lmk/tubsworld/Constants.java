/**




constants pel l'escena TestScene

 * 
 */
package visualk.lmk.tubsworld;



/**
 * @author alex
 *
 */
public class Constants {
	
	
	public static final  boolean Debug = false;
	public static final Integer MAX = 60;// quants punts per path
	
	public static final Integer DELAY_START_BALL=100;
	public static final Integer DELAY_START_LEVEL=100;
	
	
	public static final Integer MAXCONTPTS = 10;// quants controls points per
													// path
		
	public static final Integer MAXPATHS = 35;
	public static final Integer MAXGATES = 35;
	
	public static String points="debug";
	public static String lives="debug";
	public static String seconds="";
	//public static String firstRecord="???";
	
	public static String cName="No name";	//circuit name
	//public static int level=1;			//id circuit
	
	
	

	public static final Integer MAXAVATARS = 1;// quantes boles


	
//	public static final double VELOCITY_AVATARS = 0.00065;// interval de temps
															// que incrementa
															// posicions
	public static final int _1P  = 0;	// ONE PLAYER
	public static final int _2P  = 1;	// TWO PLAYERS
	public static final int _CPU = 2;  // ONE PLAYER VERSUS CPU
	
	//gametype
	public static final int _CHALENGER  = 0;	// MAX CIRCUTS WITH MIN TIME
	public static final int _PRACTICE  = 1;	// CHOSE CIRCUIT AVAILABLE
	
	
	
	public static final Integer INIT_LIVES=3;
	
	//typeEndCell
	
	public static final int _KILLED	= 1;
	public static final int _SAVED	= 3;
	public static final int _START	= 4;
	public static final int _NONE		= 5;
	
	
	public static final float _top = (float) 0;
	public static final float _left = (float) 0;
	public static final float _width = (float) 0.85;
	public static final float _height = (float) 0.85;

	public static final Integer FINAL_CIRCUIT = -1;
	
	public static final int NUMCIRCUITS = 7;

}
