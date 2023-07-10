package UI;

import Interfaces.Updateable;
import Tools.Globals;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SimulationSettingsPanel extends JPanel implements Updateable {
    private JLabel title;

    private JLabel timeScaleLabel;
    private JSpinner timeScaleSpinner;

    private JLabel frameRateLabel;
    public SimulationSettingsPanel(String Title){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.title = new JLabel(Title);
        this.add(this.title);

        JPanel timeScaleRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.timeScaleLabel = new JLabel("Time Scale:");
        timeScaleRow.add(this.timeScaleLabel);

        this.timeScaleSpinner = new JSpinner();
        SpinnerModel model = new SpinnerNumberModel(1, 0, 99, 1);
        this.timeScaleSpinner.setModel(model);
        timeScaleRow.add(this.timeScaleSpinner);

        this.timeScaleSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner eventEmitter = (JSpinner)e.getSource();
                Globals.timeScale = (Integer)eventEmitter.getValue();
            }
        });


        timeScaleRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(timeScaleRow);

        this.frameRateLabel = new JLabel("Frame rate: 0");
        this.add(this.frameRateLabel);
    }

    public void update(){
        this.frameRateLabel.setText("Frame rate: " + Globals.frameRate);
    }
}
