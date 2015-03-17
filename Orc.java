class Orc extends Unit{
    
    int hitpoints;
    int weapon_skill;
    int team;
    String name;
    int id;
    
    public Orc(){
        this.team = 1;
        this.hitpoints = 10;
        this.weapon_skill = 8;
        this.name = "Orc";
        this.id = 4;
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
    
    public int getId(){
        return this.id;
    }
    
    public void lowerHitpoints() {
        this.hitpoints --;
    }
    
    public String getFilename() {
        return this.name + ".png";
    }
    
}