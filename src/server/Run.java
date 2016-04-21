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
        Server srv = new Server(42081);
        srv.runServer();
    }

}