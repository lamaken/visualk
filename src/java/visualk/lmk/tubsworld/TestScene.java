/**
 * 
 */
package visualk.lmk.tubsworld;

import java.awt.Image;

import visualk.lmk.tubsworld.controler.LogicControl;
import visualk.lmk.tubsworld.model.Circuit;



/**
 * @author alex
 * 
 */
public class TestScene  {

	public TestScene() {

	
	}
	
	Image myBitmap;
	static int m_uniqueBall;

	
	//TextView tv_points;


	

	private int level = 1;
	private int gametype;

	public void startGame(int level, int gametype, boolean showScore) {
		
		
		
		LogicControl.createGame(level, gametype);
		

	}

	public void endGame(int level) {
		
		
		
		
	}
	

	// propias para el juego
	public void createBezierCircuit() {

	    
	}

	public Circuit circuit() {
		return LogicControl.trenAlk.getM_circuit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visualk.games.tubsworld.dev.control.IScene#leave()
	 */
	public void leave() {
		//LogicControl.trenAlk = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visualk.games.tubsworld.dev.control.IScene#update(double)
	 */
	public void update(float elapseTime) {

		LogicControl.trenAlk.controlPLAYERS(elapseTime);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visualk.games.tubsworld.dev.control.IScene#init()
	 */
	public void init() {
	/*
		tv_points = (TextView) Core.singleton().getActivity()
				.findViewById(R.id.puntuacio);
		
		 Typeface font1 = Typeface.createFromAsset(Core.singleton().getActivity().getAssets(), "fonts/ARCHRISTY.ttf");
	     tv_points.setTypeface(font1);
		
		tv_points.setText("TUBSWORLD");
		
		*/
		startGame(level, gametype, true);
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visualk.games.tubsworld.model.IScene#updateView()
	 */
	public void updateView() {
		LogicControl.trenAlk.draw();
		
		
		
		

		if (LogicControl.m_gameEnded) {
			//TODO:Arreglar aixo..
			////lmk Image b = Image.decodeResource(Core.singleton().getActivity().getResources(), R.drawable.gameover);
			////lmk myBitmap = Bitmap.createScaledBitmap(b, (int)Graphics.width,130,false) ;
			//Graphics.canvas.drawBitmap(myBitmap, Graphics.width/2-myBitmap.getWidth()/2, Graphics.height/2-myBitmap.getHeight()/2, null);
	          
		} else {
			
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visualk.games.tubsworld.model.IScene#updateModel()
	 */
	public void updateModel(float elapsedtime) {

		LogicControl.trenAlk.controlPLAYERS(elapsedtime);

		LogicControl.logica();// TODO:maquina d'estats si es mor o si comença o
							// vides++

	}

	public static void resetUniqueBall() {
		m_uniqueBall = 0;
	}

	
	
	public void clic() {// quan toca la
															// pantalla

		LogicControl.trenAlk.canIDraw=false;
		LogicControl.trenAlk.swapGates(); // refa els gates
		LogicControl.trenAlk.swapGatesPLAYERS(); // updateja pilotetes
		LogicControl.trenAlk.canIDraw=true;
		beforeExit();
	}

	

	private void beforeExit() {
	}
}
