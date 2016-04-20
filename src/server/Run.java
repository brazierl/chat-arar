/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

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
        /*for(Map.Entry<Integer,Boolean> es : Utils.portsScanner(1, 2000).entrySet()){
         System.out.println("Le port "+es.getKey()+" est "+(es.getValue()?"ouvert":"fermé"));
         }*/
        Server srv = new Server(42081);
        srv.runServer();
    }

}
//timeout0000
//        fin  de connection
