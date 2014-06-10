/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package joc;

import java.util.ArrayList;

/**
 *
 * @author Fer
 */
public class Item {
    private String name;
    private int attackBonus;
    private int defenseBonus;
    private ArrayList<Player> players = new ArrayList<>();
    
    public Item(String name,int attackBonus,int defenseBonus){
        this.name = name;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
    }

    public String getName() {return name;}
    public int getAttackBonus() {return attackBonus;}
    public int getDefenseBonus() {return defenseBonus;}
    
    public String mostraItem(){return "- "+this.name+" BA:"+this.attackBonus+" / BD:"+this.defenseBonus;}
    public void addPlayer(Player p){
        if(!this.players.contains(p)){
            players.add(p);
        }
    }
    public void removePlayer(Player p){
        if(this.players.contains(p)){
            players.remove(p);
        }
    }
    public void removeItemsReferencedPlayer(){
        for (int i = 0; i < this.players.size(); i++) {
            players.get(i).removeItem(this);
        }
    }
}