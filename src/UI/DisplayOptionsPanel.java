package UI;

import Tools.Globals;
import Tools.Pair;
import UI.Actions.AccelerationDisplayChanged;
import UI.Actions.PathDisplayChanged;
import UI.Actions.VelocityDisplayChanged;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayOptionsPanel extends JPanel {
    private JLabel title;
    private ArrayList<Pair<Action, Boolean>> UIPrototype;
    private ArrayList<JCheckBox> UIElements;
    public DisplayOptionsPanel(String Title){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.title = new JLabel(Title);
        this.add(this.title);

        this.UIPrototype = new ArrayList<Pair<Action,Boolean>>();
        this.UIElements = new ArrayList<JCheckBox>();

        this.UIPrototype.add(
                new Pair<Action,Boolean>(
                        new VelocityDisplayChanged("Show Velocity", null,null,null),
                        Globals.isVelocityVectorVisible
                ));
        this.UIPrototype.add(
                new Pair<Action,Boolean>(
                        new AccelerationDisplayChanged("Show Acceleration", null,null,null),
                        Globals.isAccelerationVectorVisible
                ));
        this.UIPrototype.add(
                new Pair<Action,Boolean>(
                        new PathDisplayChanged("Show Paths", null,null,null),
                        Globals.isPathVisible
                ));

        for(int i = 0 ; i < this.UIPrototype.size(); i++){
            this.UIElements.add(new JCheckBox(this.UIPrototype.get(i).first));
            this.UIElements.get(i).setSelected(this.UIPrototype.get(i).second);
            this.add(UIElements.get(i));
        }
    }
}
