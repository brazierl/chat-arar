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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1509283
 */
public class Server {

    private DatagramSocket ds;
    private int portServer;
    private String currentMessage;

    public Server(int portServer) {
        this.portServer = portServer;
    }

    public void runServer() {
        try {
            ds = new DatagramSocket(portServer);
            ds.setSoTimeout(60000);
            DatagramPacket dp = new DatagramPacket(new byte[128], 128);
            ds.receive(dp);

            String nameClient = dp.getAddress().getHostName();
            String addrClient = dp.getAddress().getHostAddress();
            int portClient = dp.getPort();
            /*
             DatagramSocket ds = new DatagramSocket();
             byte[] data = "Connection OK".getBytes("ASCII");
             DatagramPacket dpAns = new DatagramPacket(data, data.length, dp.getAddress(), dp.getPort());
             ds.send(dpAns);
             this.currentMessage = "Nouveau Client : " + addrClient + ":" + portClient;
            */

            final Communication com1 = new Communication(nameClient, portClient);
            new Thread() {
                public void run() {
                    com1.exec();
                }
            }.start();

        } catch (SocketException ex) {
            Logger.getLogger(client.Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(client.Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isRunning() {
        return ds.isConnected();
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void clearCurrentMessage() {
        this.currentMessage = null;
    }

}
