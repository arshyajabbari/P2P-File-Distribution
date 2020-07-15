import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

//this class indicates both UDP server and client
public class UDP implements Runnable {
    private DatagramSocket udpsocket;
    private InetAddress serverAddress;
    private int udpport;
    private String input;
    private String output;

    private Scanner scanner;

    private byte[] reading_buffer = new byte[256];
    private byte[] writing_buffer = new byte[256];

    //initializing UDP Part of a Peer
    public UDP(int udpport,String address) throws UnknownHostException {
        this.udpport=udpport;
        this.serverAddress=InetAddress.getByName(address);
    }

    //udp server making connection
    public void udpserver() throws Exception {
        System.out.println("-- Running UDP Server at " + InetAddress.getLocalHost() + "--");
        //String msg;

        while (true) {

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpsocket.receive(packet);
            this.input = new String(packet.getData()).trim();

            System.out.println(
                    "Message from " + packet.getAddress().getHostAddress() + ": " + this.input);
        }
    }

    //udp client recieving lists
    public void udpclient() throws Exception {
        String in;
        System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
        while (true) {
            in = scanner.nextLine();

            DatagramPacket p = new DatagramPacket(
                    in.getBytes(), in.getBytes().length, serverAddress, udpport);

            this.udpsocket.send(p);
        }
    }

    public void run(){
        try {
            udpserver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            udpclient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
