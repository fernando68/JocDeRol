/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inici;
import excepcions.*;
import excepcions.ExcepcioJocRol.*;
import static io.Escriure.*;
import java.util.ArrayList;
import joc.*;

/**
 *
 * @author Fernando
 */
public abstract class Inventari{
    private static ArrayList<Player> jugadors = new ArrayList<>();
    private static ArrayList<Team> grups = new ArrayList<>();
    private static ArrayList<Item> armes = new ArrayList<>();
    private static int lifeDefault = 100;
    private static final int MAXEQUIPS = 5,MAXPLAYERS = 15,MAXITEMS = 10;
    
    public static void setLifeDefault(int life){lifeDefault = life;}
    public static int getLifeDefault(){return lifeDefault;}
    public static int getMAXEQUIPS() {return MAXEQUIPS;}
    public static int getMAXPLAYERS() {return MAXPLAYERS;}
    public static int getMAXITEMS() {return MAXITEMS;}
    
    //METODOS ADD
    public static void addPlayer(Player p) throws ExcepcioJocRol{
        if(NomPlayerExists(p.getName())) throw new excNomTeamExists();
        if(isFullJugadors()) throw new excLlimitJugadors();
        jugadors.add(p);
    }
    public static void addTeam(Team t) throws ExcepcioJocRol{
        if(NomTeamExists(t.getName())) throw new excNomPlayerExists();
        if(isFullTeams()) throw new excLlimitEquips();
        grups.add(t);
    }
    public static void addItem(Item i) throws ExcepcioJocRol{
        if(NomItemExists(i.getName())) throw new excNomItemExists();
        if(isFullItems()) throw new excLlimitItems();
        armes.add(i);
    }
    //METODOS REMOVE
    public static void removePlayer(Player p){
        p.removePlayersReferencedTeams();
        jugadors.remove(p);
    }
    public static void removeTeam(Team t){
        t.removeTeamsReferencedPlayers();
        grups.remove(t);
    }
    public static void removeItem(Item i){
        i.removeItemsReferencedPlayer();
        armes.remove(i);
    }
    //MOSTRAR
    public static void mostrarJugadors() throws excNotPlayersExists{
        if(isJugadorsEmpty())
            throw new excNotPlayersExists();
        for(Player temp: jugadors){
            imprimirStr(temp.str()+"\n");
        }
    }
    public static void mostrarTeams() throws excNotTeamsExists{
        if(isGrupsEmpty())
            throw new excNotTeamsExists();
        for(Team temp: grups){
            imprimirStr(temp.str()+"\n");
        }
    }
    public static void mostrarItems(){
        if(isItemsEmpty()){
            try {
                throw new excNotItemsExists();
            } catch (excNotItemsExists ex) {
                imprimirStr(ex.getMessage());}
        }
        int cnt = 0;
        imprimirStr("*****ITEMS CREATS*****\n");
        for(Item temp: armes){
            cnt++;
            imprimirStr("\t"+cnt+temp.mostraItem()+"\n");
        }
    }
    public static void mostrarNomTeamsCreats(){
        int cnt = 0;
        for(Team tmp: grups){
            cnt++;
            imprimirStr(cnt+" "+tmp.mostrarNomEquip());
        }
    }
    public static void mostrarNomPlayersCreats(){
        int cnt = 0;
        for(Player tmp: jugadors){
            cnt++;
            imprimirStr(cnt+" "+tmp.mostraDades()+"\n");
        }
    }
    //METODOS PLAYER
    public static boolean isJugadorsEmpty(){
        return jugadors.isEmpty();
    }
    public static boolean NomPlayerExists(String nom){
        boolean encontrado = false;
        for(Player tmp: jugadors){
            if(tmp.getName().equalsIgnoreCase(nom)){
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    public static Player getPlayer(String nom) throws ExcepcioJocRol{
        if(isJugadorsEmpty())
            throw new excNotPlayersExists();
        for(Player tmp: jugadors){
            if(tmp.getName().equalsIgnoreCase(nom))
                return tmp;
        }
        throw new excNomPlayerNotExists();
    }
    public static ArrayList<Player> getJugadors() {
        return jugadors;
    }
    public static int getPlayerSize(){
        return jugadors.size();
    }
    public static void restoreLifePlayers(){
        for(Player tmp: jugadors){
            tmp.setLife(lifeDefault);
        }
    }
    public static int qJugadorsVius(){
        int jugadorsVius = 0;
        for(Player tmp: jugadors){
            if(!tmp.isDead()){
                jugadorsVius++;
            }
        }
        return jugadorsVius;
    }
    public static boolean isFullJugadors(){return jugadors.size()==MAXPLAYERS;}
    //METODOS TEAM
    public static boolean isGrupsEmpty(){
        return grups.isEmpty();
    }
    public static boolean NomTeamExists(String nom){
        boolean econtrado = false;
        for(Team tmp: grups){
            if(tmp.getName().equalsIgnoreCase(nom)){
                econtrado = true;
                break;
            }
        }
        return econtrado;
    }
    public static Team getTeam(int index) throws ExcepcioJocRol{
        if(isGrupsEmpty())
            throw new excNotTeamsExists();
        try{return grups.get(index);
        }catch(IndexOutOfBoundsException ex){
            throw new excIndexOutOfBonds();
        }
    }
    public static boolean isFullTeams(){return grups.size()==MAXEQUIPS;}
    //METODOS ITEM
    public static boolean isItemsEmpty(){
        return armes.isEmpty();
    }
    public static boolean NomItemExists(String nom){
        boolean encontrado = false;
        for(Item tmp: armes){
            if(tmp.getName().equalsIgnoreCase(nom)){
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    public static Item getItem(String nom) throws ExcepcioJocRol{
        if(isItemsEmpty())
            throw new excNotItemsExists();
        for(Item tmp: armes){
            if(tmp.getName().equalsIgnoreCase(nom))
                return tmp;
        }
        throw new excNomItemNotExists();
    }
    public static boolean isFullItems(){return armes.size()==MAXITEMS;}
}