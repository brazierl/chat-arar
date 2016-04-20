/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author p1509283
 */
public class Client {
    private String addrServ; 
    private int portServ;

    public Client(String addrServ, int portServ) {
        this.addrServ = addrServ;
        this.portServ = portServ;
    }
    
    public boolean connect(){
        return "Connection OK".equals(sendPacket("hello serveur RX302"));
    }
    
     public String sendPacket(String message) {
        try {
            InetAddress ip_s = InetAddress.getByName(addrServ);
            DatagramSocket ds = new DatagramSocket();
            byte[] data = message.getBytes("ASCII");
            DatagramPacket dp = new DatagramPacket(data, data.length, ip_s, portServ);
            ds.send(dp);
            DatagramPacket dpAns = new DatagramPacket(new byte[128], 128);
            ds.receive(dpAns);
            return new String(dpAns.getData()).trim();
            
        } catch (SocketException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
