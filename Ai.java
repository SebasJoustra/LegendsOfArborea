import java.util.*;

class Ai {

    private List<Tile> own_tiles = new ArrayList<Tile>();

    public Ai(List<Tile> own_tiles) {
        this.own_tiles = own_tiles;
    }
    
    public Tile getRandomTile() {
        int idx = new Random().nextInt(own_tiles.size());
        return own_tiles.get(idx);
    }

    public Tile getRandomNeighbour(Tile randomTile) {
        List<Tile> neighbours = randomTile.getMoveableNeighbours();
        if(neighbours.size() == 0) {
            return null;
        } else {
            int idx = new Random().nextInt(neighbours.size());
            return neighbours.get(idx);
        }
    }

}