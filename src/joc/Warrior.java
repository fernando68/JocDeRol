/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package joc;

/**
 *
 * @author Fer
 */
public class Warrior extends Human{ 
//Constructor
    public Warrior(String name, int attackPoints, int defensePoints, int life) {
        super(name, attackPoints, defensePoints, life);
    }
    @Override
    protected void hit(int attackPoints){
        if(attackPoints-this.defensePoints <= 5){
            super.hit(0);
        }else{
            super.hit(attackPoints);
        }
    }
}