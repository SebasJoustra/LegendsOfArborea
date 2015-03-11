import java.util.*;

class Ai {

    private List<Tile> own_tiles = new ArrayList<Tile>();

    public Ai(List<Tile> own_tiles) {
        this.own_tiles = own_tiles;
    }
    
    public Tile getRandomTile() {
        int idx = new Random().nextInt(own_tiles.size());
        Tile randomTile = own_tiles.get(idx);
        return randomTile;
    }
    
    public void moveRandomTile() {
        int idx = new Random().nextInt(own_tiles.size());
        Tile randomTile = own_tiles.get(idx);
        
        List<Tile> neighbours = randomTile.getNeighbours();
    }

}