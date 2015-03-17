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
    private static int INITIAL_TURNS = 2;
    private List<Tile> team_ai = new ArrayList<Tile>();
    private List<Tile> team_user = new ArrayList<Tile>();
    private Ai ai;
    
    public static void main(String[] args) {
                
        game = new Arborea();
        
        game.createTerrain();
        game.addUnits();
        
        
        
        while(true) {
            /*for(int i=INITIAL_TURNS; i>0; i--) {
                game.printTerrain();
                game.userTurns(i);
            }*/
            
            for(int i=INITIAL_TURNS; i>0; i--) {
                game.printTerrain();
                game.aiTurns();
            }
            
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
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
    
    /* Add all available neighbours to every tile on the board */
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
                if(y<8 &&terrain[x][y+1] != null) terrain[x][y].addNeighbours(terrain[x][y+1]);                 
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
    
    /* Manually adds the units to the board */
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
        terrain[0][6].add(new Goblin());
        
        team_ai.add(terrain[1][0]);
        team_ai.add(terrain[0][1]);
        team_ai.add(terrain[1][2]);
        team_ai.add(terrain[6][2]);
        team_ai.add(terrain[4][0]);
        team_ai.add(terrain[0][6]);
        
        for(int i = 0; i<5; i++) {
            terrain[i][7].add(new Swordsman());
            team_user.add(terrain[i][7]);
        }
        terrain[1][8].add(new Swordsman());        
        terrain[5][7].add(new General());
        terrain[0][8].add(new General());
        terrain[3][8].add(new General());        
        
        
        team_user.add(terrain[1][8]);
        team_user.add(terrain[5][7]);
        team_user.add(terrain[0][8]);
        team_user.add(terrain[3][8]);
         
    }
    
    /* This gives the command to move (or attack) a unit from one tile to another */
    private void move(int old_x, int old_y, int new_x, int new_y) {
        
        Tile oldTile = terrain[old_x][old_y];
        Tile newTile = terrain[new_x][new_y];
        
        Unit unit = oldTile.getUnit();
        
        if(newTile.isTaken()){
            Unit otherUnit = newTile.getUnit(); 
            if(unit.getTeam() != otherUnit.getTeam()) { 
                game.attack(oldTile, newTile); // Units are from the other team, attack
            } else {
                System.out.println("this tile is already taken by your team!");
                userTurns(2);
            }
        } else {
            // a unit of either team AI or team user moved succesfully, replace in list.
            if(unit.getTeam() == 1){
                team_ai.remove(oldTile);   
                team_ai.add(newTile);
            } else {
                team_user.remove(oldTile);
                team_user.add(newTile);
            }
            
            // add the unit to a different tile
            oldTile.remove();
            newTile.add(unit);

        }
    }


    
    /* Attacks if a unit is on that tile already */
    private void attack(Tile att_tile, Tile def_tile) {
        double d = Math.random();
        double hitChance = getHitChance(att_tile, def_tile);
        Unit defender = def_tile.getUnit();
        System.out.println(hitChance);

        if(d < hitChance){
            defender.lowerHitpoints();
            System.out.print("You hit!, new hitpoints: ");
            System.out.println(defender.getHitpoints());
            
            if(defender.getHitpoints()==0) {
                if(def_tile.getUnit().getTeam() == 1){    // a unit of team AI died, remove from list
                    team_ai.remove(def_tile);
                } else {
                    team_user.remove(def_tile);
                }
                def_tile.remove();
            }
        } else {
            System.out.println("You missed");
        }
    }

    public double getHitChance(Tile att_tile, Tile def_tile) {
        int aws = att_tile.getUnit().getWeaponSkill() + att_tile.getAdjecency();
        int dws = def_tile.getUnit().getWeaponSkill() + def_tile.getAdjecency();
        
        return 1/(1+Math.exp(-0.4*(aws-dws)));
    }
    
    private void userTurns(int turns) {
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
            } else {
                System.out.println("not a legal move, try again");
                userTurns(turns);
            }
        } catch(NullPointerException e) {
            System.out.println("There is no friendly unit at the start spot, try again");
            userTurns(turns);
        }
    }

    private void aiTurns() {
        ai = new Ai(team_ai, team_user);

        System.out.println();
        Tile startTile = ai.getStartTile();
        System.out.print("Moving from " + startTile.getPosition());
        Tile goalTile = ai.getGoalTile();
        System.out.print(" to " + goalTile.getPosition());
        Tile nextTile = ai.findMove(startTile, goalTile);
        System.out.println(" by moving first to tile " + nextTile.getPosition()); 
        

        game.move(startTile.get_x(), startTile.get_y(), nextTile.get_x(), nextTile.get_y());
    }



    

    
}