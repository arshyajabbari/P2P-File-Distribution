import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Peer extends Thread {

    private DatagramSocket udpsocket;
    public int udpport;
    //first index indicates if is in discovery faze, next one indicates
    //if is in sending file state and the next is for servicing the user
    private boolean[] state = new boolean[3];
    private byte[] reading_buffer = new byte[256];
    private byte[] writing_buffer = new byte[256];

    //for initializing the peer
    public Peer(int udpport) throws SocketException {
        this.udpport=udpport;
        this.udpsocket = new DatagramSocket(this.udpport);
    }

    //udp server making connection
    private void udplisten() throws Exception {
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


}
