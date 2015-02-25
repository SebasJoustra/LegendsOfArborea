class Swordsman extends Unit{
    
    int hitpoints;
    int weapon_skill;
    int team;
    String name;
    
    public Swordsman(){
        this.team = 0;
        this.hitpoints = 4;
        this.weapon_skill = 6;
        this.name = "Swordsman";
    }
    
    public int getHitpoints(){
        return this.hitpoints;
    }
    
    public int getTeam() {
        return this.team;
    }

    public String getName() {
        return this.name;
    }
    
    public int getWeaponSkill() {
        return this.weapon_skill;
    }
    
}