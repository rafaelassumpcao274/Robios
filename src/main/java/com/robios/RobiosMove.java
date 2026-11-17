package com.robios;

import com.robios.entity.Route;
import com.robios.entity.Walks;
import com.robios.map.Data;

import java.util.ArrayList;
import java.util.List;


public class RobiosMove implements Robios {
    private ArrayList<String> map = new ArrayList<String>();
    private ArrayList<String> walk = new ArrayList<String>();
    private int x, y, currentXPosition, currentYPosition;

    public RobiosMove(ArrayList<String> map, int x, int y) {
        this.setMap(map);
        this.setX(x);
        this.setY(y);
        this.setCurrentYPosition(y);
        this.setCurrentXPosition(x);

        Route route = new Route().setInitialCoordX(x).setInitialCoordY(y);

        new Dao().save(route);

        this.walk.add(x + "," + y);

        Double distance = distanceOfEarth();
        Double angulo = angulo();

        String direction = obterDirecao(angulo);

        relativeMove(distance, direction);
        if (this.map.contains(x + "," + y)) {
            while ((this.getCurrentXPosition() != 0) | (this.getCurrentYPosition() != 0)) {
                Walks walks = new Walks()
                        .setCoordX(getCurrentXPosition())
                        .setCoordY(getCurrentYPosition())
                        .setRoute_id(route);

                printMap(this.getCurrentXPosition(), this.getCurrentYPosition());
//				System.out.println(this.getCurrentXPosition()+","+this.getCurrentYPosition());
                relativeMove(this.getCurrentXPosition(), this.getCurrentYPosition());
                System.out.println(" ");

                new Dao().save(walks);
            }

//			System.out.println(this.getCurrentXPosition()+","+this.getCurrentYPosition());
        }
//		CoordinateBD connectBD = new CoordinateBD(this.walk);


        System.out.println("O robios deu " + this.walk.size() + " passos.");
    }

    private void printMap(Integer coordX, Integer coordY) {
        int[][] mapaActual = Data.map;

        for (int i = 0; i < mapaActual.length; i++) {
            System.out.println(coordX + ", " + coordY);
            for (int j = 0; j < mapaActual[i].length; j++) {
                if (i == coordX && j == coordY) {
                    System.out.print("*");
                } else {
                    System.out.print(mapaActual[i][j]);
                }
            }
        }
    }

    private double distanceOfEarth() {

        Double d = Math.sqrt(Math.pow((this.x - 0), 2) + Math.pow((this.y - 0), 2));
        return d;
    }

    // Método para obter a direção com base no ângulo
    public static String obterDirecao(double anguloGraus) {
        if (anguloGraus >= 337.5 || anguloGraus < 22.5) {
            return "norte";
//		} else if (anguloGraus >= 22.5 && anguloGraus < 67.5) {
//			return "nordeste";
        } else if (anguloGraus >= 22.5 && anguloGraus < 112.5) {
            return "leste";
//		} else if (anguloGraus >= 112.5 && anguloGraus < 157.5) {
//			return "sudeste";
        } else if (anguloGraus >= 112.5 && anguloGraus < 202.5) {
            return "sul";
//		} else if (anguloGraus >= 202.5 && anguloGraus < 247.5) {
//			return "sudoeste";
//		} else if (anguloGraus >= 202.5 && anguloGraus < 292.5) {
//			return "oeste";
        } else {
            return "oeste";
//			return "noroeste";
        }
    }

    private double angulo() {
        // Coordenadas dos dois pontos
        double x1 = 0.0;
        double y1 = 0.0;


        // Calcular a diferença entre as coordenadas
        double diffX = this.x - x1;
        double diffY = this.y - y1;

        // Calcular o ângulo em radianos usando a função Math.atan2()
        double anguloRadianos = Math.atan2(diffY, diffX);

        // Converter o ângulo de radianos para graus
        double anguloGraus = Math.toDegrees(anguloRadianos);

        // Garantir que o ângulo esteja no intervalo de 0 a 360 graus
        if (anguloGraus < 0) {
            anguloGraus += 360;
        }

        return anguloGraus;
    }


    public boolean[] posibility() {

        boolean[] position = {false, false, false, false};
        if (this.map.contains((this.getCurrentXPosition() + 1) + "," + this.getCurrentYPosition())) { // baixo
            position[0] = true;
        }
        if (this.map.contains((this.getCurrentXPosition() - 1) + "," + this.getCurrentYPosition())) { // cima
            position[1] = true;
        }
        if (this.map.contains(this.getCurrentXPosition() + "," + (this.getCurrentYPosition() + 1))) {    //esquerda
            position[2] = true;
        }
        if (this.map.contains(this.getCurrentXPosition() + "," + (this.getCurrentYPosition() - 1))) { // direita
            position[3] = true;
        }

        return position;
    }

    public Double AStar(Double distace) {

        return this.walk.size() + distace;


    }

    public void relativeMove(Double distance, String direction) {


    }

    public void relativeMove(int x, int y) {
        boolean[] possible = posibility();
        boolean attemps = false;

        for (int i = 0; i < 4; i++) {
            if (possible[i]) {
                attemps = true;
            }
        }
        if (attemps) {

            LineaMoviment mv = new LineaMoviment(possible, x, y);
            int[] moviment = mv.move();
            this.map.remove(this.map.indexOf(x + "," + y));

            this.setX(x);
            this.setY(y);

            this.setCurrentXPosition(moviment[0]);
            this.setCurrentYPosition(moviment[1]);

        } else if ((this.getX() != this.getCurrentXPosition()) | (this.getY() != this.getCurrentYPosition())) {

            this.map.remove(this.getCurrentXPosition() + "," + this.getCurrentYPosition());
            this.map.add(this.getX() + "," + this.getY());

            this.setCurrentXPosition(this.getX());
            this.setCurrentYPosition(this.getY());
        } else {
            Data map = new Data();
            CoordenateMap coord = new CoordenateMap(map.map);
            this.setMap(coord.replaceCoords());
        }
        this.walk.add(this.getCurrentXPosition() + "," + this.getCurrentYPosition());
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
        return currentYPosition;
    }

    public void setCurrentXPosition(int currentXPosition) {
        this.currentXPosition = currentXPosition;
    }

    public void setCurrentYPosition(int currentYPosition) {
        this.currentYPosition = currentYPosition;
    }

}

