package visualk.lmk.tubsworld.model;

import java.util.LinkedList;

import visualk.lmk.tubsworld.Constants;
import visualk.lmk.tubsworld.controler.LogicControl;
import visualk.lmk.tubsworld.view.Graphics;




public class Avatar {

	String name;
	int points; // punts aconseguits
	int lives; // vides que li queden
	int seconds;

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	int m_uniqueBall = 0;// TODO:identificador unic per balls

	Graphics.colorType color = new Graphics.colorType();

	LinkedList<Ball> balls = new LinkedList<Ball>();

	LinkedList<Ball> ballsToDelete = new LinkedList<Ball>();;

	LinkedList<Path> buclepaths = new LinkedList<Path>();

	int timeFromStart;
	boolean m_dead;

	public int getTime() {
		return (timeFromStart);
	}

	void setTime(int tm) {
		timeFromStart = tm;
	}

	boolean isDead() {
		return m_dead;
	}

	void setDead(boolean dead) {
		m_dead = dead;
	}

	void checkToSwap() {
	}// IA

	void setColor(float r, float g, float b) {
		color.blue = b;
		color.green = g;
		color.red = r;
	}

	void updateBalls() {

		// elimina les que estan a la llista de balls to delete
		int n;
		for (n = 0; n < ballsToDelete.size(); n++) {
			balls.remove(ballsToDelete.get(n));
		}
		ballsToDelete.clear();
	}

	void KillBall(int i) {
		if (lives > 1) {
			
			//Core.singleton().playSound("killed");
		} else {
			// Core::singleton().soundManager().playOops();
			//Core.singleton().playSound("bye");
			// setTime(Core::singleton().gameScene().getHud().getTimeHud());
		}
		lives--;//TODO:restem una vida

	}

	void SaveBall(int i) {
		// when last ball
		if (balls.size() > 1) {
			// Core::singleton().soundManager().playSaved();
			//Core.singleton().playSound("saved");

		} else {
			// when still remain more balls
			// Core::singleton().soundManager().playYes();
			//Core.singleton().playSound("yes");
		}
		ballsToDelete.add(balls.get(i));
		points += 10 * getLives();// TODO:posem puntuacio al acabar la bola
	}

	void setPoints(int npoints) {
		points = npoints;
	}

	void setLives(int nlives) {
		lives = nlives;
	}

	public int getPoints() {
		return (points);
	}

	public int getLives() {
		return (lives);
	}

	public void init() {
		timeFromStart = 0;
		points = 0;
		seconds = 0;
		lives = Constants.INIT_LIVES;
		
		balls.clear();
		ballsToDelete.clear();
		points = 0; // punts aconseguits
		lives = Constants.INIT_LIVES; // vides que li queden
		m_uniqueBall = 0;
	}
	

	public void resetBalls() {
		balls.clear();
		ballsToDelete.clear();
	}

	public Ball getBallAt(int i) {
		int n = 0;
		int k;
		for (k = 0; k < balls.size(); k++) {
			if (n++ == i)
				return (balls.get(k));
		}
		return (null);
	}

	public void addBall() {
		Ball newBall = new Ball();
		newBall.setUnique(m_uniqueBall++);
		int rnd = (int) ((Math.random()*100) % Constants.MAX);
		if(balls.size()>0)newBall.delay = (int) rnd;// 100 espais de separacio
		balls.add(newBall);

	}

	public void update(float elapsedtime) {
		
		// TODO:EXISTEIX RETARD EN BOLES
		
		float velocitatXbola=1;//Math.round(0.07*elapsedtime);
		
		for (int n = 0; n < balls.size(); n++) {
			
			if (!isAny(balls.get(n),n)) {
				
				balls.get(n).delay--;
				if(balls.get(n).delay<0){	//TODO retar en les boles mal calculat
					balls.get(n).setPosition( balls.get(n).position() + velocitatXbola);//TODO:control de velocitat
				}
			}
		}
	}

	boolean isAny(Ball b,int pos) {
		boolean exist = false;
		for (int n = pos; n < balls.size(); n++) {
			
			if(balls.get(n).getUnique()!=b.getUnique()){
			
			if ((balls.get(n).actualPath() == b.actualPath())
					&& (balls.get(n).actualGate() == b.actualGate())
					&& ((balls.get(n).m_position +(Constants.MAX/2) > b.m_position)
					&& (balls.get(n).m_position -(Constants.MAX/2) < b.m_position))
					&& (balls.get(n).sem == b.sem))
				exist = true;
			}
		}
		return (exist);
	}

	public void logica() {

		Path p;
		int offs;
		int actualPath;
		int typeEnd = 0;
		int typeBegin = 0;
		boolean needUpdateBalls = false;

		// TODO;actualiza el texte de punts i vides

		Constants.lives = " ";
		for (int y = 0; y < getLives(); y++) {
			Constants.lives = Constants.lives + "*";
		}
		Constants.points = getPoints() + "";

		for (int n = 0; n < balls.size(); n++) {

			Ball ball = balls.get(n);
			offs = ball.position();
			if (offs >= Constants.MAX) { // max balls per path

				// //////guardo paths per on va passant
				/*
				 * if ( ball.getSemafor() == 0) {
				 * this->buclepaths.push_back(circuit
				 * ().getPathAt(ball.actualPath())); }else{
				 * this->buclepaths.push_back
				 * (Core::singleton().gameScene().circuit
				 * ().getGateAt(ball->actualGate())); }
				 */// ///////

				actualPath = ball.actualPath();

				if (actualPath != Constants.FINAL_CIRCUIT) {

					// calculem el seguent path o gate que ha d'agafar
					if (ball.getSemafor() == 0) {
						p = LogicControl.trenAlk.getM_circuit().getPathAt(actualPath);
					} else {
						p = LogicControl.trenAlk.getM_circuit().getGateAt(
								ball.actualGate());
					}

					// //mirem si passa alguna cosa
					if (ball.getSemafor() == 0) {
						typeBegin = LogicControl.trenAlk.getM_circuit()
								.getPathAt(ball.actualPath()).getBeginType();
						typeEnd = LogicControl.trenAlk.getM_circuit()
								.getPathAt(ball.actualPath()).getEndType();

						switch (typeBegin) {
						case Constants._START:
							// soundManager().playStart();
							break;
						case Constants._NONE:
							// setPoints(getPoints()+1); //puntuacio
							break;
						default:
							break;
						}

						switch (typeEnd) {
						case Constants._SAVED:
							SaveBall(n);
							needUpdateBalls = true;
							break;
						case Constants._KILLED:
							KillBall(n);
							needUpdateBalls = true;
							break;
						case Constants._NONE:
							// setPoints(getPoints()+1);//puntuacio
							break;
						default:
							// cout << "end not defined" << endl;
							break;
						}
					}

					// ///////////////////////////////////////// update ball

					if (p.nextIsPath()) {
						ball.setActualPath(p.getNextPath());
						ball.setSemafor(0);
						// DrawView.debug=BezierCurve.getM_circuit().getPathAt(ball.actualPath()).getId();
					} else {
						ball.setActualGate(p.getNextGate());
						ball.setSemafor(1);
						// DrawView.debug=BezierCurve.getM_circuit().getGateAt(ball.actualGate()).getId();
					}

					ball.setPosition(0);

					// /////////////////////////////////////// update ball

				}
			}
			if (ball.actualPath() == -1) {// torna a començar
				ball.setActualPath(0);
			}
		}
		if (needUpdateBalls) {

			updateBalls();// eliminem les boles mortes i finalitzades
		}
	}

	
	void setActualGate(int a) {
	}

	void setActualPath(int a) {
	}

	void setPosition(int a) {
	}

	public LinkedList<Ball> getBalls() {
		return balls;
	}

	// void draw();
	Graphics.colorType getcolor() {
		return color;
	}
}
