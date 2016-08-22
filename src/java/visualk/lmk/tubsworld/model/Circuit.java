/*
 * Circuit
 *
 *     Created on : 19/05/2012
 * Last modify on : 20/05/2012
 *          Author: lamaken@gmail.com
 *                  circuit control
 */

package visualk.lmk.tubsworld.model;

import java.util.LinkedList;

import visualk.lmk.tubsworld.Constants;



/**
 * @author alex
 * 
 */
public class Circuit {

	// caracteristiques dels circuits
	class CircuitData {
		int circuit_id;
		String circuit_name;

		public CircuitData(String name, int id) {
			circuit_name = name;
			circuit_id = id;
		}
	}

	private static LinkedList<Path> Paths=null;
	private static LinkedList<Path> Gates=null;
	public LinkedList<CircuitData> circuit_data;

	public static final int CIRCUIT_ONE = 1;
	public static final int CIRCUIT_TWO = 2;
	public static final int CIRCUIT_THREE = 3;
	public static final int CIRCUIT_LOOP = 4;
	public static final int CIRCUIT_FIVE = 5;
	public static final int CIRCUIT_SIX = 6;
	public static final int CIRCUIT_SEVEN = 7;
	
	
	

	public Circuit() {
		Paths = new LinkedList<Path>();
		Gates = new LinkedList<Path>();

		circuit_data = new LinkedList<CircuitData>();

		circuit_data.add(new CircuitData("one", CIRCUIT_ONE));
		circuit_data.add(new CircuitData("two", CIRCUIT_TWO));
		circuit_data.add(new CircuitData("three", CIRCUIT_THREE));
		circuit_data.add(new CircuitData("loop", CIRCUIT_LOOP));
		
		//todo:defineix el codi del circuit
		circuit_data.add(new CircuitData("test5", CIRCUIT_FIVE));
		circuit_data.add(new CircuitData("test6", CIRCUIT_SIX));
		circuit_data.add(new CircuitData("test7", CIRCUIT_SEVEN));
		
		
		

	}

	public boolean loadCircuit(int level) {

		Paths.clear();
		Gates.clear();

		switch (level) {
		case CIRCUIT_ONE:
			loadOne();
			break;
		case CIRCUIT_TWO:
			loadTwo();
			break;
		case CIRCUIT_THREE:
			loadTree();
			break;
		case CIRCUIT_LOOP:
			loadLoop();
			break;
		case CIRCUIT_FIVE:
			loadFive();
			break;
		case CIRCUIT_SIX:
			loadSix();
			break;
		case CIRCUIT_SEVEN:
			loadSeven();
			break;
			
			

		default:

			break;
		}

		return (true);
	}

	public Integer numPaths() {
		return ((Integer) Paths.size());
	}

	public Integer numGates() {
		return ((Integer) Gates.size());
	}

	public LinkedList<Cell> allCellsFromPath(Integer i) {
		LinkedList<Cell> ret = new LinkedList<Cell>();
		Integer kk = 0;

		Integer j = 0;
		Integer n, nn;

		for (n = 0; n < Paths.size(); n++) {
			if (i == kk++) {
				if (j == Paths.get(n).getActiveGate()) {

					for (nn = 0; nn < Paths.get(n).cellsPath.size(); nn++) {
						ret.add(Paths.get(n).cellsPath.get(nn));
					}
				}
			}
		}

		return (ret);
	}

	public LinkedList<Cell> allCellsFromGate(Integer i) {
		LinkedList<Cell> ret = new LinkedList<Cell>();
		Integer kk = 0;

		Integer j = 0;
		Integer n, nn;

		for (n = 0; n < Gates.size(); n++) {
			if (i == kk) {
				if (j == Gates.get(n).getActiveGate()) {

					for (nn = 0; nn < Gates.get(n).cellsPath.size(); nn++) {
						ret.add(Gates.get(n).cellsPath.get(nn));
					}
				}
			}
			kk++;
			j++;
			if (j == Gates.get(n).getNumGroup())
				j = 0;
		}

		return (ret);
	}

	public Path getPathAt(Integer i) {
		
		if(Paths.size()>i){
			return (Paths.get(i));
		} else
			return null;

	}// retorna path que es troba en posicio i de l'array

	public Path getPathById(String id) {
		Integer n;
		for (n = 0; n < Paths.size(); n++) {
			if (Paths.get(n).getId().equals(id))
				return (Paths.get(n));
		}
		return null;
	}// retorna path amb identificador = id

	public Path getGateAt(Integer i) {
		return (Gates.get(i));
	}// retorna path que es troba en posicio i de l'array

	public Path getGateById(String id, Integer i) {
		Integer n, j;
		j = 0;
		for (n = 0; n < Gates.size(); n++) {
			if (Gates.get(n).getId().equals(id))
				if (j++ == i)
					return (Gates.get(n));
		}
		return null;
	}// retorna path amb identificador = id

	public void swapGates() {
		Integer n;
		for (n = 0; n < Gates.size(); n++) {
			Gates.get(n).swapGates();
		}
		for (n = 0; n < Paths.size(); n++) {
			Paths.get(n).swapGates();
		}
	}
	private void loadSeven(){
		
		Path[] p = new Path[Constants.MAXPATHS];

		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		
		p[0].addStart(0, 1);
		p[0].addCell(0, 0.8);
		pushPath(p[0], "A");
		
		p[1].addCell(0, 0.8);
		p[1].addCell(0, 0.6);
		p[1].addCell(-0.2, 0.6);
		pushPath(p[1], "B");
		
		
		p[2].addCell(-0.2, 0.6);
		p[2].addCell(-0.4, 0.6);
		pushPath(p[2], "C");
		
		p[3].addCell(-0.6, 0.6);
		p[3].addKill(-1, 0.6);
		pushPath(p[3], "D");
		
		p[4].addCell(-0.6, 0.45);
		p[4].addCell(-0.6, 0.3);
		p[4].addCell(-0.4, 0.3);
		pushPath(p[4], "E");
		
		p[5].addCell(-0.4, 0.3);
		p[5].addCell(0.5, 0.3);
		pushPath(p[5], "F");
		
		p[6].addCell(0.7, 0.15);
		p[6].addCell(0.7, 0);
		p[6].addCell(0.5, 0);
		pushPath(p[6], "G");
		
		p[7].addCell(0.5, 0);
		p[7].addCell(-0.6, 0);
		pushPath(p[7], "H");
		
		p[8].addCell(0.7, 0.3);
		p[8].addKill(1, 0.3);
		pushPath(p[8], "NY");
		
		p[9].addCell(-0.8, 0);
		p[9].addKill(-1, 0);
		pushPath(p[9], "K");
		
		p[10].addCell(-0.8, -0.15);
		p[10].addCell(-0.8, -0.3);
		p[10].addCell(-0.6, -0.3);
		pushPath(p[10], "I");
		
		p[11].addCell(-0.6, -0.3);
		p[11].addCell(0.4, -0.3);
		pushPath(p[11], "J");
		
		p[12].addCell(0.6, -0.3);
		p[12].addKill(1, -0.3);
		pushPath(p[12], "N");
		
		p[13].addCell(0.6, -0.45);
		p[13].addCell(0.6, -0.6);
		p[13].addCell(0.3, -0.6);
		p[13].addCell(0, -0.6);
		p[13].addCell(0, -0.8);
		pushPath(p[13], "L");
		
		p[14].addCell(0, -0.8);
		p[14].addEnd(0, -1);
		pushPath(p[14], "M");
		
		

		
		
		Path g[] = new Path[Constants.MAXGATES];// gates o compuertas
		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		
		g[0].addCell(-0.4,0.6);
		g[0].addCell(-0.6,0.6);
		g[0].addCell(-0.6,0.45);
		
		g[1].addCell(-0.4,0.6);
		g[1].addCell(-0.6,0.6);
		
		pushGate(g[0], "c",0,2);
		pushGate(g[1], "c",0,2);

		
		g[2].addCell(0.5,0.3);
		g[2].addCell(0.7,0.3);
		
		g[3].addCell(0.5,0.3);
		g[3].addCell(0.7,0.3);
		g[3].addCell(0.7,0.15);
		
		pushGate(g[2], "f",0,2);
		pushGate(g[3], "f",0,2);

		
		g[4].addCell(-0.6,0.0);
		g[4].addCell(-0.8,0.0);
		
		g[5].addCell(-0.6,0.0);
		g[5].addCell(-0.8,0.0);
		g[5].addCell(-0.8,-0.15);
		
		pushGate(g[4], "h",0,2);
		pushGate(g[5], "h",0,2);

		
		g[6].addCell(0.4,-0.3);
		g[6].addCell(0.6,-0.3);
		g[6].addCell(0.6,-0.45);

		g[7].addCell(0.4,-0.3);
		g[7].addCell(0.6,-0.3);
		
		
		pushGate(g[6], "j",0,2);
		pushGate(g[7], "j",0,2);

		
		
		
		getPathById("A").setNextPath(getPathById("B").num);
		getPathById("B").setNextPath(getPathById("C").num);
		
		
	

		getPathById("C").setNextGate(getGateById("c",0).num);
		getPathById("C").setNextGate(getGateById("c",1).num);
		
		getGateById("c",0).setNextPath(getPathById("E").num);
		getGateById("c",1).setNextPath(getPathById("D").num);
		
		getPathById("D").setNextPath(getPathById("A").num);
		getPathById("E").setNextPath(getPathById("F").num);
		
		
		getPathById("F").setNextGate(getGateById("f",0).num);
		getPathById("F").setNextGate(getGateById("f",1).num);
		
		getGateById("f",0).setNextPath(getPathById("NY").num);
		getGateById("f",1).setNextPath(getPathById("G").num);
		
		getPathById("NY").setNextPath(getPathById("A").num);
		getPathById("G").setNextPath(getPathById("H").num);
		
		
		getPathById("H").setNextGate(getGateById("h",0).num);
		getPathById("H").setNextGate(getGateById("h",1).num);
		
		getPathById("J").setNextGate(getGateById("j",0).num);
		getPathById("J").setNextGate(getGateById("j",1).num);
		
		
		getGateById("h",0).setNextPath(getPathById("K").num);
		getGateById("h",1).setNextPath(getPathById("I").num);
		getGateById("j",0).setNextPath(getPathById("L").num);
		getGateById("j",1).setNextPath(getPathById("N").num);

		
		
		
		
		
		
		
		
		
		getPathById("K").setNextPath(getPathById("A").num);
		getPathById("I").setNextPath(getPathById("J").num);
		
		getPathById("N").setNextPath(getPathById("A").num);
		
		
		getPathById("L").setNextPath(getPathById("M").num);
		
		getPathById("M").setNextPath(Constants.FINAL_CIRCUIT);

		
		
		
		
		
		
	}
	private void loadSix(){
		Path[] p = new Path[Constants.MAXPATHS];

		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		
		p[0].addStart(-1, 0.7);
		p[0].addCell(-0.7, 0.7);
		pushPath(p[0], "F");
		
		p[1].addCell(-0.7, 0.7);
		p[1].addCell(-0.2, 0.7);
		pushPath(p[1], "E");
		
		p[2].addCell(-0.2, 0.7);
		p[2].addCell(0.2, 0.7);
		pushPath(p[2], "A");
		
		p[3].addCell(0.7, 0.7);
		p[3].addKill(1, 0.7);
		pushPath(p[3], "G");
		
		p[4].addCell(0.7, 0.2);
		p[4].addCell(0.7, -0.3);
		pushPath(p[4], "B");
		
		p[5].addCell(0.2, -0.7);
		p[5].addCell(-0.2, -0.7);
		pushPath(p[5], "C");
		
		p[6].addCell(0.7, -0.7);
		p[6].addKill(0.7, -1);
		pushPath(p[6], "H");
		
		p[7].addCell(-0.7, -0.7);
		p[7].addEnd(-1, -0.7);
		pushPath(p[7], "I");
		
		p[8].addCell(-0.7, -0.3);
		p[8].addCell(-0.7, 0.2);
		pushPath(p[8], "D");
		
		p[9].addCell(-0.7, 0.2);
		p[9].addCell(-0.7, 0.7);
		p[9].addCell(-0.2, 0.7);
		pushPath(p[9], "J");
		
		
		Path g[] = new Path[Constants.MAXGATES];// gates o compuertas
		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		
		g[0].addCell(0.2,0.7);
		g[0].addCell(0.7,0.7);
		
		g[1].addCell(0.2,0.7);
		g[1].addCell(0.7,0.7);
		g[1].addCell(0.7,0.2);
		
		pushGate(g[0], "a",0,2);
		pushGate(g[1], "a",0,2);
		
		
		g[2].addCell(0.7,-0.3);
		g[2].addCell(0.7,-0.7);
		
		g[3].addCell(0.7,-0.3);
		g[3].addCell(0.7,-0.7);
		g[3].addCell(0.2,-0.7);
		
		pushGate(g[2], "b",0,2);
		pushGate(g[3], "b",0,2);
		
		
		g[4].addCell(-0.2,-0.7);
		g[4].addCell(-0.7,-0.7);
		
		g[5].addCell(-0.2,-0.7);
		g[5].addCell(-0.7,-0.7);
		g[5].addCell(-0.7,-0.3);
		
		pushGate(g[4], "c",0,2);
		pushGate(g[5], "c",0,2);
		
		
		
		getPathById("F").setNextPath(getPathById("E").num);
		getPathById("E").setNextPath(getPathById("A").num);
		getPathById("D").setNextPath(getPathById("J").num);
		getPathById("J").setNextPath(getPathById("A").num);
		
		getPathById("I").setNextPath(Constants.FINAL_CIRCUIT);


		getPathById("A").setNextGate(getGateById("a",0).num);
		getPathById("A").setNextGate(getGateById("a",1).num);
		
		getPathById("B").setNextGate(getGateById("b",0).num);
		getPathById("B").setNextGate(getGateById("b",1).num);
		
		getGateById("b",0).setNextPath(getPathById("H").num);
		getGateById("b",1).setNextPath(getPathById("C").num);
		

		getPathById("C").setNextGate(getGateById("c",0).num);
		getPathById("C").setNextGate(getGateById("c",1).num);

		getGateById("c",0).setNextPath(getPathById("I").num);
		getGateById("c",1).setNextPath(getPathById("D").num);
		
		
		
		getGateById("a",0).setNextPath(getPathById("G").num);
		getGateById("a",1).setNextPath(getPathById("B").num);
		
		
		getPathById("H").setNextPath(getPathById("F").num);
		getPathById("G").setNextPath(getPathById("F").num);
		
		
		
		
	}
	
	
	private void loadFive(){
		Path[] p = new Path[Constants.MAXPATHS];

		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		p[0].addStart(-1, 0.7);
		p[0].addCell(0.0, 0.7);
		pushPath(p[0], "G");

		p[1].addCell(0.3, -0.3);
		p[1].addEnd(0.0, -0.3);
		pushPath(p[1], "C");
				
		p[2].addCell(1, -0.5);
		p[2].addKill(1, -1);
		pushPath(p[2], "I");
		
		p[3].addCell(0.0, 0.7);
		p[3].addCell(0.85, 0.7);
		p[3].addCell(0.85, 0.1);
		pushPath(p[3], "H");
		
		p[4].addCell(0.85, -0.5);
		p[4].addCell(0.85,-0.8);
		p[4].addCell(0.7, -0.9);
		pushPath(p[4], "E");
		
		
		
		
		p[5].addCell(-0.85, -0.5);
		p[5].addCell(-0.85, 0);
		pushPath(p[5], "F");
		
		
		p[6].addCell(-0.5, 0);
		p[6].addCell(-0.5, 0.3);
		p[6].addCell(0.0, 0.3);
		pushPath(p[6], "D");
		
		
		
		p[7].addCell(0.0, 0.3);
		p[7].addCell(0.5, 0.3);
		p[7].addCell(0.5, 0.0);
		pushPath(p[7], "K");
		
		
		p[8].addCell(0.5, 0.0);
		p[8].addCell(0.5, -0.3);
		p[8].addCell(0.3, -0.3);
		pushPath(p[8], "J");
		
		
		p[9].addCell(-0.85, 0);
		p[9].addCell(-0.85, 0.7);
		p[9].addCell(0.0, 0.7);
		pushPath(p[9], "M");
		
		p[10].addCell(-0.5, -0.5);
		p[10].addCell(-0.5, 0);
		pushPath(p[10], "P");
		
		p[11].addCell(0.7, -0.9);
		p[11].addCell(0.6,-1);
		p[11].addCell(0.0, -1);
		pushPath(p[11], "N");
		

		Path g[] = new Path[Constants.MAXGATES];// gates o compuertas
		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		
		g[0].addCell(0.85,0.1);
		g[0].addCell(0.9,-0.2);
		g[0].addCell(1,-0.5);
		
		g[1].addCell(0.85,0.1);
		g[1].addCell(0.85,-0.5);
		
		pushGate(g[0], "B",0,2);
		pushGate(g[1], "B",0,2);
		
		
		g[2].addCell(0,-1.0);
		g[2].addCell(-0.5,-1);
		g[2].addCell(-0.5,-0.5);
		
		g[3].addCell(0,-1.0);
		g[3].addCell(-0.85,-1.0);
		g[3].addCell(-0.85,-0.5);
		
		pushGate(g[2], "A",0,2);
		pushGate(g[3], "A",0,2);
		
		
		
		
		
		
				
		
		
		getPathById("G").setNextPath(getPathById("H").num);
		getPathById("H").setNextGate(getGateById("B",0).num);
		getPathById("H").setNextGate(getGateById("B",1).num);
		
		getPathById("E").setNextPath(getPathById("N").num);
		getPathById("I").setNextPath(getPathById("G").num);
		
		getPathById("N").setNextGate(getGateById("A",0).num);
		getPathById("N").setNextGate(getGateById("A",1).num);
		
		getGateById("A",0).setNextPath(getPathById("P").num);
		getGateById("A",1).setNextPath(getPathById("F").num);
		
		getPathById("P").setNextPath(getPathById("D").num);
		getPathById("D").setNextPath(getPathById("K").num);
		getPathById("K").setNextPath(getPathById("J").num);
		getPathById("J").setNextPath(getPathById("C").num);
		
		
		getPathById("F").setNextPath(getPathById("M").num);
		getPathById("M").setNextPath(getPathById("H").num);
		getPathById("C").setNextPath(Constants.FINAL_CIRCUIT);
		
		getGateById("B",0).setNextPath(getPathById("I").num);
		getGateById("B",1).setNextPath(getPathById("E").num);
		
		
	}
	private void loadLoop() {
		Path[] p = new Path[Constants.MAXPATHS];

		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		p[0].addStart(1, 0.3);
		p[0].addCell(0.6, 0.3);
		pushPath(p[0], "A");

		p[1].addCell(0.6, 0.3);
		p[1].addCell(-0.3, 0.3);
		pushPath(p[1], "B");
				
		p[2].addCell(0.6, 0.7);
		p[2].addCell(1, 0.7);
		p[2].addCell(1, 0.6);
		p[2].addCell(1, 0.3);
		p[2].addCell(0.6, 0.3);
		pushPath(p[2], "J");
		
		p[3].addCell(0, 0.7);
		p[3].addCell(0.6, 0.7);
		pushPath(p[3], "K");
		
		p[4].addCell(-0.3, 0);
		p[4].addCell(0,  0);
		p[4].addCell(0, -0.1);
		p[4].addCell(0, -0.3);
		p[4].addCell(-0.3, -0.3);
		pushPath(p[4], "F");
		
		p[5].addCell(-0.3, -1);
		p[5].addCell(0, -1);
		pushPath(p[5], "I");
		
		p[6].addCell(-0.3, 0.7);
		p[6].addCell(0, 0.7);
		pushPath(p[6], "M");
		
		p[7].addCell(0.6, -1);
		p[7].addEnd(1, -1);
		pushPath(p[7], "O");

		p[8].addCell(-0.7,0.3);
		p[8].addKill(-1, 0.3);
		pushPath(p[8], "E");
		
		p[9].addCell(-0.7,-0.3);
		p[9].addKill(-1, -0.3);
		pushPath(p[9], "H");
		
		
		
		
		Path g[] = new Path[Constants.MAXGATES];// gates o compuertas
		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		g[0].addCell(-0.3, 0.3);
		g[0].addCell(-0.7, 0.3);
		g[0].addCell(-0.7, 0.6);
		g[0].addCell(-0.7, 0.7);
		g[0].addCell(-0.3, 0.7);

		
		g[1].addCell(-0.3,0.3);
		g[1].addCell(-0.7,0.3);
		g[1].addCell(-0.7,0.15);
		g[1].addCell(-0.7,0.0);
		g[1].addCell(-0.3,0);

		g[2].addCell(-0.3, 0.3);
		g[2].addCell(-0.7, 0.3);
	

		pushGate(g[0], "C", 0, 3);// els gates amb el mateix nom
		pushGate(g[1], "C", 0, 3);// els gates amb el mateix nom
		pushGate(g[2], "C", 0, 3);// els gates amb el mateix nom
		
		
		g[3].addCell(-0.3, -0.3);
		g[3].addCell(-0.7, -0.3);
		
		g[4].addCell(-0.3,-0.3);
		g[4].addCell(-0.7,-0.3);
		g[4].addCell(-0.7,-0.6);
		g[4].addCell(-0.7,-1);
		g[4].addCell(-0.3,-1);

		pushGate(g[3], "G", 0, 2);// els gates amb el mateix nom
		pushGate(g[4], "G", 0, 2);// els gates amb el mateix nom
		
		
		g[5].addCell(0, -1);
		g[5].addCell(1, -1);
		g[5].addCell(1,-0.3 );
		g[5].addCell(1, 0.3);
		g[5].addCell(0.6, 0.3);
		
		g[6].addCell(0,-1);
		g[6].addCell(0.6, -1);

		pushGate(g[6], "N",0,2);
		pushGate(g[5], "N",0,2);
		
		
		
		getPathById("A").setNextPath(getPathById("B").num);
		
		getPathById("B").setNextGate(getGateById("C",0).num);
		getPathById("B").setNextGate(getGateById("C",1).num);
		getPathById("B").setNextGate(getGateById("C",2).num);
		
		
		getGateById("C", 0).setNextPath(getPathById("M").num);
		getGateById("C", 1).setNextPath(getPathById("F").num);
		getGateById("C", 2).setNextPath(getPathById("E").num);
		
		getPathById("E").setNextPath(getPathById("A").num);//Si mor torna a començar
		getPathById("H").setNextPath(getPathById("A").num);//Si mor torna a començar
		
		getPathById("M").setNextPath(getPathById("K").num);
		getPathById("K").setNextPath(getPathById("J").num);
		getPathById("J").setNextPath(getPathById("B").num);
		
		getPathById("F").setNextGate(getGateById("G",0).num);
		getPathById("F").setNextGate(getGateById("G",1).num);
		
		getGateById("G", 0).setNextPath(getPathById("H").num);
		getGateById("G", 1).setNextPath(getPathById("I").num);
		
		getPathById("I").setNextGate(getGateById("N",0).num);
		getPathById("I").setNextGate(getGateById("N",1).num);
		
		
		getPathById("O").setNextPath(Constants.FINAL_CIRCUIT);
		
		
		
		getGateById("N",0).setNextPath(getPathById("O").num);
		getGateById("N",1).setNextPath(getPathById("B").num);
		
		
		
		
		
		
		
		
		
		
		
		
	}


	private void loadOne() {
		Path[] p = new Path[Constants.MAXPATHS];

		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		p[0].addStart(-0.9, 0);
		p[0].addCell(-0.4, -0.0);
		pushPath(p[0], "a");

		p[1].addCell(-0.4, -0.0);
		p[1].addCell(-0.1, -0.0);
		pushPath(p[1], "b");

		p[2].addCell(0.0, 0.2);
		p[2].addCell(0.5, 0.2);
		p[2].addCell(0.5, 0.2);
		p[2].addCell(0.7, 0.4);
		p[2].addCell(0.5, 0.6);
		p[2].addCell(0.0, 0.6);
		p[2].addCell(-1, 0.6);
		p[2].addCell(-0.4, 0.0);
		p[2].addCell(-0.4, 0.0);

		pushPath(p[2], "c");

		p[3].addCell(0.0, -0.2);
		p[3].addCell(0.5, -0.2);
		p[3].addCell(0.5, 0.0);
		pushPath(p[3], "d");

		p[4].addCell(0.7, 0);
		p[4].addEnd(0.9, 0);
		pushPath(p[4], "f");

		p[5].addCell(0.65, 0.15);
		p[5].addKill(0.9, 0.6);
		pushPath(p[5], "e");

		Path g[] = new Path[Constants.MAXGATES];// gates o compuertas
		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		g[0].addCell(-0.1, -0.0);
		g[0].addCell(0.0, 0.2);

		g[1].addCell(-0.1, -0.0);
		g[1].addCell(0.0, -0.2);

		pushGate(g[0], "gA", 0, 2);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[1], "gA", 0, 2);

		g[2].addCell(0.5, 0.0);
		g[2].addCell(0.7, 0);

		g[3].addCell(0.5, 0.0);
		g[3].addCell(0.65, 0.15);

		pushGate(g[2], "gB", 0, 2);
		pushGate(g[3], "gB", 0, 2);

		getPathById("a").setNextPath(getPathById("b").num);// a->b

		getPathById("b").setNextGate(getGateById("gA", 0).num);// b->gA 1
		getPathById("b").setNextGate(getGateById("gA", 1).num);// b->gA 2

		getGateById("gA", 0).setNextPath(getPathById("c").num);// gA,0 -> c
		getPathById("c").setNextPath(getPathById("b").num);// c->b

		getPathById("e").setNextPath(getPathById("a").num);// si muerte vuelve a
															// empezar

		getGateById("gA", 1).setNextPath(getPathById("d").num);// b->c

		getPathById("d").setNextGate(getGateById("gB", 0).num);
		getGateById("gB", 0).setNextPath(getPathById("f").num);// gA,0 -> c

		getPathById("d").setNextGate(getGateById("gB", 1).num);
		getGateById("gB", 1).setNextPath(getPathById("e").num);// gA,0 -> c

		getPathById("f").setNextPath(Constants.FINAL_CIRCUIT);// d->a
	}

	private void loadTwo() {
		Path[] p = new Path[Constants.MAXPATHS];

		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		p[0].addStart(0.9, 0);
		p[0].addCell(0.6, 0);
		pushPath(p[0], "a");

		p[1].addCell(0.3, 0.25);
		p[1].addCell(0, 0.55);
		p[1].addCell(0.35, 0.8);
		p[1].addCell(0.8, 0.65);
		p[1].addCell(0.8, 0.45);
		p[1].addCell(0.245, 0.45);
		pushPath(p[1], "b");

		p[2].addCell(0.4, 0.0);
		p[2].addCell(0.1, 0.0);
		pushPath(p[2], "c");

		p[3].addCell(0.1, 0.0);
		p[3].addCell(-0.2, 0.0);
		pushPath(p[3], "d");

		p[4].addCell(-0.2, 0.0);
		p[4].addCell(-0.35, 0.0);
		pushPath(p[4], "e");

		p[5].addCell(0.155, 0.45);
		p[5].addCell(-0.2, 0.45);
		pushPath(p[5], "f");

		p[6].addCell(-0.2, 0.45);
		p[6].addCell(-0.45, 0.45);
		pushPath(p[6], "g");

		p[7].addCell(-0.8, 0.45);
		p[7].addKill(-0.9, 0.45);// <--------MUERTE SI LLEGA AQUI
		pushPath(p[7], "h");

		p[8].addCell(-0.2, 0.2);
		p[8].addCell(0, 0.3);
		p[8].addCell(-0.2, 0.45);
		pushPath(p[8], "i");

		p[9].addCell(-0.85, 0.2);
		p[9].addCell(-0.85, -0.15);
		p[9].addCell(-0.65, -0.4);
		pushPath(p[9], "j");

		p[10].addCell(-0.65, -0.4);
		p[10].addCell(-0.55, -0.5);
		p[10].addCell(-0.4, -0.55);
		pushPath(p[10], "k");

		p[11].addCell(-0.5, 0);
		p[11].addCell(-0.65, 0);
		p[11].addCell(-0.65, -0.4);
		pushPath(p[11], "l");

		p[12].addCell(-0.2, -0.45);
		p[12].addCell(0, -0.25);
		p[12].addCell(-0.2, 0);
		pushPath(p[12], "m");

		p[13].addCell(-0.3, -0.75);
		p[13].addCell(-0.35, -0.85);
		p[13].addCell(-0.6, -0.85);
		pushPath(p[13], "n");

		p[14].addCell(0.15, -0.2);
		p[14].addCell(0.2, -0.1);
		p[14].addCell(0.1, 0);
		pushPath(p[14], "o");

		p[15].addCell(0.35, -0.3);
		p[15].addCell(0.3, -0.35);
		pushPath(p[15], "p");

		p[16].addCell(0.4, -0.25);
		p[16].addCell(0.35, -0.3);
		pushPath(p[16], "q");

		p[17].addCell(0.3, -0.65);
		p[17].addCell(0.55, -0.2);
		p[17].addCell(0.35, -0.3);
		pushPath(p[17], "r");

		p[18].addCell(-0.55, -0.95);
		p[18].addCell(0.2, -0.9);
		p[18].addCell(0.3, -0.65);
		pushPath(p[18], "s");

		p[19].addCell(-0.7, -0.85);
		p[19].addEnd(-0.9, -0.85);// <<----------------- saved si llega aqui
		pushPath(p[19], "t");

		Path[] g = new Path[Constants.MAXPATHS];// gates o compuertas

		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		g[0].addCell(0.6, 0.0);
		g[0].addCell(0.3, 0.25);

		g[1].addCell(0.6, 0.0);
		g[1].addCell(0.4, 0.0);

		g[2].addCell(0.6, 0.0);
		g[2].addCell(0.4, -0.25);

		pushGate(g[0], "gA", 1, 3);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[1], "gA", 1, 3);
		pushGate(g[2], "gA", 1, 3);

		g[3].addCell(0.3, -0.35);
		g[3].addCell(0.1, -0.45);
		g[3].addCell(0.15, -0.2);

		g[4].addCell(0.3, -0.35);
		g[4].addCell(-0.1, -0.85);
		g[4].addCell(0.3, -0.65);

		pushGate(g[3], "gP", 0, 2);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[4], "gP", 0, 2);

		g[5].addCell(-0.45, 0.45);
		g[5].addCell(-0.8, 0.45);

		g[6].addCell(-0.45, 0.45);
		g[6].addCell(-0.85, 0.45);
		g[6].addCell(-0.85, 0.2);

		pushGate(g[5], "gG", 0, 2);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[6], "gG", 0, 2);

		g[7].addCell(-0.35, 0.0);
		g[7].addCell(-0.55, 0.0);
		g[7].addCell(-0.2, 0.2);

		g[8].addCell(-0.35, 0.0);
		g[8].addCell(-0.5, 0);

		pushGate(g[7], "gE", 0, 2);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[8], "gE", 0, 2);

		g[9].addCell(-0.4, -0.55);
		g[9].addCell(-0.3, -0.55);
		g[9].addCell(-0.2, -0.45);

		g[10].addCell(-0.4, -0.55);
		g[10].addCell(-0.25, -0.6);
		g[10].addCell(-0.3, -0.75);

		pushGate(g[9], "gK", 0, 2);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[10], "gK", 0, 2);

		g[11].addCell(-0.6, -0.85);
		g[11].addCell(-0.7, -0.85);

		g[12].addCell(-0.6, -0.85);
		g[12].addCell(-0.7, -0.85);
		g[12].addCell(-0.7, -0.95);
		g[12].addCell(-0.55, -0.95);

		pushGate(g[11], "gN", 0, 2);// els gates amb el mateix nom
									// s'Integerercanvien al premer una tecla
		pushGate(g[12], "gN", 0, 2);

		// followers (andeva!)

		getPathById("a").setNextGate(getGateById("gA", 1).num);
		getPathById("a").setNextGate(getGateById("gA", 2).num);
		getPathById("a").setNextGate(getGateById("gA", 0).num);

		getGateById("gA", 0).setNextPath(getPathById("b").num);
		getPathById("b").setNextPath(getPathById("f").num);
		getPathById("f").setNextPath(getPathById("g").num);

		getPathById("g").setNextGate(getGateById("gG", 0).num);
		getPathById("g").setNextGate(getGateById("gG", 1).num);

		getGateById("gG", 0).setNextPath(getPathById("h").num);
		getPathById("h").setNextPath(getPathById("a").num); // <<<<<<<<<<<<<<<
															// muerte
		getGateById("gG", 1).setNextPath(getPathById("j").num);
		getPathById("j").setNextPath(getPathById("k").num);

		getPathById("k").setNextGate(getGateById("gK", 0).num);
		getPathById("k").setNextGate(getGateById("gK", 1).num);
		getGateById("gK", 0).setNextPath(getPathById("m").num);
		getPathById("m").setNextPath(getPathById("e").num);
		getGateById("gK", 1).setNextPath(getPathById("n").num);
		getPathById("n").setNextGate(getGateById("gN", 0).num);
		getPathById("n").setNextGate(getGateById("gN", 1).num);

		getGateById("gN", 0).setNextPath(getPathById("t").num);
		getGateById("gN", 1).setNextPath(getPathById("s").num);

		getPathById("s").setNextPath(getPathById("r").num);

		getGateById("gA", 1).setNextPath(getPathById("c").num);
		getPathById("c").setNextPath(getPathById("d").num);
		getPathById("d").setNextPath(getPathById("e").num);

		getPathById("e").setNextGate(getGateById("gE", 0).num);
		getPathById("e").setNextGate(getGateById("gE", 1).num);

		getGateById("gE", 0).setNextPath(getPathById("i").num);
		getPathById("i").setNextPath(getPathById("g").num);

		getGateById("gE", 1).setNextPath(getPathById("l").num);
		getPathById("l").setNextPath(getPathById("k").num);

		getGateById("gA", 2).setNextPath(getPathById("q").num);

		getPathById("q").setNextPath(getPathById("p").num);

		getPathById("p").setNextGate(getGateById("gP", 0).num);
		getPathById("p").setNextGate(getGateById("gP", 1).num);

		getGateById("gP", 1).setNextPath(getPathById("r").num);
		getPathById("r").setNextPath(getPathById("p").num);
		getGateById("gP", 0).setNextPath(getPathById("o").num);
		getPathById("o").setNextPath(getPathById("d").num);

		getPathById("t").setNextPath(Constants.FINAL_CIRCUIT);// d->a
	}

	private void loadTree() {
		Path[] p = new Path[Constants.MAXPATHS];
		for (int m = 0; m < Constants.MAXPATHS; m++)
			p[m] = new Path();

		p[0].addStart(0, 0.9);
		p[0].addCell(0, 0.7);
		pushPath(p[0], "a");

		p[1].addCell(0, 0.7);
		p[1].addCell(0, 0.55);
		pushPath(p[1], "b");

		p[2].addCell(-0.25, 0.4);
		p[2].addCell(-0.45, 0.35);
		pushPath(p[2], "c");

		p[3].addCell(0, 0.4);
		p[3].addCell(-0.45, -0.2);
		pushPath(p[3], "d");

		p[4].addCell(0.25, 0.4);
		p[4].addCell(0.35, 0.45);
		p[4].addCell(0.35, 0.7);
		pushPath(p[4], "e");

		p[5].addCell(0.5, 0.85);
		p[5].addCell(0.95, 0.85);
		pushPath(p[5], "f");

		p[6].addCell(0.5, 0.6);
		p[6].addCell(0.5, 0.4);
		pushPath(p[6], "g");

		p[7].addCell(0.5, 0.4);
		p[7].addCell(0.5, 0.25);
		pushPath(p[7], "h");

		p[8].addCell(0.7, 0.4);
		p[8].addCell(0.6, 0.55);
		p[8].addCell(0.5, 0.4);
		pushPath(p[8], "i");

		p[9].addCell(0.7, 0.25);
		p[9].addCell(0.7, 0.4);
		pushPath(p[9], "j");

		p[10].addCell(0.8, 0.25);
		p[10].addCell(0.7, 0.4);
		pushPath(p[10], "k");

		p[11].addCell(0.9, 0.3);
		p[11].addCell(0.9, 0.85);
		p[11].addCell(0.95, 0.85);
		pushPath(p[11], "l");

		p[12].addCell(0.5, 0);
		p[12].addCell(0.55, -0.7);
		pushPath(p[12], "m");

		// ///

		p[13].addCell(0.25, -0.25);
		p[13].addCell(0.55, -0.7);
		pushPath(p[13], "n");

		p[14].addCell(0, -0.3);
		p[14].addCell(-0.3, -0.75);
		p[14].addCell(-0.55, -0.4);
		pushPath(p[14], "ny");

		p[15].addCell(-0.65, -0.25);
		p[15].addCell(-0.8, 0.15);
		pushPath(p[15], "o");

		p[16].addCell(-0.8, -0.35);
		p[16].addCell(-0.95, -0.2);
		p[16].addCell(-0.8, 0.15);
		pushPath(p[16], "p");

		p[17].addCell(-0.8, -0.5);
		p[17].addKill(-0.95, -0.55); // /////// <<<<<<<< one kill
		pushPath(p[17], "q");

		p[18].addCell(0.4, 0.15);
		p[18].addCell(0.2, 0);
		pushPath(p[18], "r");

		p[19].addCell(-0.8, 0.3);
		p[19].addCell(-0.7, 0.7);
		p[19].addCell(-0.4, 0.85);
		pushPath(p[19], "s");

		p[20].addCell(-0.9, 0.85);
		p[20].addCell(-0.4, 0.85);
		pushPath(p[20], "t");

		p[21].addCell(-0.4, 0.85);
		p[21].addCell(-0.1, 0.85);
		p[21].addCell(0, 0.7);
		pushPath(p[21], "u");

		p[22].addCell(-0.45, 0.15);
		p[22].addCell(-0.45, -0.2);
		pushPath(p[22], "v");

		p[23].addCell(-0.45, -0.2);
		p[23].addCell(-0.7, -0.4);
		pushPath(p[23], "w");

		p[24].addCell(-0.6, 0.3);
		p[24].addKill(-1, 0.15); // ////////////// dead
		pushPath(p[24], "x");

		p[25].addCell(0.85, -0.6);
		p[25].addCell(0.9, 0);
		pushPath(p[25], "y");

		p[26].addCell(0.6, -0.9);
		p[26].addEnd(0.35, -0.9);
		pushPath(p[26], "z");

		Path g[] = new Path[Constants.MAXGATES];// gates o compuertas
		for (int m = 0; m < Constants.MAXGATES; m++)
			g[m] = new Path();

		g[0].addCell(0, 0.55);
		g[0].addCell(-0.25, 0.4);

		g[1].addCell(0, 0.55);
		g[1].addCell(0, 0.4);

		g[2].addCell(0, 0.55);
		g[2].addCell(0.1, 0.4);
		g[2].addCell(0.25, 0.4);

		pushGate(g[0], "gB", 0, 3);// els gates amb el mateix nom s'intercanvien
									// al premer una tecla
		pushGate(g[1], "gB", 0, 3);
		pushGate(g[2], "gB", 0, 3);

		g[3].addCell(-0.45, 0.35);
		g[3].addCell(-0.6, 0.3);

		g[4].addCell(-0.45, 0.35);
		g[4].addCell(-0.45, 0.15);

		pushGate(g[3], "gC", 0, 2);
		pushGate(g[4], "gC", 0, 2);

		g[5].addCell(0.55, -0.7);
		g[5].addCell(0.8, -0.75);
		g[5].addCell(0.85, -0.6);

		g[6].addCell(0.55, -0.7);
		g[6].addCell(0.75, -0.8);
		g[6].addCell(0.6, -0.9);

		pushGate(g[5], "gN", 0, 2);
		pushGate(g[6], "gN", 0, 2);

		g[7].addCell(0.5, 0.25);
		g[7].addCell(0.4, 0.15);

		g[8].addCell(0.5, 0.25);
		g[8].addCell(0.5, 0);

		g[9].addCell(0.5, 0.25);
		g[9].addCell(0.6, 0.05);
		g[9].addCell(0.7, 0.25);

		pushGate(g[7], "gH", 0, 3);
		pushGate(g[8], "gH", 0, 3);
		pushGate(g[9], "gH", 0, 3);

		g[10].addCell(0.2, 0);
		g[10].addCell(0, -0.3);

		g[11].addCell(0.2, 0);
		g[11].addCell(0.15, -0.15);
		g[11].addCell(0.25, -0.25);

		pushGate(g[10], "gR", 0, 2);
		pushGate(g[11], "gR", 0, 2);

		g[12].addCell(0.35, 0.7);
		g[12].addCell(0.35, 0.85);
		g[12].addCell(0.5, 0.85);

		g[13].addCell(0.35, 0.7);
		g[13].addCell(0.5, 0.7);
		g[13].addCell(0.5, 0.6);

		pushGate(g[12], "gE", 0, 2);
		pushGate(g[13], "gE", 0, 2);

		g[14].addCell(-0.7, -0.4);
		g[14].addCell(-0.8, -0.35);

		g[15].addCell(-0.7, -0.4);
		g[15].addCell(-0.8, -0.5);

		pushGate(g[14], "gW", 0, 2);
		pushGate(g[15], "gW", 0, 2);

		g[16].addCell(0.9, 0);
		g[16].addCell(0.8, 0.25);

		g[17].addCell(0.9, 0);
		g[17].addCell(0.9, 0.3);

		pushGate(g[16], "gY", 0, 2);
		pushGate(g[17], "gY", 0, 2);

		getPathById("a").setNextPath(getPathById("b").num);// a.b
		getPathById("b").setNextGate(getGateById("gB", 0).num);
		getGateById("gB", 0).setNextPath(getPathById("c").num);

		getPathById("c").setNextGate(getGateById("gC", 0).num);
		getGateById("gC", 0).setNextPath(getPathById("x").num);
		getPathById("x").setNextPath(getPathById("a").num);//

		getPathById("c").setNextGate(getGateById("gC", 1).num);
		getGateById("gC", 1).setNextPath(getPathById("v").num);
		getPathById("v").setNextPath(getPathById("w").num);//
		getPathById("w").setNextGate(getGateById("gW", 0).num);
		getGateById("gW", 0).setNextPath(getPathById("p").num);
		getPathById("p").setNextPath(getPathById("s").num);//
		getPathById("s").setNextPath(getPathById("u").num);//
		getPathById("u").setNextPath(getPathById("b").num);//

		getPathById("w").setNextGate(getGateById("gW", 1).num);
		getGateById("gW", 1).setNextPath(getPathById("q").num);
		getPathById("q").setNextPath(getPathById("a").num);//

		getPathById("b").setNextGate(getGateById("gB", 1).num);
		getGateById("gB", 1).setNextPath(getPathById("d").num);
		getPathById("d").setNextPath(getPathById("w").num);//

		getPathById("b").setNextGate(getGateById("gB", 2).num);
		getGateById("gB", 2).setNextPath(getPathById("e").num);

		getPathById("e").setNextGate(getGateById("gE", 0).num);
		getGateById("gE", 0).setNextPath(getPathById("f").num);
		getPathById("f").setNextPath(getPathById("t").num);//

		getPathById("t").setNextPath(getPathById("u").num);//
		getPathById("u").setNextPath(getPathById("b").num);//

		getPathById("e").setNextGate(getGateById("gE", 1).num);

		getGateById("gE", 1).setNextPath(getPathById("g").num);
		getPathById("g").setNextPath(getPathById("h").num);//

		getPathById("h").setNextGate(getGateById("gH", 0).num);
		getGateById("gH", 0).setNextPath(getPathById("r").num);

		getPathById("r").setNextGate(getGateById("gR", 0).num);

		getGateById("gR", 0).setNextPath(getPathById("ny").num);
		getPathById("ny").setNextPath(getPathById("o").num);//
		getPathById("o").setNextPath(getPathById("s").num);//

		getGateById("gR", 1).setNextPath(getPathById("n").num);

		getPathById("m").setNextGate(getGateById("gN", 0).num);
		getPathById("m").setNextGate(getGateById("gN", 1).num);

		getPathById("n").setNextGate(getGateById("gN", 0).num);
		getGateById("gN", 0).setNextPath(getPathById("y").num);

		getPathById("n").setNextGate(getGateById("gN", 1).num);
		getGateById("gN", 1).setNextPath(getPathById("z").num);

		getPathById("y").setNextGate(getGateById("gY", 0).num);
		getGateById("gY", 0).setNextPath(getPathById("k").num);
		getPathById("k").setNextPath(getPathById("i").num);//
		getPathById("i").setNextPath(getPathById("h").num);//

		getGateById("gY", 1).setNextPath(getPathById("l").num);
		getPathById("l").setNextPath(getPathById("t").num);//

		getGateById("gH", 2).setNextPath(getPathById("j").num);
		getPathById("j").setNextPath(getPathById("i").num);//

		getPathById("y").setNextGate(getGateById("gY", 1).num);

		getPathById("r").setNextGate(getGateById("gR", 1).num);

		getPathById("h").setNextGate(getGateById("gH", 1).num);
		getGateById("gH", 1).setNextPath(getPathById("m").num);

		getPathById("h").setNextGate(getGateById("gH", 2).num);

		getPathById("z").setNextPath(Constants.FINAL_CIRCUIT);// d.a

	}

	private void pushPath(Path p, String id) {
		p.num = (Integer) Paths.size();
		p.setId(id);
		Paths.add(p);
	}

	private void pushGate(Path p, String id, Integer active, Integer maxgates) {
		p.num = (Integer) Gates.size();
		p.setActiveGate(active);
		p.setId(id);
		p.setNumGroup(maxgates);
		Gates.add(p);

	}

}
