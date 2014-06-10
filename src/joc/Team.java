/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package joc;
import java.util.*;
/**
 *
 * @author Fer
 */
public class Team{
    //Atributos
    private String name;
    private ArrayList<Player> players = new ArrayList<>();
    //Contructores
    public Team(String name){
        this.name = name;
    }
    //Metodos
    public String getName(){return this.name;}
    public String str(){
        int cnt = 0;
        String cadena = mostrarNomEquip();
        for(Player temp: this.players){
            cnt++;
            cadena+="\t"+cnt+" Jugador: "+temp.str();
        }
        if(players.isEmpty()){
            cadena+="\tNO NI HAN JUGADORS!!\n";
        }
        return cadena;
    }
    public String mostrarNomEquip(){
        return "Equip: "+this.name+":\n";
    }
    public void addPlayer(Player p){
        if(!this.players.contains(p)){
            this.players.add(p);
            p.addTeam(this);
        }
    }
    public void removePlayer(Player p){
        if(this.players.contains(p)){
            this.players.remove(p);
            p.removeTeam(this);
        }
    }
    public void removeTeamsReferencedPlayers(){
        for (int i = 0; i < this.players.size(); i++) {
            players.get(i).removeTeam(this);
        }
    }
}