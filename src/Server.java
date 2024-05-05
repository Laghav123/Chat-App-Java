import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private ServerSocket serverSocket; // socket connects client to server

    public void startServer(){
        try {
            System.out.println("Server is starting, listening on port 1234");
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); // this is a blocking method, it will halt the program until client connect, and it will return a socket when client connects
                System.out.println("A New Client Joined The Chat");
                ClientHandler clientHandler = new ClientHandler(socket); // This class implement Runnable, and the override run method will be executed when thread.start() is called

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
//            e.printStackTrace();
//            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1235);
        Server server = new Server(serverSocket);
        server.startServer();
    }

}
