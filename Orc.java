class Orc extends Unit{
    
    int hitpoints;
    int weapon_skill;
    int team;
    String name;
    
    public Orc(){
        this.team = 1;
        this.hitpoints = 10;
        this.weapon_skill = 8;
        this.name = "Orc";
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