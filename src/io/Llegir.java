/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io;

import static io.Escriure.*;
import java.util.Scanner;

/**
 *
 * @author Fer
 */
public class Llegir {
    static Scanner entrada=new Scanner(System.in);
    
    //NUMEROS
    public static Float llegirFloat(){
        String cadena;
        Float num = null;
        boolean error;
        do{
            error=false;
            cadena=entrada.nextLine();
            try{
                num=Float.parseFloat(cadena);
            }catch(NumberFormatException nfe){
                msgErrorNum();
                error=true;
            }
        }while(error);
        return num;
    }
    public static int LlegirInt(){
        String dato;
        int num=0;
        boolean error;
        do{
            error=false;
            dato=entrada.nextLine();
        try{
            num=Integer.parseInt(dato);
        }catch(NumberFormatException nfe){
            msgErrorNum();
            error=true;
        }
        }while(error);
        return num;
    }
    
    //CADENA
    public static String LlegirString(){
        String cadena;
        cadena=entrada.nextLine();
        return cadena;
    }
    public static String LlegirString(String msg){
        String cadena;
        imprimirStr(msg);
        cadena=entrada.nextLine();
        return cadena;
    }
    
    //OTROS METODOS
    public static void pause(){
        imprimirStr("Presiona enter para continuar!");
        entrada.nextLine();
    }
    public static int pedirOpcion(){
        int opcion;
        imprimirStr("Elige una opcion: ");
        opcion=LlegirInt();
        return opcion;
    }
    
    public static boolean eixir(){
        boolean error;
        imprimirStr("Segur que vols eixir (s/n)?");
        do{
            String opcion = LlegirString();
            opcion = opcion.toUpperCase();
            switch(opcion){
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    imprimirStr("Per favor introdueix s/n: ");
                    error = true;
                    break;
            }
        }while(error);
        return false;
    }
}