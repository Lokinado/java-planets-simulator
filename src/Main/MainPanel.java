package Main;

import java.awt.*;
import javax.swing.*;
import java.util.List;

import Interfaces.Drawable;
import Objects.Mass;
import UI.UserInterfaceController;

public class MainPanel extends JPanel {
    private List<Mass> Objects;

    private UserInterfaceController UIController;

    public MainPanel(List<Mass> Objects, UserInterfaceController UI){
        this.setLayout(new BorderLayout());

        this.Objects = Objects;

        this.UIController = UI;
        this.UIController.SetParentPanel(this);
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
            if( object instanceof Drawable){
                ((Drawable)object).Draw(g);
            }
        }

    }
}
