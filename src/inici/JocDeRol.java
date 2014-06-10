/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inici;

import excepcions.ExcepcioJocRol;
import excepcions.ExcepcioJocRol.*;
import static io.Escriure.*;
import static io.Llegir.*;
import java.util.ArrayList;
import joc.*;

/**
 *
 * @author Fer
 */
public class JocDeRol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Joc();
    }
    
    //JUEGO
    public static void Joc(){
        boolean continuar;
        do{
            mostrarMenuPrincipal();
            continuar=true;
            switch(pedirOpcion()){
                case 1:
                    configuracio();
                    break;
                case 2:
                    try {jugar();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());
                        pause();}
                    break;
                case 3:
                    test();
                    imprimirStr("S'han creat correctament els objectes per defecte\n");
                    pause();
                    break;
                case 4:
                    if(eixir()){continuar = false;}
                    break;
                default:
                    msgError();
                    pause();
                    break;
            }
        }while(continuar);
    }
    public static void jugar() throws ExcepcioJocRol{
        if(Inventari.qJugadorsVius() < 2){throw new excInsuficientPlayers();}
        ArrayList<Player> players = Inventari.getJugadors();
        boolean error;
        do{
            for(Player p: players){
                if(p.isDead()){continue;}//Si el player esta muerto pasa al siguiente
                do{
                    error=false;
                    if(Inventari.qJugadorsVius()==1){break;}
                    imprimirStr("**********JOC DE ROL JUGANT**********\n");
                    imprimirStr("Le toca el torn al jugador: "+p.getName()+"\nA qui vols atacar: ");
                    try{
                        Player p2 = obtindreJugador();
                        p.attack(p2);
                    }catch(ExcepcioJocRol ex){
                        imprimirStr(ex.getMessage());
                        error = true;
                    }finally{
                        pause();
                    }
                }while(error);
            }
        }while(Inventari.qJugadorsVius()>1);
        try{
            imprimirStr("**********FIN PARTIDA**********\n"
                        + "Guanyador de la partida "
                        + obtindreGuanyador(players).getName()+"\n");//Cojemos el jugador que queda
        }catch (excJugadorMort ex){
            imprimirStr(ex.getMessage());
        }finally{
            pause();
            Inventari.restoreLifePlayers();
        }
    }
    public static Player obtindreGuanyador(ArrayList<Player> players) throws excJugadorMort{
        for(Player tmp:players){
            if(!tmp.isDead()){return tmp;}
        }
        throw new excJugadorMort();
    }
    //MENUS
    public static void mostrarMenuPrincipal(){
        imprimirStr("*****JOC DE ROL*****\n1. Configuració\n2. Jugar\n3. Test\n4. Eixir\n");
    }
    public static void mostrarMenuConf(){
        imprimirStr("*****CONFIGURACIO*****\n1. Gestió jugadors\n2. Gestio equips\n"
                + "3. Gestio objectes\n4. Eixir\n");
    }
    public static void mostrarMenuJugadors(){
        imprimirStr("*****JUGADORS*****\n1. Crear jugador\n2. Mostrar jugadors\n"
                + "3. Esborrar jugador\n4. Donar de baixa un equip"
                + "\n5. Assignar jugador a equip\n"
                + "6. Assignar objecte a jugador\n7. Desequipar un item\n"
                + "8. Modificar vida del jugadors\n"
                + "9. Eixir\n");
    }
    public static void mostrarMenuEquips(){
        imprimirStr("*****EQUIPS*****\n1. Crear equip\n2. Mostrar equips\n3. Esborrar equip\n"
                + "4. Assignar equip a jugador\n5. Donar de baixa un jugador\n"
                + "6. Eixir\n");
    }
    public static void mostrarMenuItems(){
        imprimirStr("*****OBJECTES*****\n1. Crear objecte\n2. Mostrar objectes\n3. Esborrar objecte\n"
                + "4. Assignar objecte a jugador\n5. Eixir\n");
    }
    
    //CONFIGURACIONES
    public static void configuracio(){
        boolean continuar;
        do{
            continuar=true;
            mostrarMenuConf();
            switch(pedirOpcion()){
                case 1:
                    gestioJugadors();
                    break;
                case 2:
                    gestioEquips();
                    break;
                case 3:
                    gestioItems();
                    break;
                case 4:
                    if(eixir()){continuar=false;};
                    break;
                default:
                    msgError();
                    break;
            }
        }while(continuar);
    }
    public static void gestioJugadors(){
        boolean continuar = true;
        do{
            mostrarMenuJugadors();
            switch(pedirOpcion()){
                case 1:
                    try {
                        crearPlayer();
                        imprimirStr("JUGADOR CREAT CORRECTAMENT\n");
                    } catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());
                    }
                    break;
                case 2:
                    try {Inventari.mostrarJugadors();
                    } catch (excNotPlayersExists ex) {
                        imprimirStr(ex.getMessage());
                    }
                    break;
                case 3:
                    try {borrarPlayer();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 4:
                    try {desAsignarEquip();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 5:
                    try {asignarJugadorAEquip();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 6:
                    try {asignarItemAJugador();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 7:
                    try {desAsignarItem();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 8:
                    setLifePlayers();
                    break;
                case 9:
                    if(eixir()){continuar=false;};
                    break;
                default:
                    msgError();
                    break;
            }
            if(!continuar==false)pause();
        }while(continuar);
    }
    public static void gestioEquips(){
        boolean continuar = true;
        do{
            mostrarMenuEquips();
            switch(pedirOpcion()){
                case 1:
                    try {
                        crearTeam();
                        imprimirStr("EQUIP CREAT CORRECTAMENT\n");
                    } catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());
                    }
                    break;
                case 2:
                    try {Inventari.mostrarTeams();
                    } catch (excNotTeamsExists ex) {
                        imprimirStr(ex.getMessage());
                    }
                    break;
                case 3:
                    try {borrarTeam();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 4:
                    try {asignarJugadorAEquip();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 5:
                    try {desAsignarPlayer();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 6:
                    if(eixir()){continuar=false;};
                    break;
                default:
                    msgError();
                    break;
            }
            if(!continuar==false)pause();
        }while(continuar);
    }
    public static void gestioItems(){
        boolean continuar = true;
        do{
            mostrarMenuItems();
            switch(pedirOpcion()){
                case 1:
                    try {crearItem();
                    } catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());
                    }
                    break;
                case 2:
                    Inventari.mostrarItems();
                    break;
                case 3:
                    try {borrarItem();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 4:
                    try {asignarItemAJugador();} catch (ExcepcioJocRol ex) {
                        imprimirStr(ex.getMessage());}
                    break;
                case 5:
                    if(eixir()){continuar=false;};
                    break;
                default:
                    msgError();
                    break;
            }
            if(!continuar==false)pause();
        }while(continuar);
    }
    
    //METODOS CREAR OBJETOS
    public static void crearPlayer() throws ExcepcioJocRol{
        if(Inventari.isFullJugadors()) throw new excLlimitJugadors();
        final int TOTALPDPA = 100;
        int life = Inventari.getLifeDefault();
        String nom;
        boolean error;
        msgCrear("JUGADOR");
        imprimirStr("Tipo de jugador (H)uman, (W)arrior, (A)lien: ");
        String tipo = preguntarTipoPlayer();
        do{
            error = false;
            nom = preguntarNomPlayer();
            if(Inventari.NomPlayerExists(nom)){
                imprimirStr("Ya ni ha un jugador amb eixe nom!!\n");
                pause();
                error = true;
            }
        }while(error);
        int pa = LlegirPoints("Punts d'atac: ");
        int pd = TOTALPDPA - pa;
        switch(tipo){
            case "H":
                Inventari.addPlayer(new Human(nom,pa,pd,life));
                break;
            case "W":
                Inventari.addPlayer(new Warrior(nom,pa,pd,life));
                break;
            case "A":
                Inventari.addPlayer(new Alien(nom,pa,pd,life));
                break;
            default:
                break;
        }
    }
    public static void crearTeam() throws ExcepcioJocRol{
        if(Inventari.isFullTeams()) throw new excLlimitEquips();
        msgCrear("EQUIP");
        Inventari.addTeam(new Team(preguntarNomTeam()));
    }
    public static void crearItem() throws ExcepcioJocRol{
        if(Inventari.isFullItems()) throw new excLlimitItems();
        boolean error;
        String nom;
        msgCrear("ITEM");
        do{
            error = false;
            nom = preguntarNomItem();
            if(Inventari.NomItemExists(nom)){
                error = true;
                try{throw new excNomItemExists();
                }catch(excNomItemExists ex){
                    imprimirStr(ex.getMessage());
                    pause();
                    error = true;
                }
            }
        }while(error);
        Inventari.addItem(new Item(nom,LlegirPoints("Punts de bonus d'atac: "),LlegirPoints("Punts de bonus de defensa: ")));
        imprimirStr("El objecte s'ha creat correctament\n");
    }
    
    //METODOS DE BORRAR OBJETOS
    public static void borrarPlayer() throws ExcepcioJocRol{
        Player p = obtindreJugador();
        Inventari.removePlayer(p);
        imprimirStr("El jugador s'ha esborrat Correctament\n");
    }
    public static void borrarTeam() throws ExcepcioJocRol{
        Team t = obtindreEquip();
        Inventari.removeTeam(t);
        imprimirStr("El Equip s'ha esborrat Correctament\n");
    }
    public static void borrarItem() throws ExcepcioJocRol{
        Item i = obtindreItem();
        Inventari.removeItem(i);
        imprimirStr("El Item s'ha esborrat Correctament\n");
    }
    
    //METODOS ASIGNAR
    public static void asignarJugadorAEquip() throws ExcepcioJocRol{
        isGrupsEmpty();//Lanza excepcion
        Player p = obtindreJugador();
        Inventari.mostrarNomTeamsCreats();
        Team team = obtindreEquip();
        if(p.containsTeam(team))
            throw new excRepeatTeamPlayer();
        p.addTeam(team);
        msgAsignacio();
    }
    public static void asignarItemAJugador() throws ExcepcioJocRol{
        isArmesEmpty();
        Player p = obtindreJugador();
        if(p.isFullItems()) throw new excLlimitItemsPerPlayer();
        Item item = obtindreItem();
        if(p.containsItem(item))
            throw new excRepeatItemPlayer();
        p.addItem(item);
        msgAsignacio();
    }
    
    //METODOS DESASIGNAR
    public static void desAsignarEquip() throws ExcepcioJocRol{
        isGrupsEmpty();
        Player player = obtindreJugador();
        imprimirStr(player.mostrarNomEquips()+"De quin equip tens vol anar, ");
        Team t = obtindreEquip();
        if(!player.containsTeam(t)){throw new excNoPertenixesEquip();}
        t.removePlayer(player); 
        imprimirStr("Ya no perteneixes a l'equip "+t.getName()+"\n");
    }
    public static void desAsignarPlayer() throws ExcepcioJocRol{
        isJugadorsEmpty();
        Team t = obtindreEquip();
        imprimirStr(t.str());
        imprimirStr("Quin jugador vols donar de baixa del equip, ");
        Player player = obtindreJugador();
        if(!player.containsTeam(t)){throw new excNoPertenixesEquip();}
        t.removePlayer(player);
        imprimirStr("Jugador donat de baixa correctament de l'equip "+t.getName()+"\n");
    }
    public static void desAsignarItem() throws ExcepcioJocRol{
        isArmesEmpty();
        Player player = obtindreJugador();
        imprimirStr(player.str());
        if(!player.isItemsEmpty()){
            imprimirStr("Quin item vols eliminar del teu inventari:, ");
            Item item = obtindreItem();
            if(!player.containsItem(item)){throw new excNoItemEquipat();}
            player.removeItem(item);
            imprimirStr("S'ha esborrat correctament "+item.getName()+" del teu inventari\n");
        }
    }
    
    //OTROS METODOS
    public static void test(){
        try{
            Inventari.addPlayer(new Human("Fernando",75,25,100));
            Inventari.addPlayer(new Warrior("Warrior",80,20,100));
            Inventari.addPlayer(new Alien("Alien",60,40,100));
            Inventari.addPlayer(new Human("Fernando2",69,31,100));
            Inventari.addPlayer(new Warrior("Warrior2",90,10,100));
            
        }catch (ExcepcioJocRol ex){}
        try{
            Inventari.addTeam(new Team("Fer"));
            Inventari.addTeam(new Team("Fer2"));
            Inventari.addTeam(new Team("Fer3"));
            Inventari.addTeam(new Team("Fer4"));
            Inventari.addTeam(new Team("Fer5"));
        }catch (ExcepcioJocRol ex){}
        try{
            Inventari.addItem(new Item("espada",50,50));
            Inventari.addItem(new Item("martillo",40,60));
            Inventari.addItem(new Item("escudo",70,30));
            Inventari.addItem(new Item("espada2",50,50));
            Inventari.addItem(new Item("martillo2",40,60));
            Inventari.addItem(new Item("escudo2",70,30));
        }catch (ExcepcioJocRol ex){}
    }
    public static void msgCrear(String tipo){
        imprimirStr("\n******CREACIO DE UN NOU "+tipo+"******\n");
    }
    public static int LlegirPoints(String msg){
        int num;
        boolean error;
        do{
            imprimirStr(msg);
            error=false;
            num = LlegirInt();
            if(num<0 || num>100){
                imprimirStr("MIN 0, MAX 100!!\n");
                error = true;
            }
        }while(error);
        return num;
    }
    public static void msgAsignacio(){
        imprimirStr("Asignacio correcta!\n");
    }
    
    //PLAYER
    public static String preguntarNomPlayer(){
        return LlegirString("Nom del Jugador: ");
    }
    public static String preguntarTipoPlayer(){
        String cadena;
        boolean error;
        do{
            error=false;
            cadena = LlegirString().toUpperCase();
            if(!(cadena.equals("H")||cadena.equals("W")||cadena.equals("A"))){
                imprimirStr("Por favor introduce H,W,A: ");
                error = true;
            }
        }while(error);
        return cadena;
    }
    public static Player obtindreJugador() throws ExcepcioJocRol{
        isJugadorsEmpty();
        boolean error;
        Player p = null;
        do{
            error = false;
            imprimirStr("\n*****LLISTA DE JUGADORS*****\n");
            Inventari.mostrarNomPlayersCreats();
            try {
                p = Inventari.getPlayer(preguntarNomPlayer());
            } catch (excNomPlayerNotExists ex) {
                imprimirStr(ex.getMessage());
                pause();
                
                error = true;
            }
        }while(error);
        return p;
    }
    public static void isJugadorsEmpty() throws excNotPlayersExists{
        if(Inventari.isJugadorsEmpty()){
            throw new excNotPlayersExists();
        }
    }
    public static void setLifePlayers(){
        int life;
        imprimirStr("VIDA ACTUAL DELS JUGADORS "+Inventari.getLifeDefault());
        do{
            imprimirStr("\nNou valor (MAX 250): ");
            life = LlegirInt();
        }while(life>250 || life < 1);
        Inventari.setLifeDefault(life);
        Inventari.restoreLifePlayers();
    }
    
    //TEAM
    public static String preguntarNomTeam(){
        return LlegirString("Nom del equip: ");
    }
    public static Team obtindreEquip() throws ExcepcioJocRol{
        isGrupsEmpty();
        boolean error;
        Team t = null;
        do{
            error = false;
            imprimirStr("*****LLISTA DE EQUIPS CREATS*****\n");
            Inventari.mostrarNomTeamsCreats();
            try{
                t = Inventari.getTeam(pedirOpcion()-1);
            }catch (excIndexOutOfBonds ex){
                imprimirStr(ex.getMessage()+" PER FAVOR INTROUDEIX UN NUMERO VALID\n");
                pause();
                error = true;
            }
        }while(error);
        return t;
    }
    public static void isGrupsEmpty() throws excNotTeamsExists{
        if(Inventari.isGrupsEmpty()){
            throw new excNotTeamsExists();
        }
    }
    
    //ITEM
    public static String preguntarNomItem(){
        return LlegirString("Nom del Item: ");
    }
    public static Item obtindreItem() throws ExcepcioJocRol{
        isArmesEmpty();
        boolean error;
        Item i = null;
        do{
            error = false;
            try {
                i = Inventari.getItem(preguntarNomItem());
            } catch (excNomItemNotExists ex) {
                imprimirStr(ex.getMessage());
                pause();
                Inventari.mostrarItems();
                error = true;
            }
        }while(error);
        return i;
    }
    public static void isArmesEmpty() throws excNotItemsExists{
        if(Inventari.isItemsEmpty()){
            throw new excNotItemsExists();
        }
    }
}