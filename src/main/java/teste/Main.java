package teste;


public class Main {

	public static void main(String[] args) {
		Data mapa = new Data();
		CoordenateMap coord = new CoordenateMap(mapa.map);
		RobiosMove robios = new RobiosMove(coord.replaceCoords(), 8, 0);
	}

}
