import java.net.*;
import java.util.Scanner;
import java.util.Vector;

public class Peer implements Runnable {

    //private DatagramSocket udpsocket;
    //private InetAddress serverAddress;
    //private Scanner scanner;
    private String name;
    private String IP;
    private Discovery discovery;
    //private Vector<String[]> nodeList;
    private User_Request user_request;
    private File_Transfer file_transfer;
    private String directoryPath;

    public int tcpport;
    public int udpport;

    //first index indicates if is in discovery faze, next one indicates
    //if is in sending file state and the next is for servicing the user
    //private boolean[] state = new boolean[3];
    //private byte[] reading_buffer = new byte[256];
    //private byte[] writing_buffer = new byte[256];

    //for initializing the peer
    public Peer(int udpport,int tcpport,String directoryPath,String IP,String name) throws SocketException, UnknownHostException {
        this.udpport=udpport;
        this.tcpport=tcpport;
        //this.serverAddress=InetAddress.getByName(IP);
        this.IP=IP;
        this.directoryPath=directoryPath;
        this.name=name;
        this.discovery=new Discovery(name,IP);
        this.user_request=new User_Request();
        this.file_transfer=new File_Transfer(directoryPath);
    }

    @Override
    public void run() {

    }
}
