import MainFrame.View.MainFrameView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainFrameView();
                } catch (IOException e) {
                    /// handle!
                    System.out.println("could not load main frame");
                }
            }
        });
        mainThread.start();
    }
}
