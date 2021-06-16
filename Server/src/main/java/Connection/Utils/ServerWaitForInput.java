package Connection.Utils;

import Connection.Exceptions.CouldNotConnectToServerException;
import Game.Controller.ServerGameController;

import java.io.IOException;
import java.net.Socket;

public class ServerWaitForInput {
    volatile boolean isFinished = false;

    public ServerWaitForInput() {
    }
    public ServerWaitForInput(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void waitForInput(Socket socket) throws CouldNotConnectToServerException {

        long start = System.currentTimeMillis();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }


            try {
                /*
                if(System.currentTimeMillis() - start > 5e3){
                    throw new CouldNotConnectToServerException(" could not connect to server");
                }
                 */
                if(isFinished){
                    break;
                }
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
