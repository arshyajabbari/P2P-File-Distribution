import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//this class indicates both TCP server and client
public class TCP implements Runnable {
    private ServerSocket tcpsocket;
    private Socket socket;
    private int tcpport;
    private InetAddress serverAddress;

    private byte[] reading_buffer = new byte[256];
    private byte[] writing_buffer = new byte[256];

    public void TCP(ServerSocket tcpsocket, int tcpport,String address) throws IOException {
        this.tcpport=tcpport;
        this.tcpsocket=tcpsocket;
        this.serverAddress=InetAddress.getByName(address);
        socket= new Socket(address, tcpport);
    }

    //TCP server making connection
    public void tcpserver() throws IOException {
        System.out.println("-- Running UDP Server at " + InetAddress.getLocalHost() + "--");
        tcpsocket = new ServerSocket(tcpport);

        while (true) {
            socket=tcpsocket.accept();
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }
    }

    //tcp clients getting ready to receive and send files
    public void tcpclient() throws IOException {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + 'n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        socket.close();
    }

    public void run(){
        try {
            tcpserver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            tcpclient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
