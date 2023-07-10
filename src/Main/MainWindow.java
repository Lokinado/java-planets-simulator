package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Collidable;
import Interfaces.Updateable;
import Objects.CircleMass;
import Objects.Mass;
import Objects.Vector;
import Tools.*;
import UI.UserInterfaceController;

public class MainWindow extends JFrame {
    private MainPanel mainPanel;
    private List<Mass> Objects;

    public boolean isWindowAlive;
    private UserInterfaceController UIController;

    private void updateObjects(){
        //System.out.println("I UPDATE");
        for(int i = 0 ; i < Objects.size(); i++){
            Mass origin = Objects.get(i);
            origin.acceleration.Set(new Vector(0,0));
            for(int j = 0 ; j < Objects.size(); j++){
                if( i == j ) continue;
                Mass target = Objects.get(j);

                Vector force = new Vector(origin.position, target.position);

                double distance = force.Length();
                force.Normalize();

                double forceValue = ( Constants.G.value * origin.mass * target.mass ) / (distance * distance);
                force.Multiply(forceValue);
                force.Multiply( 1.0 / origin.mass );

                origin.acceleration.Add(force);
            }
        }

        for (Mass origin : Objects) {
            Vector accelerationScaled = new Vector(origin.acceleration.x, origin.acceleration.y);
            accelerationScaled.Multiply((Constants.FrameTime.value / 1000.0) * Globals.timeScale);

            origin.velocity.Add(accelerationScaled);

            Vector velocityScaled = new Vector(origin.velocity.x, origin.velocity.y);
            velocityScaled.Multiply((Constants.FrameTime.value / 1000.0) * Globals.timeScale);

            origin.position.Add(velocityScaled);
        }
        for(int i = 0 ; i < Objects.size(); i++){
            Mass origin = Objects.get(i);
            for(int j = 0 ; j < Objects.size(); j++){
                if( i == j ) continue;
                Mass target = Objects.get(j);
                if( (origin instanceof Collidable) && (target instanceof Collidable) ){
                    boolean result = ((Collidable)origin).IsColliding(target);
                    if(result){
                        System.out.println("SOMETHING IS COLLIDING!!!");
                    }
                }
            }
        }
    }
    public class MainLoop implements Runnable {
        @Override
        public void run() {
            ArrayList<JPanel> panelsList = UIController.getPanels();
            long lastTime = System.nanoTime();
            while (isWindowAlive) {

                updateObjects();

                mainPanel.repaint();

                for( JPanel panel : panelsList){
                    if ( panel instanceof Updateable){
                        ((Updateable) panel).update();
                    }
                }

                try {
                    Thread.sleep((long)Constants.FrameTime.value);
                } catch(InterruptedException e) {
                    return;
                }
                Globals.frameRate = (int)(1000000000.0 / (System.nanoTime() - lastTime));
                lastTime = System.nanoTime();
            }
            System.exit(1);
        }
    }
    MainWindow(){
        super("Java Planets Simulator");

        this.isWindowAlive = true;
        this.Objects = new ArrayList<>();

        Objects.add( new CircleMass(new Vector(800, 450), 1e15, 50) );
        Objects.add( new CircleMass(new Vector(1100, 450), new Vector(0,9), 1e4, 10) );
        Objects.add( new CircleMass(new Vector(800, 150), new Vector(9,0), 1e4, 10) );

        this.UIController = new UserInterfaceController();

        this.mainPanel = new MainPanel(Objects, this.UIController);

        add(mainPanel);
        setSize(1600, 900);
        setVisible(true);

        // start render loop
        Thread loop = new Thread(new MainLoop());
        loop.start();
    }
}