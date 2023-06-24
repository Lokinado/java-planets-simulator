import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import Objects.CircleMass;
import Objects.Mass;
import Objects.Vector;
import Tools.*;

public class MainWindow extends JFrame {
    private MainPanel mainPanel;
    private List<Mass> Objects;

    private void updateObjects(){
        //System.out.println("I UPDATE");
        for(int i = 0 ; i < Objects.size(); i++){
            Mass origin = Objects.get(i);
            origin.acceleration.x = 0;
            origin.acceleration.y = 0;
            for(int j = 0 ; j < Objects.size(); j++){
                if( i == j ) continue;
                Mass target = Objects.get(j);

                Vector force = new Vector(origin.position, target.position);

                double distance = force.Length();
                if( distance < 50 )continue;
                force.Normalize();

                double forceValue = ( Constants.G.value * origin.mass * target.mass ) / (distance * distance);
                force.Multiply(forceValue);
                force.Multiply( 1.0 / origin.mass );

                origin.acceleration.Add(force);
            }
        }
        //System.out.println(Double.toString(Objects.get(0).acceleration.x) + " " + Double.toString(Objects.get(0).acceleration.y));
        //System.out.println(Double.toString(Objects.get(0).velocity.x) + " " + Double.toString(Objects.get(0).velocity.y));
        //System.out.println(Double.toString(Objects.get(0).position.x) + " " + Double.toString(Objects.get(0).position.y));

        for(int i = 0 ; i < Objects.size(); i++){
            Mass origin = Objects.get(i);

            Vector accelerationScaled = new Vector(origin.acceleration.x, origin.acceleration.y);
            accelerationScaled.Multiply((Constants.FrameTime.value / 1000.0) * Constants.TimeScale.value);

            origin.velocity.Add(accelerationScaled);

            Vector velocityScaled = new Vector(origin.velocity.x, origin.velocity.y);
            velocityScaled.Multiply((Constants.FrameTime.value / 1000.0) * Constants.TimeScale.value);

            origin.position.Add(velocityScaled);
        }

    }
    public class MainLoop implements Runnable {
        @Override
        public void run() {
            while (true) {

                updateObjects();

                mainPanel.repaint();

                try {
                    Thread.sleep((long)Constants.FrameTime.value);
                } catch(InterruptedException e) {
                    return;
                }
            }
        }
    }
    MainWindow(){
        super("Java Planets Simulator");

        this.Objects = new ArrayList<>();

        System.out.println((Constants.FrameTime.value / 1000.0) );

        Objects.add( new CircleMass(new Vector(300, 300), 1e13) );
        Objects.add( new CircleMass(new Vector(600, 300), new Vector(0,0), 1e13) );

        this.mainPanel = new MainPanel(Objects);

        add(mainPanel);
        setSize(800, 600);
        setVisible(true);

        // start render loop
        Thread loop = new Thread(new MainLoop());
        loop.start();
    }
}