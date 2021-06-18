package teste;

import java.util.ArrayList;
import java.util.HashMap;

public class RobiosMove implements Robios {
	
	private int currentXPosition;
	private int currentYPosition;
	private int[][] map;
	private int ind_hash=0;
	
	private HashMap<Integer,Integer> coordX = new HashMap<Integer,Integer>();
	private HashMap<Integer,Integer> coordY = new HashMap<Integer,Integer>();
	

	// caso queira que o robios saia do ponto zero para um ponto especifico;
	private int endPointX;
	private int endPointY;
	
	private int[][] positions;
	private boolean verdade = true;

	public RobiosMove(int[][]map,int x,int y) {
		
		this.currentXPosition = x;
		this.currentYPosition = y;		
		this.map = map;
		this.coordX.put(ind_hash,x);
		this.coordY.put(ind_hash,y);
		this.endPointX = 0;
		this.endPointY = 0;
		System.out.println(this.getCurrentXPosition()+" : "+ this.getCurrentYPosition());
		while(true) {
			if((this.getCurrentXPosition() == this.endPointX) && (this.getCurrentYPosition() == this.endPointY)) {
				break;
			}			
			move(this.currentXPosition,this.currentYPosition,posibility());			
			
		}

		CoordinateBD connectBD = new CoordinateBD(coordX, coordY);

		
	}
	
	public boolean [] posibility() throws ArrayIndexOutOfBoundsException{
		boolean[] positions = {false,false,false,false};
		if(!conteinCoords(this.getCurrentXPosition(), this.getCurrentYPosition()))
			throw new ArrayIndexOutOfBoundsException();
		
		if(this.map.length-1 > this.getCurrentXPosition()) {
			if(map[this.getCurrentXPosition()+1][this.getCurrentYPosition()] != 1) { //baixo
				positions[0]=true;
			}
		}
		if(getCurrentXPosition()> 0) {
			if(map[this.getCurrentXPosition()-1][this.getCurrentYPosition()] != 1) { // cima
				positions[1]=true;
			}
		}
		
		if(this.map[0].length-1 > this.getCurrentYPosition()) {
			if(map[this.getCurrentXPosition()][this.getCurrentYPosition()+1] != 1) { // direita
				positions[2] = true;
			}
		}
		if(this.getCurrentYPosition()>0) {
			if(map[this.getCurrentXPosition()][this.getCurrentYPosition()-1] != 1 ) { // esquerda
				positions[3] = true;
			}			
		}
		
		return positions;
	}
	
	public void move(int x, int y,boolean[] positions) {
		boolean [] position = positions;
		if(x>y) {
			if((x>=this.endPointX)&&(y>=this.endPointY)) {
				if(position[1]) {
					x--;
				}else if(position[3]) {
					y--;
				}else if(position[0]) {
					x++;
				}else if(position[2]) {
					y++;
				}			
			}else {
				if(position[0]) {
					x++;
				}else if(position[2]) {
					y++;
				}else if(position[1]) {
					x--;
				}else if(position[3]) {
					y--;
				}
			}

		}else {
			if((y>=this.endPointY)&&(x>=this.endPointX)) {
				if(position[3]) {
					y--;
				}else if(position[1]) {
					x--;
				}else if(position[0]) {
					x++;
				}else if(position[2]) {
					y++;
				}
			}else {
				if(position[2]) {
					y++;
				}else if(position[0]) {
					x++;
				}else if(position[3]) {
					y--;
				}else if(position[1]) {
					x--;
				}
			}

		}

		relativeMove(x, y);
	}

	public void relativeMove(int x, int y) {

		System.out.println(this.getCurrentXPosition()+" : "+ this.getCurrentYPosition());
		this.setCurrentXPosition(x);
		this.setCurrentYPosition(y);
		for(int i=0;i < this.coordX.size();i++) {
				if((this.coordX.get(i) == x) && (this.coordY.get(i) == y)) {
					boolean[] ex = posibility();
					if(this.coordX.containsKey(i+1)) {
						if(this.coordX.get(i+1)>x) {
							ex[0] = false;
						}else if(this.coordX.get(i+1)<x) {
							ex[1]= false;
						}else if(this.coordY.get(i+1)>y) {
							ex[2]=false;
						}else if(this.coordY.get(i+1)<y) {
							ex[3]=false;
						}
						move(this.coordX.get(i),this.coordY.get(i),ex);		
					}
					
				}
			}
		this.ind_hash++;
		this.coordX.put(this.ind_hash,x);
		this.coordY.put(this.ind_hash,y);

		
	}

	
	  public boolean coordsX(int x) {
		  if(map.length < x) {
			  return false;
			  
		  }
		  return true;
	  }
	  public boolean coordsY(int y) {
		  if(map[0].length < y) {
			  return false;
		  }
		  return true;
	  }
	  
	  public boolean conteinCoords(int x,int y) {
		  if(map[x][y] != 1) {
			  if(coordsX(x) && coordsY(y)) {
				  return true;
			  }			  
		  }

		  return false;
	  }


	
	
	
	public int getCurrentXPosition() {
		return this.currentXPosition;
	}
	public void setCurrentXPosition(int x) {
		this.currentXPosition = x;
	}


	public int getCurrentYPosition() {
		return this.currentYPosition;
	}
	
	public void setCurrentYPosition(int y) {
		this.currentYPosition =y;
	}





}
