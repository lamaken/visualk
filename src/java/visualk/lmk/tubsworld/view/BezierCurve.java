package visualk.lmk.tubsworld.view;

import java.awt.Image;
import java.util.LinkedList;

import visualk.lmk.tubsworld.Constants;
import visualk.lmk.tubsworld.controler.LogicControl;
import visualk.lmk.tubsworld.model.Ball;
import visualk.lmk.tubsworld.model.Circuit;
import visualk.lmk.tubsworld.model.Cell;
import visualk.lmk.tubsworld.model.Path;

/**
 * @author alex
 * 
 * 
 *         Es lencarregat de dibuixar el circuit i les boles
 * 
 * 
 * 
 */

public class BezierCurve {

	public boolean canIDraw = true;

	private Circuit m_circuit = null;

	private int[] coef = new int[Constants.MAXCONTPTS]; // the binomial
	public boolean start = true;

	int espera = 0; // path
	float kBLUR = 0;// TODO:BLUR

	private Graphics.floatPointArray[] circuitCurve = null;

	private Graphics.floatPointArray[] gatesCurve = null;
	private Graphics.floatPointArray ControlPts = new Graphics.floatPointArray();

	public int numPaths;
	public int numGates;

	Image bmpSave;
	Image bmpDead;
	Image bmpLive;

	Image bmpTemp;

	public BezierCurve() {
		start = true;

		circuitCurve = new Graphics.floatPointArray[Constants.MAXPATHS];
		gatesCurve = new Graphics.floatPointArray[Constants.MAXGATES];

		m_circuit = new Circuit();
	}

	public void create() {

		creaJoc();

		numPaths = getM_circuit().numPaths();
		for (int i = 0; i < numPaths; i++) {
			// read control points and draw them in red big points
			readControlPointsFromPaths(getM_circuit(), ControlPts, i);
			// Compute the Bezier curve points and draw
			Bezier(ControlPts, Constants.MAX, circuitCurve[i]);
		}

		numGates = getM_circuit().numGates();
		for (int i = 0; i < numGates; i++) {
			// read control points and draw them in red big points
			readControlPointsFromGates(getM_circuit(), ControlPts, i);
			// Compute the Bezier curve points and draw
			Bezier(ControlPts, Constants.MAX, gatesCurve[i]);
		}

	}

	public void swapGates() {

		getM_circuit().swapGates();
		for (int i = 0; i < numGates; i++) {
			// read control points
			readControlPointsFromGates(getM_circuit(), ControlPts, i);
			// Compute the Bezier curve points and draw
			Bezier(ControlPts, Constants.MAX, gatesCurve[i]);
		}
		// resetGates();

	}

	public void swapGatesPLAYERS() {

		if (LogicControl.gameType == Constants._1P) {

			int numBalls = LogicControl.Player1().getBalls().size();

			for (int k = 0; k < numBalls; k++) {
				int actualpath = LogicControl.Player1().getBallAt(k)
						.actualPath();
				int actualgate = getM_circuit().getPathAt(actualpath)
						.getNextGate();
				LogicControl.Player1().getBallAt(k).setActualGate(actualgate);
			}

		}

	}// refresh players gates

	public void controlPLAYERS(float elapsedtime) {
		// time += elapsedtime;
		// if (time >Constants.VELOCITY_AVATARS) {
		LogicControl.update(elapsedtime);
		// time = 0;
		// }
	} // logica

	public void initdraw() {

		/*
		 * bmpDead = BitmapFactory.decodeResource(
		 * Graphics.context.getResources(), R.drawable.dead); bmpTemp =
		 * BitmapFactory.decodeResource( Graphics.context.getResources(),
		 * R.drawable.cor);
		 * 
		 * bmpSave = BitmapFactory.decodeResource(
		 * Graphics.context.getResources(), R.drawable.home); bmpLive =
		 * Bitmap.createScaledBitmap(bmpTemp, 45, 45, true); // resetCircuit();
		 * // resetGates();
		 */
	}

	public void draw() {

		if (start) {
			initdraw();
			start = false;
		} else {

			// TODO:draw all

			if (canIDraw) {

				drawFondo();
				drawPaths();
				drawGates();
				drawPilotaLLESTA();
				drawIcons();// icons de dead y se house }//pinta escena
				drawLives();
				drawPoints();
				drawRecord();
			}

			if (Constants.Debug) {
				// drawDebug();
			}
		}
	}

	private void drawPoints() {

		/*
		 * Canvas c = new Canvas(); c = Graphics.canvas; LinearLayout layout =
		 * new LinearLayout(Core.singleton().getActivity()); TextView textView =
		 * new TextView(Core.singleton().getActivity()); Typeface font1 =
		 * Typeface.createFromAsset(Core.singleton() .getActivity().getAssets(),
		 * "fonts/LEHN085.ttf"); textView.setTypeface(font1);
		 * textView.setTextSize(23); textView.setTextColor(Color.WHITE);
		 * textView.setText(Constants.points); layout.addView(textView);
		 * layout.measure(c.getWidth(), c.getHeight()); layout.layout(0, 0,
		 * c.getWidth(), c.getHeight()); layout.draw(c);
		 */
	}

	private void drawRecord() {
		/*
		 * 
		 * Canvas c = new Canvas();
		 * 
		 * c= Graphics.canvas; LinearLayout layout = new
		 * LinearLayout(Core.singleton().getActivity()); TextView textView = new
		 * TextView(Core.singleton().getActivity()); Typeface font1 =
		 * Typeface.createFromAsset(Core.singleton() .getActivity().getAssets(),
		 * "fonts/LEHN085.ttf"); textView.setTypeface(font1);
		 * textView.setTextColor(Color.YELLOW); textView.setTextSize(23);
		 * textView.setText(LogicControl.firstRecord); layout.addView(textView);
		 * layout.measure(c.getWidth(), c.getHeight()); layout.layout(0, 0,
		 * c.getWidth(), c.getHeight());
		 * 
		 * float x = Graphics.width / 2 - Graphics.paintTextYellow
		 * .measureText(LogicControl.firstRecord) / 2;
		 * 
		 * c.translate(x, 0); layout.draw(c); c.translate(-x, 0);
		 */
	}

	private void drawDebug() {
		float cx;
		float cy;

		for (int cc = 0; cc < numPaths; cc++) {
			cx = circuitCurve[cc].pt[0].x;
			cy = circuitCurve[cc].pt[0].y;
			float sX = (float) (((cx + 1.0) / 2.0) * Graphics.width);
			float sY = (float) (((1.0 - cy) / 2.0) * Graphics.height);

			if (null != getM_circuit().getPathAt(cc)) {
				/*
				 * canvas.drawText(getM_circuit() .getPathAt(cc).getId(), sX, sY
				 * - 3, Graphics.paintText);
				 */
				for (int k = 0; k < circuitCurve[cc].num - 1; k++) {
					cx = circuitCurve[cc].pt[k].x;
					cy = circuitCurve[cc].pt[k].y;
					sX = (float) (((cx + 1.0) / 2.0) * Graphics.width);
					sY = (float) (((1.0 - cy) / 2.0) * Graphics.height);

				}

			}

		}

	}

	private void drawGates() {
		// Graphics.canvas.drawPath(graph_path_Gates,
		// Graphics.paintGate);

		float startX = 0;
		float startY = 0;

		for (int num = 0; num < numGates; num++) {

			float cx = gatesCurve[num].pt[0].x;
			float cy = gatesCurve[num].pt[0].y;

			cx = (float) (((cx + 1.0) / 2.0) * Graphics.width);
			cy = (float) (((1.0 - cy) / 2.0) * Graphics.height);

			startX = cx;
			startY = cy;

			for (int i = 0; i < gatesCurve[num].num - 1; i++) {

				// TODO Xapuza para no pinte gate en 0,0

				if ((gatesCurve[num].pt[i].x != 0)
						|| (gatesCurve[num].pt[i].y != 0)) {

					cx = gatesCurve[num].pt[i].x;
					cy = gatesCurve[num].pt[i].y;

					cx = (float) (((cx + 1.0) / 2.0) * Graphics.width);
					cy = (float) (((1.0 - cy) / 2.0) * Graphics.height);

					int wx = (int) (Graphics.width / 2 - cx);
					int wy = (int) (Graphics.height / 2 - cy);

					int sx = (int) (Graphics.width / 2 - startX);
					int sy = (int) (Graphics.height / 2 - startY);

					if (((wx != 0) || (wy != 0)) && ((sx != 0) || (sy != 0))) {
						/*
						 * Graphics.canvas.drawLine( startX, startY, cx, cy,
						 * Graphics.paintGate);
						 */
					}
				}
				startX = cx;
				startY = cy;
			}
		}

	}

	private void drawPaths() {
		// Graphics.canvas.drawPath(graph_path_Paths,
		// Graphics.paintPath);

		float cx;

		float cy;
		float antX = 0;
		float antY = 0; // /////////////////////////////
		for (int cc = 0; cc < numPaths; cc++) {
			cx = circuitCurve[cc].pt[0].x;
			cy = circuitCurve[cc].pt[0].y;
			float sX = (float) (((cx + 1.0) / 2.0) * Graphics.width);
			float sY = (float) (((1.0 - cy) / 2.0) * Graphics.height);

			antX = sX;
			antY = sY;
			for (int k = 0; k < circuitCurve[cc].num - 1; k++) {
				cx = circuitCurve[cc].pt[k].x;
				cy = circuitCurve[cc].pt[k].y;
				sX = (float) (((cx + 1.0) / 2.0) * Graphics.width);
				sY = (float) (((1.0 - cy) / 2.0) * Graphics.height);
				/*
				 * Graphics.canvas.drawLine(antX, antY, sX, sY,
				 * Graphics.paintPath);
				 */
				antX = sX;
				antY = sY;
			}
		}

	}

	private void readControlPointsFromPaths(Circuit c,
			Graphics.floatPointArray P, int k) {

		LinkedList<Cell> cells = new LinkedList<Cell>();
		cells = c.allCellsFromPath(k);
		int i;
		for (i = 0; i < cells.size(); i++) {
			P.pt[i] = new Graphics.floatPoint();
			P.pt[i].x = (float) cells.get(i).getX();
			P.pt[i].y = (float) cells.get(i).getY();
		}
		P.num = i;
	}

	private void readControlPointsFromGates(Circuit c,
			Graphics.floatPointArray P, int k) {
		LinkedList<Cell> cells = new LinkedList<Cell>();
		cells = c.allCellsFromGate(k);
		int i;
		for (i = 0; i < cells.size(); i++) {
			P.pt[i].x = (float) cells.get(i).getX();
			P.pt[i].y = (float) cells.get(i).getY();
		}
		P.num = i;
	}

	private void ComputeCoeff(int n) {
		int j, k;
		for (k = 0; k <= n; k++) { // compute n! / (k!*(n-k)!)
			coef[k] = 1;
			for (j = n; j >= k + 1; j--)
				coef[k] *= j;
			for (j = n - k; j >= 2; j--)
				coef[k] /= j;
		}
	}

	private float BlendingValue(int n, int k, float t) {
		int j;
		float bv;
		// compute c[k]*t^k * (1-t)^(n-k)
		bv = coef[k];
		for (j = 1; j <= k; j++)
			bv *= t;
		for (j = 1; j <= n - k; j++)
			bv *= (1 - t);
		return bv;
	}

	private void ComputePoint(float t, int n, Graphics.floatPoint p,
			Graphics.floatPointArray ctrlPts) {

		int k;
		float b;

		p.x = (float) 0.0;
		p.y = (float) 0.0;
		for (k = 0; k <= n; k++) {
			b = BlendingValue(n, k, t);

			p.x += ctrlPts.pt[k].x * b;
			p.y += ctrlPts.pt[k].y * b;
		}
	}

	// compute the array of Bezier points - drawing done separately
	private void Bezier(Graphics.floatPointArray controlPts, int numInter,
			Graphics.floatPointArray curve) {
		// there are numContPts+1 control points and numInter t values to
		// evaluate the curve

		int k;
		float t;
		ComputeCoeff(controlPts.num - 1);
		curve.num = numInter + 1;
		for (k = 0; k < numInter; k++) {
			t = (float) k / (float) numInter;
			ComputePoint(t, controlPts.num - 1, curve.pt[k], controlPts);
		}
	}

	private void creaJoc() {

		circuitCurve = new Graphics.floatPointArray[Constants.MAXPATHS];
		gatesCurve = new Graphics.floatPointArray[Constants.MAXPATHS];

		for (int k = 0; k < Constants.MAXPATHS; k++) {
			circuitCurve[k] = new Graphics.floatPointArray();
			for (int m = 0; m < Constants.MAX; m++)
				circuitCurve[k].pt[m] = new Graphics.floatPoint();
		}

		for (int k = 0; k < Constants.MAXPATHS; k++) {
			gatesCurve[k] = new Graphics.floatPointArray();
			for (int m = 0; m < Constants.MAX; m++)
				gatesCurve[k].pt[m] = new Graphics.floatPoint();
		}

	}

	private void drawFondo() {
		// Graphics.canvas.drawBitmap(bmpFondo, 0, 0, Graphics.paintMain);
	}

	private void drawIcons() {
		for (int p = 0; p < getM_circuit().numPaths(); p++) {
			Path path = getM_circuit().getPathAt(p);
			if (path.getEndType() == Constants._KILLED) {
				putDead(path.getEndCell().getX(), path.getEndCell().getY());
			}
			if (path.getEndType() == Constants._SAVED) {
				putSaved(path.getEndCell().getX(), path.getEndCell().getY());
			}

		}
	}

	private void drawLives() {

		int num = Constants.lives.length();
		for (int n = 1; n < num; n++) {
			/*
			 * Graphics.canvas.drawBitmap(
			 * 
			 * bmpLive, Graphics.width - (bmpLive.getWidth() * (num - n)), 0,
			 * Graphics.paintMain);
			 */
		}

	}

	private void putDead(double d, double e) {

		float x = (float) (((d + 1.0) / 2.0) * Graphics.width);
		float y = (float) (((1.0 - e) / 2.0) * Graphics.height);
		/*
		 * Graphics.canvas.drawBitmap(bmpDead, x - bmpDead.getWidth() / 2, y -
		 * bmpDead.getHeight() / 2, Graphics.paintMain);
		 */
	}

	// Graphics.canvas.drawBitmap(b, x, y, Graphics.paint);

	private void putSaved(double px, double py) {

		float x = (float) (((px + 1.0) / 2.0) * Graphics.width);
		float y = (float) (((1.0 - py) / 2.0) * Graphics.height);
		/*
		 * Graphics.canvas.drawBitmap(bmpSave, x - bmpSave.getWidth() / 2, y -
		 * bmpSave.getHeight() / 2, Graphics.paintMain);
		 */
	}

	private void drawPilotaLLESTA() {
		// hi posem la piloteta
		int numballs;
		int actualpath;

		float px = 0, py = 0;

		kBLUR += 1.1;
		if (kBLUR > 50.1)
			kBLUR = (float) 10.1;

		// ///////////////////////////////// oneplayer

		if (LogicControl.gameType == Constants._1P) {

			numballs = LogicControl.Player1().getBalls().size();

			for (int b = 0; b < numballs; b++) {

				Ball ball = LogicControl.Player1().getBalls().get(b);
				actualpath = ball.actualPath();

				if (actualpath != Constants.FINAL_CIRCUIT) {
					if (ball.getSemafor() == 0) {// if is in a path
						px = circuitCurve[actualpath].pt[ball.position()].x;
						py = circuitCurve[actualpath].pt[ball.position()].y;
					} else {// if is in a gate
						px = gatesCurve[ball.actualGate()].pt[ball.position()].x;
						py = gatesCurve[ball.actualGate()].pt[ball.position()].y;
					}
				}

				int x = (int) (((px + 1.0) / 2.0) * Graphics.width);
				int y = (int) (((1.0 - py) / 2.0) * Graphics.height);

				if ((px != 0) || (py != 0)) {
					/*
					 * Graphics.canvas.drawCircle(x, y, Graphics.ballWidth,
					 * Graphics.paintBall);
					 */
				}
				// TODO:podria ser una elipse que seguis el path sinx rad etc..

			}

		}
		// //////////////////// one player
	}

	/**
	 * @return the m_circuit
	 */
	public Circuit getM_circuit() {
		return m_circuit;
	}

	/**
	 * @param m_circuit
	 *            the m_circuit to set
	 */
	public void setM_circuit(Circuit m_circuit) {
		this.m_circuit = m_circuit;
	}

}
