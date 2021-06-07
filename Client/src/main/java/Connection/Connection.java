package Connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    Socket socket;

    public Connection() throws IOException {
        this.socket = new Socket("localhost", 9000);
    }

    public boolean executeBoolean(ClientRequest clientRequest) throws IOException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientRequest);

        Scanner scanner = new Scanner(socket.getInputStream());
        String serverAnswer = scanner.nextLine();

        if(serverAnswer.equals("true")){
            return true;
        }
        else{
            return false;
        }

    }
    public void execute(ClientRequest clientRequest){

    }

}
