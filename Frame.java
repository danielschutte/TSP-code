package tsp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Frame extends JFrame implements ActionListener {

    private JButton calculate, start, stop, send;
    protected JComboBox menu;
    private JFileChooser fileChooser;
    private FileInputStream fileInput;
    private JTextField text;
    private ImageIcon ii;
    protected JLabel background, label1, label2, label3, output1, output2, output3;
    private int i;
    private Panel panel;
    private Dialog dialog;
    private String[] menuItems =
    {
        "volledige enumeratie", "aangepast simpel gretig"
    };

    public Frame()
    {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("De magazijnrobot-simulator 2016");

        start = new JButton("Start");
        add(start);
        start.addActionListener(this);
        start.setBounds(200, 30, 120, 20);

        stop = new JButton("Stop");
        add(stop);
        stop.addActionListener(this);
        stop.setBounds(200, 50, 120, 20);

        text = new JTextField();
        add(text);
        text.setBounds(10, 30, 120, 20);

        send = new JButton("Zet positie");
        add(send);
        send.addActionListener(this);
        send.setBounds(10, 50, 120, 20);

        calculate = new JButton("Bereken");
        add(calculate);
        calculate.addActionListener(this);
        calculate.setBounds(200, 10, 120, 20);

        label1 = new JLabel("Algoritme: ");
        add(label1);
        label1.setBounds(350, 10, 100, 20);

        label2 = new JLabel("Aantal stoppen: ");
        add(label2);
        label2.setBounds(350, 40, 100, 20);

        label3 = new JLabel("Tijd: ");
        add(label3);
        label3.setBounds(350, 70, 100, 20);

        output1 = new JLabel("Default");
        add(output1);
        output1.setBounds(450, 10, 260, 20);

        output2 = new JLabel("Default");
        add(output2);
        output2.setBounds(450, 40, 260, 20);

        output3 = new JLabel("Default");
        add(output3);
        output3.setBounds(450, 70, 260, 20);

        menu = new JComboBox(menuItems);
        add(menu);
        menu.setSelectedIndex(1);
        menu.setBounds(10, 10, 120, 20);

        fileChooser = new JFileChooser();

        panel = new Panel();
        add(panel);
        panel.setBounds(200, 130, 450, 450);
        panel.setSize(401, 401);

        setVisible(true);
    }

    //  @Override
    public void getSelected()
    {
        menu.getSelectedObjects();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == start)
        {
            panel.counter = 0;
            panel.repaint();
            panel.animation.start();
        }
        if (e.getSource() == stop)
        {
            panel.locationRobot = 1;
            panel.moveRobot(panel.locationRobot);

        }

    }

    public String getTekst()
    {
        return text.getText();
    }

}
