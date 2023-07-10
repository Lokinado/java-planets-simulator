package UI;

import javax.swing.*;
import Main.MainPanel;
import Objects.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterfaceController {
    private DisplayOptionsPanel DisplayOptions;
    private SimulationSettingsPanel SimulationSettings;
    public UserInterfaceController(){ }
    public void SetParentPanel(MainPanel parentPanel){
        DisplayOptions = new DisplayOptionsPanel("Display Options");
        DisplayOptions.setAlignmentX(0);
        parentPanel.add(DisplayOptions, BorderLayout.NORTH);

        SimulationSettings = new SimulationSettingsPanel("Simulation Settings");
        parentPanel.add(SimulationSettings, BorderLayout.SOUTH);
    }

    public ArrayList<JPanel> getPanels(){
        ArrayList<JPanel> ret = new ArrayList<JPanel>();
        ret.add(DisplayOptions);
        ret.add(SimulationSettings);
        return ret;
    }
}
