package teste;

public class LineaMoviment {
	private int x,y;
	private boolean[] positions;
	public LineaMoviment(boolean[] positions,int x,int y) {
		this.positions = positions;
		this.x=x;
		this.y=y;
	}
	public int[] move() {
		int[] coord = {0,0};
		boolean [] position = positions;
		if(x>y) {
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
			if(position[3]) {
				y--;
			}else if(position[1]) {
				x--;
			}else if(position[0]) {
				x++;
			}else if(position[2]) {
				y++;
			}
			
		}
		coord[0]=x ;
		coord[1]=y;
		return coord;

	}
}
