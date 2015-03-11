/**
 * @author Joeri Bes - 10358234 & Sebastiaan Joustra 10516999
 *
 * Legends of Arborea
 * 
 */

import java.util.*;
import java.io.*;

class Arborea {
    
    private static Tile[][] terrain;
    private static Arborea game;
    private Random rand = new Random();
    private int turns;
    private List<Tile> team_ai = new ArrayList<Tile>();
    private List<Tile> team_user = new ArrayList<Tile>();
    
    public static void main(String[] args) {
                
        game = new Arborea();
        
        game.createTerrain();
        game.addUnits();
        
        game.printTerrain();
        
        while(true) {
            //game.userTurns();
            game.AiTurns();
        }
    }
    
    public Arborea() {
        
    }
    
    private void createTerrain() {
        
        /* Loop that creates the initial terrain */
        terrain = new Tile[9][9];
        int tilesInColumn = 5;
        for(int i=0; i < 5; i++) {
            for(int j=0; j<tilesInColumn; j++) {
                terrain[j][i] = new Tile(j, i);
            }
            tilesInColumn++;
        }
        
        tilesInColumn--;
        
        for(int i=5; i < 9; i++) {
            tilesInColumn--;
            for(int j=0; j<tilesInColumn; j++) {
                terrain[j][i] = new Tile(j, i);
            }
        }
        
        /* Loop that adds the neighbour tiles */
        tilesInColumn = 5;
        for(int i=0; i < 5; i++) {
            for(int j=0; j<tilesInColumn; j++) {
                addNeighbours(j, i);
            }
            tilesInColumn++;
        }
        
        tilesInColumn--;
        
        for(int i=5; i < 9; i++) {
            tilesInColumn--;
            for(int j=0; j<tilesInColumn; j++) {
                addNeighbours(j, i);
            }
        }
    }
    
    private void addNeighbours(int x, int y) {
            if(y<4) {
                terrain[x][y].addNeighbours(terrain[x][y+1]);
                terrain[x][y].addNeighbours(terrain[x+1][y+1]); 
                if(x>0) terrain[x][y].addNeighbours(terrain[x-1][y]);
                if(terrain[x+1][y] != null) terrain[x][y].addNeighbours(terrain[x+1][y]);
                if(y>0 && terrain[x][y-1] != null) terrain[x][y].addNeighbours(terrain[x][y-1]);                
                if(x>0 && y>0) terrain[x][y].addNeighbours(terrain[x-1][y-1]);              
            }
            if(y==4) {
                if(x>0) terrain[x][y].addNeighbours(terrain[x-1][y]);
                if(x>0) terrain[x][y].addNeighbours(terrain[x-1][y-1]);
                if(x>0) terrain[x][y].addNeighbours(terrain[x-1][y+1]);
                if(x<8) terrain[x][y].addNeighbours(terrain[x+1][y]);
                if(terrain[x][y-1] != null) terrain[x][y].addNeighbours(terrain[x][y-1]);
                if(terrain[x][y+1] != null) terrain[x][y].addNeighbours(terrain[x][y+1]);
            }
            if(y>4) {
                terrain[x][y].addNeighbours(terrain[x][y-1]);
                terrain[x][y].addNeighbours(terrain[x+1][y-1]);
                if(terrain[x+1][y] != null) terrain[x][y].addNeighbours(terrain[x+1][y]);
                if(x>0) terrain[x][y].addNeighbours(terrain[x-1][y]);
                if(x>0 && y<8) terrain[x][y].addNeighbours(terrain[x-1][y+1]);
                if(y<8) terrain[x][y].addNeighbours(terrain[x][y+1]);                 
            }
            
            
    }
    
    private void printTerrain(){
        for(int i = 0; i < terrain.length; i++){            
            for(int j = 0; j < terrain[i].length; j++){
                try{
                    System.out.print(terrain[i][j].getUnit().getId() + " ");
                } catch(NullPointerException e) {
                    if(terrain[i][j] != null) {
                        System.out.print("- ");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    private void addUnits() {
        
        for(int i = 1; i<6; i++) {
            terrain[i][1].add(new Goblin());
            team_ai.add(terrain[i][1]);
        }
        terrain[1][0].add(new Goblin());
        terrain[1][2].add(new Goblin());        
        terrain[6][2].add(new Goblin());        
        terrain[4][0].add(new Orc());
        terrain[0][1].add(new Orc());
        
        team_ai.add(terrain[1][0]);
        team_ai.add(terrain[0][1]);
        team_ai.add(terrain[1][2]);
        team_ai.add(terrain[6][2]);
        team_ai.add(terrain[4][0]);
        
        for(int i = 0; i<6; i++) {
            terrain[i][7].add(new Swordsman());
        }
        terrain[1][8].add(new Swordsman());
        
        terrain[5][7].add(new General());
        terrain[0][8].add(new General());
        terrain[3][8].add(new General());
        
        for(Tile item : team_ai) {
                System.out.println(item.getPosition());
        }
        
        //terrain[0][6].add(new Goblin());
        
        /*List<Tile> neighbours = terrain[2][6].getNeighbours();
        for(Tile item : neighbours) {
            try{
                System.out.println(item.getPosition());
            } catch(NullPointerException e) {
                
            }
        }*/
    }
    
    private void move(int old_x, int old_y, int new_x, int new_y) {
        
        Tile oldTile = terrain[old_x][old_y];
        Tile newTile = terrain[new_x][new_y];
        
        Unit unit = oldTile.getUnit();
        
        if(newTile.isTaken()){
            Unit otherUnit = newTile.getUnit(); 
            if(unit.getTeam() != otherUnit.getTeam()) { 
                game.attack(oldTile, newTile); // Units are from the other team
            } else {
                System.out.println("this tile is already taken by your team!");
                turns++;
            }
        } else {
            oldTile.remove();        
            newTile.add(unit);
        }
    }
    
    private void attack(Tile att_tile, Tile def_tile) {
        double d = Math.random();
        Unit attacker = att_tile.getUnit();
        Unit defender = def_tile.getUnit();
        
        int aws = attacker.getWeaponSkill();
        int dws = defender.getWeaponSkill();
        
        //hitChance = 1/(1+e^-0.4(aws-dws));
        double hitChance = 0.9;
        if(d < hitChance){
            defender.lowerHitpoints();
            System.out.print("You hit!, new hitpoints: ");
            System.out.println(defender.getHitpoints());
            
            if(defender.getHitpoints()==0) {
                def_tile.remove();
            }
        } else {
            System.out.println("You missed");
        }
    }
    
    /*private boolean checkAdjency() {
        
    }*/
    
    private void userTurns() {
        turns = 5;
        while(turns > 0){
            System.out.println(turns + " turns left");
            Scanner in = new Scanner(System.in);
            System.out.println("Your turn, from which x,y do you want to move?");
            System.out.print("from x: ");
            int x1 = Integer.parseInt(in.next());
            System.out.print("from y: ");
            int y1 = Integer.parseInt(in.next());
            
            System.out.println("where do you want to move to?");
            System.out.print("to x: ");
            int x2 = Integer.parseInt(in.next());
            System.out.print("to y: ");
            int y2 = Integer.parseInt(in.next());
            
            Tile userTile = terrain[x1][y1];
            
            try{
                if(userTile.neighbourAvailable(terrain[x2][y2]) && userTile.getUnit().getTeam()==0){
                    game.move(x1, y1, x2, y2);
                    game.printTerrain();
                    turns--;
                } else {
                    System.out.println("not a legal move, try again");
                }
            } catch(NullPointerException e) {
                System.out.println("There is no unit at this spot");
            }
            
        }
    }
    
    private boolean legalMove(int x1, int y1, int x2, int y2){    
        if( (x1-1 == x2) && (y1 == y2) ) {
            return true;
        } else if( (x1+1 == x2) && (y1 == y2) ) {
            return true;
        } else if( (x1 == x2) && (y1-1 == y2) ) {
            return true;
        } else if( (x1 == x2) && (y1+1 == y2) ) {
            return true;
        } else if( (x1+1 == x2) && (y1-1 == y2) ) {
            return true;
        } else if( (x1+1 == x2) && (y1+1 == y2) ) {
            return true;
        } else {
            return false;
        }
    }
    private void AiTurns() {
        Ai ai = new Ai(team_ai);
        System.out.println(ai.moveRandomTile());
    }

    

    
}