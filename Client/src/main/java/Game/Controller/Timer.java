package Game.Controller;

public class Timer extends Thread {
    GameController gameController;
    boolean resetTimer = false;
    public Timer(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void run() {
        while (!gameController.isFinished) {
            while (gameController.getRemaingTime() > 0) {
                try {
                    Thread.sleep(100);
                    gameController.setRemaingTime(gameController.getRemaingTime() - 0.1);
                    gameController.userGamePanel.getTimerLbl().setText(Integer.toString((int) gameController.getRemaingTime()));
                    gameController.userGamePanel.repaint();
                    if (resetTimer) {
                        resetTimer = false;
                        break;
                    }

                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

    }

    public void resetTimer(){
        resetTimer = true;
    }
}
