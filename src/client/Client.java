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
import java.util.Map;
import java.util.TreeMap;
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
    private int portClt;
    private int portMin;
    private int portMax;

    public Client(String addrServ, int portServ, int portMin, int portMax) {
        this.addrServ = addrServ;
        this.portServ = portServ;
        this.portMin = portMin;
        this.portMax = portMax;

        refreshPortClt();
    }

    private void refreshPortClt() {
        TreeMap<Integer, Boolean> ports = Utils.portsScanner(portMin, portMax);
        for (Map.Entry<Integer, Boolean> es : ports.entrySet()) {
            if (es.getValue()) {
                portClt = es.getKey();
                break;
            }
        }
    }

    public boolean connect() {
        return "Connection OK".equals(sendPacket("hello serveur RX302"));
    }

    public String sendPacket(String message) {
        try {
            InetAddress ip_s = InetAddress.getByName(addrServ);
            DatagramSocket ds = new DatagramSocket(portClt);
            ds.setSoTimeout(60000);
            byte[] data = message.getBytes("ASCII");
            DatagramPacket dp = new DatagramPacket(data, data.length, ip_s, portServ);
            ds.send(dp);
            DatagramPacket dpAns = new DatagramPacket(new byte[128], 128);
            ds.receive(dpAns);
            ds.close();
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
