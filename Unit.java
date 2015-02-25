public abstract class Unit {

    int hitpoints;
    int weapon_skill;
    int team;

    public Unit(){
        this.hitpoints = 0;
        this.weapon_skill = 0;
        this.team = 0;
    }
    
    public abstract int getHitpoints();
    public abstract int getTeam();
    public abstract int getWeaponSkill();
    public abstract String getName();
    
    
    
}