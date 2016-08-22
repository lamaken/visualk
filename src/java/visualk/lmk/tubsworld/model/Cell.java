/*
 * Cell
 *
 *  Created on: 19/05/2012
 *      Author: lamaken@gmail.com
 *      cell control
 */
package visualk.lmk.tubsworld.model;

import visualk.lmk.tubsworld.Constants;;
/**
 * @author alex
 * 
 */
public class Cell {
	
	public Integer endCell;
	
	public Cell() {
	};

	void setXY(double x, double y) {
		this.x = (double)( (Constants._left*x)+(x*Constants._width));//_left + (x * _width);
		this.y = (double) ((Constants._top*y)+(y*Constants._height));//_top + (y * _height);
	}

	void setTypeEnd(Integer typeend) {
		endCell = typeend;
	}

	Integer getTypeEnd() {
		return (endCell);
	}

	// screen position
	private double x;
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	private double y;
	
}
