package teste;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Data mapa = new Data();
		int x=3;
		int y=0;
		int pos_ant_x=0;
		int pos_ant_y=0;
		boolean caminhos_l = true;
		int passos=0;
		Scanner scan = new Scanner(System.in);
		if(x>3 && y>8) 
		{
			System.out.println("Voado em espaço não mapeado");
		}
		if(mapa.map[x][y] == 1)
		{
			System.out.println("O robo bateu");
			System.exit(0);
		}
		do{

			System.out.println(" ");
			if((x>1) && (y>1)) {

				if(caminhos_l) {
					if(x>y) {
						if(mapa.map[x-1][y] != 1) {
							x--;
						}else {
							caminhos_l=false;
						}

					}else{
						if(mapa.map[x][y-1] != 1) {
							y--;
						}else {
							caminhos_l = false;
						}
					};	
				}else {
					
					if(mapa.map[x-1][y] != 1) {							
						if(mapa.map[x-2][y] != 1) {
							caminhos_l=true;
						}
						x--;
					}else if(mapa.map[x][y-1] !=1) {
						if(mapa.map[x][y-2] != 1 ) {
							caminhos_l=true;
						}
						y--;
					}else if((x<3)&&(y<8)) {
						if(mapa.map[x][y+1] !=1) {
							if(mapa.map[x][y+2] != 1) {
								caminhos_l=true;
							}
						}else if(mapa.map[x+1][y] != 1) {
							if(mapa.map[x+2][y] != 1) {
								caminhos_l=true;
							}
							x++;
						}
					}else {
						if(x>y) {
							x++;
						}else {
							y++;
						}
					}
					
				}

			}else if((x>0)||(y>0))
			{
				if(caminhos_l) {
					if(x>y) {
						if(mapa.map[x-1][y] != 1) {
							x--;
						}else {
							caminhos_l=false;
						}

					}else{
						if(mapa.map[x][y-1] != 1) {
							y--;
						}else {
							caminhos_l = false;
						}
					};	
				}else {		
					if((x>0)&&(y<9)) {
						if(mapa.map[x-1][y] != 1) {
							x--;
							if(mapa.map[x][y+1] != 1) {
								caminhos_l=true;
							}
						}else if(y > 0) {
							if(mapa.map[x+1][y] != 1){
							x++;
							caminhos_l=true;
							}
						}else if(mapa.map[x][y+1]!= 1) {							
							if(mapa.map[x-1][y] != 1) {
								caminhos_l=true;
							}
							y++;
						}else {
							System.out.println("Preso");
							break;
						}
					}else if((y>0)&&(x<3)) {
						if(mapa.map[x][y-1] != 1) {
							y--;
							if(mapa.map[x][y-2]!= 1) {
								caminhos_l=true;
							}
						}else if(mapa.map[x+1][y] !=1) {
							x++;
						}else if(mapa.map[x][y+1]!= 1) {
							y++;
						}else {
							System.out.println("Preso");
							break;
						}
					}
//					if(x>0) {
//						if(mapa.map[x-1][y] != 1) {
//							x--;
//							if(mapa.map[x][y+1] != 1) {
//								caminhos_l=true;
//							}
//							
//						}else if(y>0) {
//							if(mapa.map[x][y-1] !=1) {
//								y--;
//								if(mapa.map[x+1][y]!= 1) {
//									caminhos_l=true;
//								}
//								y--;
//							}else if(x<3) {
//								if(mapa.map[x+1][y]!= 1) {
//									x++;
//									if(mapa.map[x][y+1] != 1) {
//										caminhos_l=true;
//									}
//								}
//							}else if(mapa.map[x][y+1]!=1) {
//								y++;
//								if(mapa.map[x+1][y] != 1) {
//									caminhos_l=true;
//								}
//							}else {
//								System.out.print("Preso");
//							}
//						}
//					}else if(mapa.map[x][y-1] !=1) {
//						y--;
//						if(mapa.map[x+1][y]!= 1) {
//							caminhos_l=true;
//						}
//					}else if(mapa.map[x+1][y]!= 1) {
//						x++;
//						if(mapa.map[x][y-1] != 1) {
//							caminhos_l=true;
//						}
//					}else if(y<9) {
//						if(mapa.map[x][y-1]!=1) {
//							y++;
//							if(mapa.map[x+1][y] != 1) {
//								caminhos_l=true;
//							}
//						}
//					}else {
//						System.out.println("Preso");
//					}
					
				}
			}
			if((pos_ant_x == x)&&(pos_ant_y == y)) {
				continue;
			}else {
				pos_ant_x=x;
				pos_ant_y=y;
			}

		    for (int i = 0; i < mapa.map.length; ++i) {
		        for(int j = 0; j < mapa.map[i].length; ++j) {
		        	if(i==x && j ==y) {
		        		System.out.print("*");
		        	}else {
		        		System.out.print(mapa.map[i][j]);
		        	}		          
		        	if(j>= 9)
		        		System.out.println(",");
		        }
		      }		




		    passos++;
		    if((x<1)&&(y<1)) {
		    	break;
		    }
		}while(true);

		System.out.println("Foi dado "+passos+" passos para chegar a terra!!");
	}

}
