package UI;

import javax.swing.*;
import Main.MainPanel;
import Objects.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterfaceController {
    private DisplayOptionsPanel DisplayOptions;
    public UserInterfaceController(MainPanel parentPanel){
        DisplayOptions = new DisplayOptionsPanel("Display Options");
        DisplayOptions.setAlignmentX(0);
        parentPanel.add(DisplayOptions, BorderLayout.NORTH);
    }
}
