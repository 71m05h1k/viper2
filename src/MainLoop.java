public class MainLoop implements Runnable {

    private Draw object;

    public MainLoop(Draw object) {
        this.object = object;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
                object.dvizhenie();
                object.updateUI();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}