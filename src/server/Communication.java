/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1509283
 */
public class Communication {

    private String ipClient;
    private int portClient;
    private DatagramSocket ds;

    public Communication(String ipClient, int portClient) {
        this.ipClient = ipClient;
        this.portClient = portClient;
        try {
            ds = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exec() {
        try {
            byte[] data = "Connection OK".getBytes("ASCII");
            DatagramPacket dpAns;
            dpAns = new DatagramPacket(data, data.length, InetAddress.getByName(ipClient), portClient);
            DatagramPacket dp = new DatagramPacket(new byte[128], 128);
            ds.send(dpAns);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
