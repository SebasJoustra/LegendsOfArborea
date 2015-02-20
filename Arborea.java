/**
 * @author Joeri Bes - 10358234 & Sebas Joustra 10516999
 *
 * Legends of Arborea bla
 * 
 */

import java.util.*;
import java.io.*;

class Arborea {
      
    public static void main(String[] args) {
        Tile[][] terrain;
        
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
        
        for(int i = 0; i < terrain.length; i++){            
            for(int j = 0; j < terrain[i].length; j++){
                System.out.println(terrain[i][j]);

            }
            System.out.println("------");
        }
        
    }
    
    private void createNormalGame() {
        
        
    }
    
}