package teste;

import java.util.ArrayList;


public class RobiosMove implements Robios {
	private ArrayList<String> map = new ArrayList<String>();
	private ArrayList<String> walk = new ArrayList<String>();
	private int x,y,currentXPosition,currentYPosition;

	public RobiosMove(ArrayList<String> map,int x,int y) {
		this.setMap(map);
		this.setX(x);
		this.setY(y);
		this.setCurrentYPosition(y);
		this.setCurrentXPosition(x);
		this.walk.add(x+","+y);
		if(this.map.contains(x+","+y))
		{
			while((this.getCurrentXPosition() != 0)|(this.getCurrentYPosition() != 0)) {
				System.out.println(this.getCurrentXPosition()+","+this.getCurrentYPosition());
				relativeMove(this.getCurrentXPosition(),this.getCurrentYPosition());
			}
			System.out.println(this.getCurrentXPosition()+","+this.getCurrentYPosition());
		}
	}
	
public boolean [] posibility() {
		
		boolean[] position = {false,false,false,false};
		if(this.map.contains((this.getCurrentXPosition()+1)+","+this.getCurrentYPosition())) { // baixo
			position[0] = true;
		}
		if(this.map.contains((this.getCurrentXPosition()-1)+","+this.getCurrentYPosition())) { // cima
			position[1] = true;
		}
		if(this.map.contains(this.getCurrentXPosition()+","+(this.getCurrentYPosition()+1))) {	//esquerda
			position[2] = true;
		}
		if(this.map.contains(this.getCurrentXPosition()+","+(this.getCurrentYPosition()-1))) { // direita
			position[3] = true;
		}
		
		return position;
	}
	
	public void relativeMove(int x,int y) {
		boolean [] possible = posibility();
		boolean attemps = false;
		for(int i = 0 ; i<4;i++) {
			if(possible[i]) {
				attemps = true;
			}
		}
		if(attemps) {
			LineaMoviment mv = new LineaMoviment(possible,x,y);
			int [] moviment = mv.move();
			this.map.remove(this.map.indexOf(x+","+y));
			this.setX(x);
			this.setY(y);
			
			this.setCurrentXPosition(moviment[0]);
			this.setCurrentYPosition(moviment[1]);			
		}else if((this.getX() != this.getCurrentXPosition()) | (this.getY()!= this.getCurrentYPosition())) {
			this.map.remove(this.map.indexOf(this.getCurrentXPosition()+","+this.getCurrentYPosition()));
			this.map.add(this.getX()+","+this.getY());
			this.setCurrentXPosition(this.getX());
			this.setCurrentYPosition(this.getY());
		}
		this.walk.add(this.getCurrentXPosition()+","+this.getCurrentYPosition());
	}
	
	


	public ArrayList<String> getMap() {
		return map;
	}
	
	public void setMap(ArrayList<String> map) {
		this.map = map;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getCurrentXPosition() {
		// TODO Auto-generated method stub
		return currentXPosition;
	}

	public int getCurrentYPosition() {
		// TODO Auto-generated method stub
		return currentYPosition;;
	}
	
	public void setCurrentXPosition(int currentXPosition) {
		this.currentXPosition = currentXPosition;
	}

	public void setCurrentYPosition(int currentYPosition) {
		this.currentYPosition = currentYPosition;
	}

}

