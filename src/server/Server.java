/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1509283
 */
public class Server {

    private DatagramSocket ds;
    private int portServer;
    private ArrayList<String> connectedClients;

    public Server(int portServer) {
        this.portServer = portServer;
        connectedClients = new ArrayList<>();
    }

    public void runServer() {
        try {
            ds = new DatagramSocket(portServer);
            ds.setSoTimeout(60000);
            do {
                DatagramPacket dp = new DatagramPacket(new byte[128], 128);
                ds.receive(dp);

                String nameClient = dp.getAddress().getHostName();
                String addrClient = dp.getAddress().getHostAddress();
                int portClient = dp.getPort();
                
                if(!connectedClients.contains(addrClient + portClient)){                
                    System.out.println("Nouveau Client : " + addrClient + ":" + portClient);
                }
                connectedClients.add(addrClient + portClient);
                final Communication com1 = new Communication(nameClient, portClient);
                new Thread() {
                    @Override
                    public void run() {
                        com1.exec();
                    }
                }.start();
            } while (true);

        } catch (SocketException ex) {
            Logger.getLogger(client.Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(client.Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isRunning() {
        return ds.isConnected();
    }
    
    public static void printServer(String msg){
        System.out.println(msg);
    }
}
