class Tile {
    
    int x_pos;
    int y_pos;
    boolean isTaken;
    
    public Tile(int x_pos, int y_pos){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }
    
    public Tile(int x_pos, int y_pos, boolean isTaken){
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.isTaken = isTaken;
    }
    
    public boolean isTaken() {
        return isTaken;
    }
}