/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excepcions;

import inici.Inventari;

/**
 *
 * @author Fernando
 */
public abstract class ExcepcioJocRol extends Exception{

    @Override
    public abstract String getMessage();
    
    public static class excNomPlayerNotExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "No ni ha ningun jugador amb eixe nom!!\n";
        }
        
    }
    public static class excNomTeamNotExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "No ni ha ningun equip amb eixe nom!!\n";
        }
        
    }
    public static class excNomItemNotExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "No ni ha ningun Item amb eixe nom!!\n";
        }
        
    }
    
    public static class excNomPlayerExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "Ya ni ha un jugador amb eixe nom!!\n";
        }
        
    }
    public static class excNomTeamExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "Ya ni ha un equip amb eixe nom!!\n";
        }
        
    }
    public static class excNomItemExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "Ya ni ha un item amb eixe nom!!\n";
        }
        
    }
    
    public static class excNotTeamsExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "NO NI HAN EQUIPS CREATS!!\n";
        }
        
    }
    public static class excNotPlayersExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "NO NI HAN JUGADORS CREATS!!\n";
        }
        
    }
    public static class excNotItemsExists extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "NO NI HAN ITEMS CREATS!!\n";
        }
        
    }
    
    public static class excRepeatTeamPlayer extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "EL JUGADOR JA PERTANY A AQUEST EQUIP!!\n";
        }
        
    }
    public static class excRepeatItemPlayer extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "AQUEST ITEM JA PERTANY A AQUEST JUGADOR!!\n";
        }
        
    }
    
    public static class excInsuficientPlayers extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "ES NECESITEN MINIM DOS JUGADORS!!\n";
        }
        
    }
    public static class excIndexOutOfBonds extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return "ERROR\n";
        }
        
    }
    public static class excAttackJugadorMort extends ExcepcioJocRol{
        
        @Override
        public String getMessage() {
            return "ERROR NO ES POT ATACAR A UN JUGADOR MORT!!\n";
        }
        
    }
    public static class excJugadorMort extends ExcepcioJocRol{
        
        @Override
        public String getMessage() {
            return "EL JUGADOR ESTA MORT!!\n";
        }
        
    }
    public static class excAttackMe extends ExcepcioJocRol{
        
        @Override
        public String getMessage() {
            return("No pots atacarte a tu mateix!!\n");
        }
        
    }
    public static class excNoPertenixesEquip extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return("ERROR EL JUGADOR NO PERTENEIX A ESTE EQUIP!!\n");
        }
        
    }
    public static class excNoItemEquipat extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return("ERROR NO TENS AQUEST ITEM EQUIPAT!!!!\n");
        }
        
    }
    public static class excLlimitEquips extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return("ERROR S'HA ALCANZAT EL LLIMIT D'EQUIPS CREATS("+Inventari.getMAXEQUIPS()+")!!\n");
        }
        
    }
    public static class excLlimitJugadors extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return("ERROR S'HA ALCANZAT EL LLIMIT DE JUGADORS CREATS("+Inventari.getMAXPLAYERS()+")!!\n");
        }
        
    }
    public static class excLlimitItems extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return("ERROR S'HA ALCANZAT EL LLIMIT DE ITEMS CREATS("+Inventari.getMAXITEMS()+")!!\n");
        }
        
    }
    public static class excLlimitItemsPerPlayer extends ExcepcioJocRol{

        @Override
        public String getMessage() {
            return("ERROR EL TEU INVENTARI ESTA PLE (MAX 3 ITEMS)!!\n");
        }
        
    }
}