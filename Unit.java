public abstract class Unit {

    int old_x;
    int old_y;

    public Unit(){
        
    }
    
    public abstract int getHitpoints();
    public abstract int getTeam();
    public abstract int getWeaponSkill();
    public abstract String getName();
    
    public void move(int new_x, int new_y) {
        
    }
    
    
    
}