/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package joc;
import excepcions.ExcepcioJocRol;
import excepcions.ExcepcioJocRol.*;
import java.util.ArrayList;
/**
 *
 * @author Fer
 */
public abstract class Player{
    //Atributos
    private String name;
    protected int attackPoints,defensePoints,life;
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private final int MAXITEMS=3;
    //Constructor
    public Player(String name, int attackPoints, int defensePoints, int life) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.life = life;
    }

    //Metodes Gets
    public String getName() {return name;}
    public int getAttackPoints() {return attackPoints;}
    public int getDefensePoints() {return defensePoints;}
    public int getLife() {return life;}
    public int qEquips(){return this.teams.size();}
    public boolean isItemsEmpty(){return this.items.isEmpty();}
    public boolean isDead(){return this.life == 0;}

    //SET
    public void setLife(int life) {this.life = life;}
    //METODOS DE MOSTRAR
    public String str(){
        String cadena = this.mostraDades()+" (Pertany a "+this.qEquips()+" equips) té els items:\n";
        if(this.items.isEmpty()){
            cadena+="\t\tEl jugador no te ITEMS\n";
        }else{
            for(Item it: this.items){
                cadena+="\t"+it.mostraItem()+"\n";
            }
        }
        return cadena;
    }
    public String mostraDades(){
        return this.name+" PA:"+this.attackPoints+" / PD:"+this.defensePoints+" / PV:"+this.life;
    }
    public String mostrarNomEquips(){
        if(this.teams.isEmpty()){
            return "AQUEST JUGADOR NO PERTENEIX A NINGUN EQUIP!!\n";
        }
        String cadena = "El jugador "+this.name+" pertany a aquestos equips: \n";
        for(Team t : this.teams){
            cadena += t.mostrarNomEquip();
        }
        return cadena;
    }
    
    // Altres Metodes
    public void attack(Player p) throws ExcepcioJocRol{
        if(this == p){throw new excAttackMe();}//Un jugador no pot atacar-se a ell mateix.
        if(this.isDead()){throw new excJugadorMort();}//Un mort no pot atacar
        if(p.isDead()){throw new excAttackJugadorMort();}//Un mort no pot ser atacat
        System.out.println(this.str()+"ataca "+p.str());
        this.hit(p.AttackTotal());
        if(!this.isDead()){//Si el jugador ha muerto en el ataque ya no puede atacar
            p.hit(this.AttackTotal());
        }
        System.out.println("\nRESULTAT: \n\t"+this.mostraDades()+"\n\t"+p.mostraDades());
    }
    protected void hit(int attackPoints){
        int aux = this.life;
        String cadena = this.name+" es atacat amb "+attackPoints+" i es defen amb "+this.DefenseTotal()+".";
        int atac = attackPoints - this.DefenseTotal();
        if(atac > 0){//Condicion para que no pueda aumentar la vida
            if(atac <= this.life){//Condicion para que la vida no pueda ser negativa
                this.life -= atac;
            }else{
                this.life = 0;
            }
            System.out.println(cadena+" Vides: "+aux+" - "+atac+" = "+this.life);
        }else{
            System.out.println(cadena+" Vides: "+aux+" - 0 = "+this.life);
        }
    }
    private int AttackTotal(){
        int bonus = 0;
        for(Item it: this.items){
            bonus+=it.getAttackBonus();
        }
        return bonus+this.attackPoints;
    }
    private int DefenseTotal(){
        int bonus = 0;
        for(Item it: this.items){
            bonus+=it.getDefenseBonus();
        }
        return bonus+this.defensePoints;
    }
    
    //Metodes LLISTA
    public void addTeam(Team t){
        if(!this.teams.contains(t)){
            this.teams.add(t);
            t.addPlayer(this);
        }
    }
    public void removeTeam(Team t){
        if(this.teams.contains(t)){
            this.teams.remove(t);
            t.removePlayer(this);
        }
    }
    public void addItem(Item item) throws excLlimitItemsPerPlayer{
        if(isFullItems()) throw new excLlimitItemsPerPlayer();
        if(!this.items.contains(item)){
            this.items.add(item);
            item.addPlayer(this);
        }
    }
    public void removeItem(Item item){
        if(this.items.contains(item)){
            this.items.remove(item);
            item.removePlayer(this);
        }
    }
    public void removePlayersReferencedTeams(){
        for (int i = 0; i < this.teams.size(); i++) {
            teams.get(i).removePlayer(this);
        }
    }
    public boolean containsTeam(Team t){
        return this.teams.contains(t);
    }
    public boolean containsItem(Item t){
        return this.items.contains(t);
    }
    public boolean isFullItems(){return items.size()==MAXITEMS;}
    
}