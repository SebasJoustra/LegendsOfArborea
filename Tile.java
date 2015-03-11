import java.util.*;

class Tile {
    
    int x_pos;
    int y_pos;
    boolean isTaken;
    Unit unit;
    List<Tile> neighbours = new ArrayList<Tile>();
    
    public Tile(int x_pos, int y_pos){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }
    
    public boolean isTaken() {
        return isTaken;
    }
    
    public void add(Unit unit) {
        this.unit = unit;
        this.isTaken = true;
    }
    
    public void remove() {
        this.unit = null;
        this.isTaken = false;
    }
    
    public Unit getUnit(){
        return unit;
    }
    
    public String getPosition(){
        return x_pos + "," + y_pos;
    }

    public int get_x(){
        return x_pos;
    }

    public int get_y(){
        return y_pos;
    }
    
    public void addNeighbours(Tile tile) {
        neighbours.add(tile);
    }
    
    public List getNeighbours() {
        return neighbours;
    }

    public int getAdjency() {
        int adjency = 0;
        for(Tile tile : neighbours) {
            if(tile.isTaken()) {
                if(tile.getUnit().getTeam() == this.getUnit().getTeam()) {
                    if(tile.getUnit().getId() == 2) {
                        adjency = adjency + 2;
                    } else {
                        adjency++;
                    }
                } else {
                    if(tile.getUnit().getId() == 2) {
                        adjency = adjency - 2;
                    } else {
                        adjency--;
                    }
                }
            }
        }
        return adjency;
    }
    
    /* Checks whether a given tile is a neighbour of this tile */
    public boolean neighbourAvailable(Tile tile) {
        if(neighbours.contains(tile)){
            return true;
        }
        else return false;
    }

    /* Returns a list of neighbours that the AI is able to move to or attack */
    public List getMoveableNeighbours() {
        List<Tile> moveableNeighbours = new ArrayList<Tile>();
        for(Tile tile : neighbours) {
            if(!tile.isTaken()) {
                moveableNeighbours.add(tile);
            } else if(tile.getUnit().getTeam() == 0) {
                moveableNeighbours.add(tile);
            } else {
                continue;
            }
        }
        return moveableNeighbours;
    }
}