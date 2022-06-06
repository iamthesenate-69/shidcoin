package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        setLayout(new GridLayout(3,1));

        JToggleButton enableSlashCommands = new JToggleButton("enableSlashCommands");
        JToggleButton enableLogging = new JToggleButton("enableLogging");
        JToggleButton enableOutputLogging = new JToggleButton("enableOutputLogging");

        enableSlashCommands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsoleFrame.SlashCommandsEnabled = enableSlashCommands.getModel().isSelected();
            }
        });
        enableLogging.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsoleFrame.LoggingEnabled = enableLogging.getModel().isSelected();
            }
        });
        enableOutputLogging.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsoleFrame.OutputLoggingEnabled = enableOutputLogging.getModel().isSelected();
            }
        });

        add(enableSlashCommands);
        add(enableLogging);
        add(enableOutputLogging);
    }
}
