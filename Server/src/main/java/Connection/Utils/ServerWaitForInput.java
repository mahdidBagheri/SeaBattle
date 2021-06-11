package Connection.Utils;

import Connection.Exceptions.CouldNotConnectToServerException;

import java.io.IOException;
import java.net.Socket;

public class ServerWaitForInput {

    public void waitForInput(Socket socket) throws CouldNotConnectToServerException {

        long start = System.currentTimeMillis();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (System.currentTimeMillis() - start > 10e8) {
                throw new CouldNotConnectToServerException(" could not connect to server");
            }
            try {
                if (socket.getInputStream().available() == 0) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            break;

        }
    }
}
