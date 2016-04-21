/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Map;
import java.util.Scanner;
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
        int port = 42081;
        Client clt = new Client(addr, port, 47000, 47100);

        if (clt.connect()) {
            System.out.println("Serveur RX302 ready : " + addr + ":" + port);
            String answer = "";
            String message;
            do {
                Scanner sc = new Scanner(System.in);
                message = sc.nextLine();
                if(!message.equals("#close"))
                    clt.sendPacket(message);
            } while (!message.equals("#close"));
        } else {
            System.out.println("Erreur Serveur RX302");
        }
    }

}
