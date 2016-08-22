/*
 * Ball
 *
 *  Created on: 19/05/2012
 *      Author: lamaken@gmail.com
 *      ball control
 */

package visualk.lmk.tubsworld.model;

import visualk.lmk.tubsworld.Constants;



public class Ball {

	private Integer m_actualPath; // en quin path es troba la pilota
	Integer m_actualGate; // en quin gate es troba la pilota
	Integer m_position; // posicio relativa de la pilota
	Integer sem; // semafor 0:path 1:gate
	float r, g, b;
	Integer unique;// id unic de la ball
	Integer delay; // temps d'espera abans de sortir al circuit

	public Ball() {
		r = (float) 1.0;
		g = (float) 1.0;
		b = (float) 1.0;
		reset();
	}

	void reset() {
		m_actualPath = 0; // en quin path es troba la pilota
		m_actualGate = 0; // en quin gate es troba la pilota
		m_position = 0; // posicio relativa de la pilota
		unique=0;
		sem = 0; // semafor 0:path 1:gate}
		delay = 0;
	}

	public Integer actualPath() {
		return m_actualPath;
	}

	public Integer actualGate() {
		return m_actualGate;
	}

	public Integer position() {
		return m_position;
	}

	void setColor(float re, float gr, float bl) {
		r = re;
		g = gr;
		b = bl;

	}

	void setActualPath(Integer path) {
		m_actualPath = path;
	}

	public void setActualGate(Integer gate) {
		m_actualGate = gate;
	}

	void setPosition(float pos) {

		if (pos > Constants.MAX)
			m_position = Constants.MAX;
		else
			m_position = Math.round(pos);
	}

	public Integer getSemafor() {
		return sem;
	}

	void setUnique(Integer u) {
		unique = u;
	}

	Integer getUnique() {
		return unique;
	}

	void setSemafor(Integer new_semafor) {
		sem = new_semafor;
	}
}
