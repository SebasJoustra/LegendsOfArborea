class Tile {
    
    int x_pos;
    int y_pos;
    boolean isTaken;
    Unit unit;
    
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
}