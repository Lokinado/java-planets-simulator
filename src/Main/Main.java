package Main;

public class Main {
    public static void main(String[] args){

        MainWindow app = new MainWindow();

        app.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                app.isWindowAlive = false;
            }
        });
    }
}