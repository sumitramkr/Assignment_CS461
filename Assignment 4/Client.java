import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ChatInterface , Runnable {
    private static final long serialVersionUID = 1L;
    private ChatInterface server;
    private String ClientName;
    boolean chkExit = true;
    boolean chkLog = false;
 
    protected Client(ChatInterface chatinterface,String clientname,String password) throws RemoteException {
        this.server = chatinterface;
        this.ClientName = clientname;
        chkLog = server.checkClientCredintials(this,clientname,password);
    }

    public void sendMessageToClient(String message) throws RemoteException {
        System.out.println(message); 
    }
 
    public void broadcastMessage(String clientname,String message) throws RemoteException {}
 
    public boolean checkClientCredintials(ChatInterface chatinterface ,String clientname,String password) throws RemoteException {
        return true;
    }

    public void run() {
        if(chkLog) {
            System.out.println("Connected To RMI Server");
            System.out.println("Type LOGOUT to Exit From The Service");
            System.out.println("Online now, type messages below\n");
            Scanner scanner = new Scanner(System.in);
            String message;
            
            while(chkExit) {
                message = scanner.nextLine();
                if(message.equals("LOGOUT")) {
                    chkExit = false;
                }
                else {
                    try {
                        server.broadcastMessage(ClientName, message);
                    }
                    catch(RemoteException e) {
                        e.printStackTrace();
                    }
                }  
            } 
            System.out.println("\nLogged out from the RMI Chat Program");
        }
        else {
            System.out.println("\nClient Name/Password Incorrect");
        }  
    }
    public static void main(String[] args) throws MalformedURLException,RemoteException,NotBoundException {
        Scanner scanner = new Scanner(System.in);
        String clientName = "";
        String clientPassword = "";
        System.out.println("\n201951156\n"); 
        System.out.println("\n----Welcome To RMI Chat Program----\n");  
        System.out.print("Enter Name: ");
        clientName = scanner.nextLine();
        System.out.print("Enter Password: ");
        clientPassword = scanner.nextLine();
        System.out.println("\nConnecting To RMI Server\n");

        ChatInterface chatinterface = (ChatInterface)Naming.lookup("rmi://localhost/RMIServer");
        new Thread(new Client(chatinterface , clientName , clientPassword)).start();
    }
 
}