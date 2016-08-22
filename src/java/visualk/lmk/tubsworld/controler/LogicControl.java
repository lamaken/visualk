/**
 * 
 */
package visualk.lmk.tubsworld.controler;

import visualk.lmk.tubsworld.Constants;
import visualk.lmk.tubsworld.TestScene;
import visualk.lmk.tubsworld.model.Avatar;
import visualk.lmk.tubsworld.view.BezierCurve;

/**
 * @author alex
 * 
 */
public class LogicControl {

	public static Integer delay_nextlevel = 0;
	public static Integer delay_start = 0;

	public static Integer level;
	public static Integer sublevel;
	public static Integer velocity;

	public static boolean m_gameEnded;// if end game
	public static boolean m_paused;

	public static Integer gameType;
	public static Avatar A1P = new Avatar();

	private static int numAvatars;
	private static int gameCounter;
	private static int levelCounter;

	public static String firstRecord;// nom i puntuacio del millor record

	public static BezierCurve trenAlk = null;

	public static void createGame(int lev, int gtype) {

		LogicControl.level = lev;
		LogicControl.sublevel = 0;
		LogicControl.gameType = gtype;
		LogicControl.velocity = 0;

		delay_nextlevel = 0;

		trenAlk = new BezierCurve(); // creem el beizer
		trenAlk.getM_circuit().loadCircuit(lev);// li carreguem un circuit
		trenAlk.create();// creem a partir del circuit

		//LogicControl.firstRecord = new NewRecord(lev).getFirstPlayerRecord(lev);
		//LogicControl.firstRecord = new NewRecord(lev).getFirstPoints(lev).toString();
		LogicControl.firstRecord = "demo";
		// Constants.level = level;

		setGameCounter(0);

		// TODO:LOADRECORD i posal per pantalla;

		m_paused = true;
		m_gameEnded = false;

		delay_start = Constants.DELAY_START_LEVEL;

		switch (gameType) {
		case Constants._1P:
			setNumAvatars(1);
			A1P.init();
			A1P.addBall();
			// A1P.setColor(1.0,0.6,0.2);
			break;

		}
	}

	public static void update(float elapsedtime) {

		delay_start--;

		if (delay_start < 0) {
			delay_start = 0;

			setGameCounter(getGameCounter() + 1);

			switch (gameType) {
			case Constants._1P:
				// one player.//////////////////////
				if (A1P.getLives() > 0) {
					A1P.update(elapsedtime);
					setLevelCounter(getLevelCounter() + 1);
				} else {
					Constants.lives = "";
				}

			default:
				// error gametype not found
				break;
			}
		}
	}

	public static void logica() {

		switch (gameType) {
		case Constants._1P:

			if (A1P.getLives() > 0) {

				if (A1P.getBalls().size() == 0) {
					// Core::singleton().gameScene().gameData().swapPaused();
					// Core::singleton().gameScene().pause().setText("Press SPACE to next level");
					A1P.logica();
					delay_nextlevel++;

					if (delay_nextlevel > Constants.DELAY_START_BALL) {
						delay_nextlevel = 0;

						// TODO:SOROLLET DE NEXTLEVEL
						LogicControl.nextLevel();
						
					} else {
						// TODO:un compte errere abans de començar
					}

				} else
					A1P.logica();
			} else {
				// TODO:SOROLLET DE ENDGAME

				endGame();

			}
			break;
		default:
			// error gametype not found
			break;
		}

	}

	void showHud() {
	}

	void control(int a) {
	}

	// bool canUpdate(list<Ball>::iterator it);

	public static Avatar Player1() {
		return A1P;
	}

	boolean paused() {
		return m_paused;
	}

	boolean isgameEnded() {
		return m_gameEnded;
	}

	void swapPaused() {
		m_paused = !m_paused;
	}

	void swapGameEnd() {
		m_gameEnded = !m_gameEnded;
	}

	int getGameType() {
		return gameType;
	}

	int getSublevel() {
		return sublevel;
	}

	static void endGame() {
		m_gameEnded = true;

	}// passa a endgame

	static void nextLevel() {
		TestScene.resetUniqueBall();

		sublevel++;
		setLevelCounter(0);
		setGameCounter(0);
		switch (gameType) {
		case Constants._1P:
			A1P.resetBalls();// las eliminamos
			for (int n = 0; n < sublevel + 1; n++) {
				A1P.addBall();
			}
			break;

		default:
			// error gametype not found
			break;
		}

	}// si todo ok,, pasa al siguiente nivel

	/**
	 * @return the gameCounter
	 */
	public static int getGameCounter() {
		return gameCounter;
	}

	/**
	 * @param gameCounter
	 *            the gameCounter to set
	 */
	public static void setGameCounter(int gameCounter) {

		LogicControl.gameCounter = gameCounter;
	}

	/**
	 * @return the levelCounter
	 */
	public static int getLevelCounter() {
		return levelCounter;
	}

	/**
	 * @param levelCounter
	 *            the levelCounter to set
	 */
	public static void setLevelCounter(int levelCounter) {
		LogicControl.levelCounter = levelCounter;
	}

	public static int getNumAvatars() {
		return numAvatars;
	}

	public static void setNumAvatars(int numAvatars) {
		LogicControl.numAvatars = numAvatars;
	}

}
