package teste;

import java.util.ArrayList;

public class CoordenateMap {
	private int [][] map;

	public CoordenateMap(int[][] map) {
		this.setMap(map);

	}
	
	public ArrayList<String> replaceCoords(){
		ArrayList<String> coordsCells = new ArrayList<String>();
		for(int i =0;i< map.length;i++) {
			for(int j =0 ; j<map[0].length;j++) {
				if(this.map[i][j] < 1) {
					coordsCells.add(i+","+j);
				}
				
			}
		}
		return coordsCells;
	}
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
}
