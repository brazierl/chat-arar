/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import utils.Utils;
/**
 *
 * @author p1509019
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String addr = "localhost";
        String message = "hello serveur RX302";
        int port = 42081;
        Client clt = new Client(addr, port);
        System.out.println(clt.connect()?"Serveur RX302 ready : "+addr+":"+port:"Erreur Serveur RX302");
    }
    
}
