/*
 * Path
 *
 *  Created on: 19/05/2012
 *      Author: lamaken@gmail.com
 *      path control
 */

package visualk.lmk.tubsworld.model;

import java.util.LinkedList;


import visualk.lmk.tubsworld.Constants;

/**
 * @author alex
 * 
 */
public class Path {

	public Integer num;// numero consecutiu que te el path ordenat segons
	
	
    
	
	
	// creacio
	public LinkedList<Cell> cellsPath;
	public LinkedList<Integer> nextGates;// gates que formen el path
	private boolean m_nextispath;
	private boolean m_nextisgate;

	private String m_id;// indentificador del path
	private Integer next;

	private void IsGate() {
		m_nextisgate = true;
		m_nextispath = false;
	}

	private void IsPath() {
		m_nextisgate = false;
		m_nextispath = true;
	}

	private void setPositionXY(Cell c, double x, double y) {
		c.setXY( x,y);
	}

	private Integer activeGate; // gate de gates que esta actiu
	private Integer numgroupgates;// cuants gates per grup

	public Path() {
		activeGate=0;
		numgroupgates=0;
		num=0;
		next=0;
		cellsPath=new LinkedList<Cell>();
		nextGates=new LinkedList<Integer>();
	}

	public void addStart(double x, double y) {
		Cell c = new Cell();
		c.setTypeEnd(Constants._START);
		setPositionXY(c, x, y);
		cellsPath.add(c);
	}// inicio del circuito

	public void addEnd(double x, double y) {
		Cell c = new Cell();
		setPositionXY(c, x, y);
		c.setTypeEnd(Constants._SAVED);
		cellsPath.add(c);
	};// final del circuito

	public void addKill(double x, double y) {
		Cell c = new Cell();
		setPositionXY(c, x, y);
		c.setTypeEnd(Constants._KILLED);
		cellsPath.add(c);
	};// final del circuito

	public void addCell(double x, double y) {
		Cell c = new Cell();
		c.setTypeEnd(Constants._NONE);
		setPositionXY(c, x, y);
		cellsPath.add(c);
	};// recta horitzontal est

	public void addGate(double x, double y) {
		Cell c = new Cell();
		setPositionXY(c, x, y);
		cellsPath.add(c);
	};// la continuacion es un gate, es el cell que conecta amb el gate

	public void setNextPath(Integer np) {
		next = np;
		IsPath();
	}

	public void setNextGate(Integer np) {
		numgroupgates++;
		nextGates.add(np);
		IsGate();
	}

	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		m_id = id;
	}

	public Integer getNextPath() {
		return next;
	}

	public Integer getNextGate() {
		
		int ret=-1;//not found
		int k=0;
		int n=0;
		for (n=0;n<nextGates.size();n++){
			if(k++==activeGate)ret=nextGates.get(n);
		}
		return(ret);

	}
	
	
	
	
	
	
	public void swapGates() {
		activeGate++;
		if (activeGate >= numgroupgates)
			activeGate = 0;
	}

	public Integer getActiveGate() {
		return activeGate;
	}

	public void setActiveGate(Integer ag) {
		activeGate = ag;
	}

	public void setNumGroup(Integer numg) {
		numgroupgates = numg;
	}

	public Integer getNumGroup() {
		return (numgroupgates);
	}

	public boolean nextIsGate() {
		//if((!m_nextispath)&&(!m_nextisgate))return true;
		return (m_nextisgate);
	}

	public boolean nextIsPath() {
		//if((!m_nextispath)&&(!m_nextisgate))return true;
		return (m_nextispath);
	}

	public Integer getBeginType() {
		return cellsPath.getFirst().getTypeEnd();
	}// only paths

	public Integer getEndType() {
		return cellsPath.getLast().getTypeEnd();
	}// only paths

	public Cell getEndCell() {
		return cellsPath.getLast();
	}

}
