package teste;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Data mapa = new Data();
		int x=3;
		int y=6;

		int pos_ant_x=0;
		int pos_ant_y=0;
		int caminhos = 0;
		int passos=0;
		boolean teste = false;
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
			if(mapa.map[x][y] == 1)
			{
				x=pos_ant_x;
				y= pos_ant_y;
				if(teste) {
					x=pos_ant_x;
					y= pos_ant_y;
					
					break;
				}else {
					if(((x<3)||(y<9))&&((x>0)&&(y>0))){
						caminhos=0;
						if(mapa.map[x-1][y] != 0) {
							if(mapa.map[x][y-1] !=0) {
								if(x != 3) {
									if(mapa.map[x+1][y] != 0) {
										x=pos_ant_x;
										y= pos_ant_y;
									}else {
										x++;
									}									
								}else{
									if(mapa.map[x][y+1] != 0) {
										x=pos_ant_x;
										y= pos_ant_y;
									}else {
										caminhos++;
										y++;
									}	
								}
								
							}else {
								y--;
							}
						}else {
							x--;
						}
					}else {
						if(x==3) {
							if(y<1){
								if(mapa.map[x-1][y] !=0) {
									if(mapa.map[x][y+1] !=0) {
										System.out.println("Erro robo preso");
										break;
									}else {
										y++;
									}
								}else {
									x--;
								}								
							}else if(y>8){
								if(mapa.map[x-1][y] !=0) {
									if(mapa.map[x][y-1] !=0) {
										System.out.println("Erro robo preso");
										break;
									}else {
										y--;
									}
								}else {
									x--;
									}	
								
							}
						}else if(x<1){
							if(mapa.map[x+1][y] !=0) {
								if(mapa.map[x][y-1] !=0) {
									if(y<9) {
										if(mapa.map[x][y+1] == 0) {
											y++;
											caminhos++;
										}									
										}
								}else {
									y--;
								}
							}else {
								x--;
							}	
						}						
					}
					teste=true;
				}
				continue;
				
			}else {
				teste = false;
				pos_ant_x = x;
				pos_ant_y = y;

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
			    if(y>2) {
			    	if(x>2) {
			    		if((mapa.map[x][y-2]== 0)&&(mapa.map[x][y-1]== 0)) {
			    			caminhos = 0;
			    		};
			    		if((mapa.map[x-2][y]== 0)&&(mapa.map[x-1][y]== 0)) {
			    			caminhos = 0;
			    		};
			    	}
	
			    }

			    if(caminhos>1) {
			    	if(x<y) {
			    		x++;
			    	}else {
			    		y++;
			    	}
			    
			    
			    if(x>=3) {
			    	caminhos=0;
			    }
			    }else {
				    if(x>y) {
				    	x--;
				    }else{
				    	y--;
				    };
				   
			    }

			}

		    
//		    System.out.println("Valor x:");
//		    x=scan.nextInt();
//		    System.out.println("Valor y:");
//		    y=scan.nextInt();

		    passos++;

		}while((x>=0) && (y>=0));

		System.out.println("Foi dado "+passos+" passos para chegar a terra!!");
	}

}
