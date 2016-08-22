package visualk.lmk.tubsworld.view;

import visualk.lmk.tubsworld.Constants;

//TODO canviar a final

 public class Graphics {
	
	 public static float width = 800;
	 public static float height = 600;
	 
	//TODO tamany lineas circucit en funcio de la sdk del android
	public static float circuitWidth = 7;
	public static float ballWidth = 15;
	
	
	public int m_textureId_fondo;// fons del circuit
	public int m_textureId_dead;// icono calavera
	public int m_textureId_save;// icono casa

	public static final Integer LINE_WEIGHT = 9;// gruix del cirucuit
	public static final Integer BALL_WEIGTH = 8;// gruix de la bola

	

	// ************ Data structure
	public static class floatPoint {
		public float x, y;
	}

	public static class floatPointArray {

		public int num;
		public floatPoint[] pt = new floatPoint[Constants.MAX];
	}

	// ***************** subprograms
	public static class colorType {
		public float red;
		public float green;
		public float blue;
	}
}
