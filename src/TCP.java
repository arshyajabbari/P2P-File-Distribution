import java.io.*;
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
        //Initialize Sockets
        //ServerSocket ssock = new ServerSocket(5000);
        //Socket socket = ssock.accept();

        //The InetAddress specification
        //InetAddress IA = InetAddress.getByName("localhost");

        //Specify the file
        File file = new File("e:\\data1.bin");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        //Get socket's output stream
        OutputStream os = socket.getOutputStream();

        //Read File Contents into contents array
        byte[] contents;
        long fileLength = file.length();
        long current = 0;

        long start = System.nanoTime();
        while(current!=fileLength){
            int size = 10000;
            if(fileLength - current >= size)
                current += size;
            else{
                size = (int)(fileLength - current);
                current = fileLength;
            }
            contents = new byte[size];
            bis.read(contents, 0, size);
            os.write(contents);
            System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!");
        }

        os.flush();
        //File transfer done. Close the socket connection!
        socket.close();
        tcpsocket.close();
        System.out.println("File sent succesfully!");
    }


    //tcp clients getting ready to receive and send files
    public void tcpclient() throws IOException {
        //Initialize socket
        byte[] contents = new byte[10000];

        //Initialize the FileOutputStream to the output file's full path.
        FileOutputStream fos = new FileOutputStream("e:\\data2.bin");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socket.getInputStream();

        //No of bytes read in one read() call
        int bytesRead = 0;

        while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead);

        bos.flush();
        socket.close();

        System.out.println("File saved successfully!");
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
