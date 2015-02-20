class Swordsman extends Unit{
    
    int hitpoints;
    int weapon_skill;
    int team;
    
    public Swordsman(int team){
        super(team);
        this.hitpoints = 4;
        this.weapon_skill = 6;
    }

    
}