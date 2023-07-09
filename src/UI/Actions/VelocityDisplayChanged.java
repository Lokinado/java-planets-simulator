package UI.Actions;

import Tools.Globals;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VelocityDisplayChanged extends AbstractAction {
    public VelocityDisplayChanged(String text, ImageIcon icon, String desc, Integer mnemonic){
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }
    public void actionPerformed(ActionEvent e) {
        Globals.isVelocityVectorVisible = !Globals.isVelocityVectorVisible;
    }
}
