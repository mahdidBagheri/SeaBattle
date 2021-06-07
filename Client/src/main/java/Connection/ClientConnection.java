package Connection;

import Connection.Exceptions.CouldNotConnectToServerException;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    Socket socket;

    public ClientConnection() throws IOException {
        this.socket = new Socket("localhost", 9000);
    }

    public boolean executeBoolean(ClientRequest clientRequest) throws IOException, ClassNotFoundException, CouldNotConnectToServerException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientRequest);
        long start = System.currentTimeMillis();
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if(System.currentTimeMillis() - start > 10e8){
                throw new CouldNotConnectToServerException(" could not connect to server");
            }
            if(socket.getInputStream().available() == 0){
                continue;
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();


            if(serverRequest.getCommand().equals("true")){
                return true;
            }
            else{
                return false;
            }

        }

    }
    public void execute(ClientRequest clientRequest){

    }

}
