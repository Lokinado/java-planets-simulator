package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Collidable;
import Objects.CircleMass;
import Objects.Mass;
import Objects.Vector;
import Tools.*;

public class MainWindow extends JFrame {
    private MainPanel mainPanel;
    private List<Mass> Objects;

    public boolean isWindowAlive;

    private void updateObjects(){
        //System.out.println("I UPDATE");
        for(int i = 0 ; i < Objects.size(); i++){
            Mass origin = Objects.get(i);
            for(int j = 0 ; j < Objects.size(); j++){
                if( i == j ) continue;
                Mass target = Objects.get(j);

                Vector force = new Vector(origin.position, target.position);

                double distance = force.Length();
                force.Normalize();

                double forceValue = ( Constants.G.value * origin.mass * target.mass ) / (distance * distance);
                force.Multiply(forceValue);
                force.Multiply( 1.0 / origin.mass );

                origin.acceleration.Set(force);
            }
        }

        for (Mass origin : Objects) {
            Vector accelerationScaled = new Vector(origin.acceleration.x, origin.acceleration.y);
            accelerationScaled.Multiply((Constants.FrameTime.value / 1000.0) * Constants.TimeScale.value);

            origin.velocity.Add(accelerationScaled);

            Vector velocityScaled = new Vector(origin.velocity.x, origin.velocity.y);
            velocityScaled.Multiply((Constants.FrameTime.value / 1000.0) * Constants.TimeScale.value);

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
            while (isWindowAlive) {

                updateObjects();

                mainPanel.repaint();

                try {
                    Thread.sleep((long)Constants.FrameTime.value);
                } catch(InterruptedException e) {
                    return;
                }
            }
            System.exit(1);
        }
    }
    MainWindow(){
        super("Java Planets Simulator");

        this.isWindowAlive = true;
        this.Objects = new ArrayList<>();

        System.out.println((Constants.FrameTime.value / 1000.0) );

        Objects.add( new CircleMass(new Vector(300, 300), 1e15, 50) );
        Objects.add( new CircleMass(new Vector(600, 300), new Vector(0,9), 1e4, 10) );

        this.mainPanel = new MainPanel(Objects);

        add(mainPanel);
        setSize(800, 600);
        setVisible(true);

        // start render loop
        Thread loop = new Thread(new MainLoop());
        loop.start();
    }
}