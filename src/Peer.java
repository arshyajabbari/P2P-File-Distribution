import java.net.*;
import java.util.Scanner;

public class Peer extends Thread {

    private DatagramSocket udpsocket;
    private InetAddress serverAddress;
    private Scanner scanner;
    private String name;

    public int tcpport;
    public int udpport;

    //first index indicates if is in discovery faze, next one indicates
    //if is in sending file state and the next is for servicing the user
    private boolean[] state = new boolean[3];
    private byte[] reading_buffer = new byte[256];
    private byte[] writing_buffer = new byte[256];

    //for initializing the peer
    public Peer(int udpport,String destination,String name) throws SocketException, UnknownHostException {
        this.udpport=udpport;
        this.udpsocket = new DatagramSocket(this.udpport);
        this.serverAddress=InetAddress.getByName(destination);
        this.name=name;
    }

    public String getname(){
        return this.name;
    }

    //udp server making connection
    public void udpserver() throws Exception {
        System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
        String msg;

        while (true) {

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpsocket.receive(packet);
            msg = new String(packet.getData()).trim();

            System.out.println(
                    "Message from " + packet.getAddress().getHostAddress() + ": " + msg);
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


}
