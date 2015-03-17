import java.util.*;

class Ai {

    private List<Tile> own_tiles;
    private List<Tile> enemy_tiles;
    private Tile startTile;
    private Tile goalTile;
    List<Tile> moveableNeighbours;
    private Tile enemyNeighbour;


    public Ai(List<Tile> own_tiles, List<Tile> enemy_tiles) {
        this.own_tiles = own_tiles;
        this.enemy_tiles = enemy_tiles;
        this.startTile = setStartTile();
        this.goalTile = setGoalTile();
    }
    
    /* Sets the start tile of the AI. It will find a smart decision based on which friendly units
       are strong, or when a unit is all alone and needs to get to the group */
    private Tile setStartTile() {
        Tile tile = findTileWithEnemyNeighbour();
        if(tile != null) {
            moveableNeighbours = tile.getMoveableNeighbours();
            return tile;
        }

        int idx = new Random().nextInt(own_tiles.size());
        tile = own_tiles.get(idx);
        moveableNeighbours = tile.getMoveableNeighbours();
        while(moveableNeighbours.size() == 0) {
            idx = new Random().nextInt(own_tiles.size());
            tile = own_tiles.get(idx);
            moveableNeighbours = tile.getMoveableNeighbours();
        }

        return tile;
    }

    /* Sets the goal tile of the AI. It will find a smart decision based on which enemies are
       weak, or where most friendly units are */
    private Tile setGoalTile() {
        if(enemyNeighbour != null && getHitChance(enemyNeighbour) > 0.3) {
            return enemyNeighbour;
        }
        if(enemyNeighbour != null && getHitChance(enemyNeighbour) < 0.3) {
            Tile safeSpot = findSafeSpot();
            if(safeSpot != null) {
                return safeSpot;
            }
        }

        Tile weakestEnemy = findWeakestEnemy();
        Tile closestEnemy = findClosestEnemy(startTile);

        return weakestEnemy;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public Tile getGoalTile() {
        return goalTile;
    }

    /* This will return a safe tile (for when a tile is under pressure by an enemy tile) */
    private Tile findSafeSpot() {
        for(Tile tile : moveableNeighbours) {
            if(!tile.hasEnemyNeighbour(startTile)) {
                return tile;
            }
        }
        return null;
    }

    /* This will return the hit chance of the start tile to a given enemy, even when it is not
       a neighbour tile */
    private double getHitChance(Tile enemyNeighbour) {
        int aws = startTile.getUnit().getWeaponSkill() + startTile.getAdjecency();
        int dws = enemyNeighbour.getUnit().getWeaponSkill() + enemyNeighbour.getAdjecency();
        
        return 1/(1+Math.exp(-0.4*(aws-dws)));
    }

    /* This will return a random neighbour of a given tile */
    public Tile getRandomNeighbour(Tile tile) {
        if(moveableNeighbours.size() == 0) {
            System.out.println("geen moveable neighbour");
            return null;
        } else {
            int idx = new Random().nextInt(moveableNeighbours.size());
            return moveableNeighbours.get(idx);
        }
    }

    /* This method will return a tile when that tile is next to an enemy tile (and something
       should be done about that!) */
    private Tile findTileWithEnemyNeighbour() {
        List<Tile> neighbours;

        for(Tile own_tile : own_tiles) {
            neighbours = own_tile.getNeighbours();

            for(Tile neighbour : neighbours) {
                try{
                    if(neighbour.getUnit().getTeam() != own_tile.getUnit().getTeam()) {
                        System.out.println("found tile with enemy neighbour");
                        enemyNeighbour = neighbour;
                        return own_tile;
                    }
                } catch(NullPointerException e) {

                }
            }
        }
        return null;
    }
    
    /* This method returns the weakest enemy to the start tile the weakness is determined by the
       hitpoints, the adjecency and the weapon skill of that enemy. */
    private Tile findWeakestEnemy() {
        int lowestStrength = 100;
        Tile weakestTile = null;

        for(Tile tile : enemy_tiles) {
            int strength = tile.getUnit().getHitpoints() + tile.getUnit().getWeaponSkill() +
              tile.getAdjecency();

            if(strength < lowestStrength) {
                weakestTile = tile;
                lowestStrength = strength;
            }
        }
        return weakestTile;
    }

    /* This method returns the closest enemy to the start tile */
    public Tile findClosestEnemy(Tile startTile) {
        int x1 = startTile.get_x();
        int y1 = startTile.get_y();
        double shortestDist = 100;
        Tile shortestTile = null;

        for(Tile tile : enemy_tiles) {
            if(findDistance(x1, y1, tile.get_x(), tile.get_y()) < shortestDist) {
                shortestDist = findDistance(x1, y1, tile.get_x(), tile.get_y());
                shortestTile = tile;
            }
        }
        return shortestTile;
    }

    /* When the AI found a tile to start with and a tile where it wants to move to but is not
       a neighbour tile, it needs to find another way to get there. This method returns the
       shortest distance to that goal. */
    public Tile findMove(Tile startTile, Tile goalTile) {
        int x1 = startTile.get_x();
        int y1 = startTile.get_y();
        int x2 = goalTile.get_x();
        int y2 = goalTile.get_y();
        double shortestDist = findDistance(x1, y1, x2, y2);
        Tile shortestTile = null;

        // Start tile is next to goal tile
        if(moveableNeighbours.contains(goalTile)) {
            return goalTile;
        }

        for(Tile tile : moveableNeighbours) {
            if(findDistance(tile.get_x(), tile.get_y(), x2, y2) < shortestDist) {
                shortestDist = findDistance(tile.get_x(), tile.get_y(), x2, y2);
                shortestTile = tile;
            }
        }
        
        if(shortestTile == null){
            Tile rn = getRandomNeighbour(startTile);
            return rn;
        }
        return shortestTile;
    }

    /* Finds the distance between two coordinates */
    private double findDistance(int x1, int y1, int x2, int y2) {
        int x_diff = x2-x1;
        int y_diff = y2-y1;
        return Math.sqrt(Math.pow(x_diff, 2) + Math.pow(y_diff, 2));
    }

}