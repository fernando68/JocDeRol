/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io;


/**
 *
 * @author Fer
 */
public class Escriure {
    public static void imprimirStr(String msg){
        System.out.print(msg);
    }
    public static void imprimirInt(int n){
        System.out.println(n);
    }
    public static void msgError(){
        imprimirStr("Opcion incorrecta!\n");
    }
    public static void msgErrorNum(){
        imprimirStr("Error solo se aceptan numeros!!\nVuelve a introducir: ");
    }
   
}
