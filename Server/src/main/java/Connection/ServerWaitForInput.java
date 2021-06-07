package Connection;

import Connection.Exceptions.CouldNotConnectToServerException;

import java.io.IOException;
import java.net.Socket;

public class ServerWaitForInput {

    public static void waitForInput(Socket socket) throws CouldNotConnectToServerException, IOException {

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
            if (socket.getInputStream().available() == 0) {
                continue;
            }
            break;

        }
    }
}
