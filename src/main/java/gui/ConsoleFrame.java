package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsoleFrame extends JFrame {

    public static JTextArea console;

    static boolean SlashCommandsEnabled;
    static boolean LoggingEnabled;
    static boolean OutputLoggingEnabled;

    public ConsoleFrame() {
        super();
        setName("Tasque Manager");

        JPanel panel = new JPanel();

        //initialize text field for console
        console = new JTextArea();
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setEditable(false);

        panel.setLayout(new GridLayout(2,1));
        panel.setPreferredSize(new Dimension(400,300));

        //scrollable text field
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(console);


        panel.add(pane);
        panel.add(new ButtonPanel());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);

        add(panel);
        setVisible(true);
    }
}