/**
 * @author Joeri Bes - 10358234 & Sebas Joustra 10516999
 *
 * Legends of Arborea
 * 
 */

import java.util.*;
import java.io.*;

class Arborea {
    
    private Tile[][] terrain;
    
    public static void main(String[] args) {
        
        Arborea game = new Arborea();
        
        game.createTerrain();
        game.addUnits();
    }
    
    public Arborea() {
        
    }
    
    private void createTerrain() {
        terrain = new Tile[9][9];
        int tilesInColumn = 5;
        for(int i=0; i < 5; i++) {
            for(int j=0; j<tilesInColumn; j++) {
                terrain[i][j] = new Tile(i, j);
            }
            tilesInColumn++;
        }
        
        tilesInColumn--;
        
        for(int i=5; i < 9; i++) {
            tilesInColumn--;
            for(int j=0; j<tilesInColumn; j++) {
                terrain[i][j] = new Tile(i, j);
            }
        }

        
    }
    
    private void addUnits() {  

        terrain[0][1].add(new Goblin());
        terrain[1][1].add(new Goblin());
        terrain[1][2].add(new Goblin());
        terrain[1][3].add(new Goblin());
        terrain[1][4].add(new Goblin());
        terrain[1][5].add(new Goblin());
        terrain[2][1].add(new Goblin());
        terrain[2][6].add(new Goblin());
        
        terrain[0][4].add(new Orc());
        terrain[0][0].add(new Orc());
        
        terrain[7][0].add(new Swordsman());
        terrain[7][1].add(new Swordsman());
        terrain[7][2].add(new Swordsman());
        terrain[7][3].add(new Swordsman());
        terrain[7][4].add(new Swordsman());
        terrain[8][1].add(new Swordsman());
        
        terrain[7][5].add(new General());
        terrain[8][0].add(new General());
        terrain[8][3].add(new General());
        
        for(int i = 0; i < terrain.length; i++){            
            for(int j = 0; j < terrain[i].length; j++){
                try{
                    System.out.println(terrain[i][j].getUnit().getName());
                } catch(NullPointerException e) {
                    System.out.println(".");
                }
            }
            System.out.println("------");
        }
        
    }
    
}