public abstract class Unit {

    int x;
    int y;

    public Unit(){
        /*this.x = x;
        this.y = y;*/
    }
    
    public abstract int getHitpoints();
    public abstract int getTeam();
    public abstract int getWeaponSkill();
    public abstract String getName();
    public abstract int getId();
    public abstract void lowerHitpoints();
    
}