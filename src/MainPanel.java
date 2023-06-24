import java.awt.*;
import javax.swing.*;
import java.util.List;

import Objects.CircleMass;
import Objects.Mass;
import Objects.Vector;
import Tools.*;
public class MainPanel extends JPanel {
    private List<Mass> Objects;

    public MainPanel(List<Mass> Objects){
        this.Objects = Objects;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        //INIT
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D)graphics;

        //Graphics Renderer Config
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Stroke k = new BasicStroke(3);
        g.setStroke(k);

        for( Mass object : Objects){
            object.Draw(g);
        }

        //System.out.println("RERENDER!!!");

    }
}
