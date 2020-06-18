import java.util.Scanner;

//this class handles user requests
public class User_Request {
    private String fileAddress;

    public String giveAddress(){
        String Address;
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the requested file:");
        Address=input.nextLine();
        return Address;
    }
    public User_Request(){
        this.fileAddress=giveAddress();
    }

}
