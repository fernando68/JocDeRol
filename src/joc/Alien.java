/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package joc;

import excepcions.ExcepcioJocRol;

/**
 *
 * @author Fer
 */
public class Alien extends Player{
//Constructor
    public Alien(String name, int attackPoints, int defensePoints, int life) {
        super(name, attackPoints, defensePoints, life);
    }
    @Override
    public void attack(Player p) throws ExcepcioJocRol{
        if(this.life>20){
            this.attackPoints *= 2;
            this.defensePoints *= 0.3;
        }
        super.attack(p);
    }
}