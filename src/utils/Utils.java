/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

/**
 *
 * @author p1509019
 */
public class Utils {

    public static HashMap<Integer, Boolean> portsScanner(int pDeb, int pFin) {
        HashMap<Integer, Boolean> ports = new HashMap<>();
        for (int i = pDeb; i < pFin; i++) {
            ports.put(i, portScanner(i));
        }
        return ports;
    }

    private static boolean portScanner(int port) {
        try {
            DatagramSocket ds = new DatagramSocket(port);
            return true;
        } catch (SocketException ex) {
            return false;
        }
    }
    

}
