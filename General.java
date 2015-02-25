class General extends Unit{
    
    int hitpoints;
    int weapon_skill;
    int team;
    String name;
    
    public General(){
        this.team = 0;
        this.hitpoints = 5;
        this.weapon_skill = 8;
        this.name = "General";
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