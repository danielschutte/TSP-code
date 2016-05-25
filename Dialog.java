package tsp;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Dialog extends JDialog implements ActionListener {

    private Frame frame;
    private JButton ok, set;
    private JSlider slider;
    private JLabel label, label2, label3;
    private JTextField text;
    private JTextArea area;
    private int value;
    protected boolean closed = false;
    private Panel panel;
    protected JList list;
    protected DefaultListModel listModel;
    protected int i, j, x, y;
    protected ArrayList<StorageSpace> thisList = new ArrayList<>();
    private Storage thisStorage;
    protected RouteCheck result;
    private boolean isOk = false;

    public Dialog(Frame frame)
    {
        super(frame, true);
        setTitle("Set size");
        setSize(400, 450);
        setLocationRelativeTo(frame);

        setLayout(null);

        thisStorage = new Storage(5, 5);

        //Voor elk object wordt een setBounds() gebruikt zodat de exacte locatie bepaald kan worden op het scherm
        label2 = new JLabel("Give locations (1 - 100): ");
        add(label2);
        label2.setBounds(20, 20, 150, 20);

        text = new JTextField();
        add(text);
        text.setBounds(200, 20, 120, 20);

        set = new JButton("Set");
        add(set);
        set.addActionListener(this);
        set.setBounds(320, 20, 60, 20);

        label3 = new JLabel("Selected locations: ");
        add(label3);
        label3.setBounds(20, 50, 120, 20);

        listModel = new DefaultListModel();

        list = new JList(thisList.toArray());
        add(list);
        list.setBounds(200, 50, 120, 120);

        ok = new JButton("Ok");
        add(ok);
        ok.addActionListener(this);
        ok.setBounds(150, 350, 120, 20);

        label = new JLabel("Set width/heigth:");
        add(label);
        label.setBounds(150, 220, 120, 20);

        slider = new JSlider(2, 10, 5);
        add(slider);
        slider.setBounds(100, 250, 200, 100);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        // Het ophalen van de gegevens die in het dialoog worden ingevoerd, dus de grootte van het raster en de locaties van de items
        if (e.getSource() == set)
        {
            String s = text.getText();

            if (text.getText().length() > 0)
            {
                i = Integer.parseInt(s);

                j = slider.getValue() * slider.getValue();
                if (i <= j && i >= 0)
                {
                    LocationToXY(i);
                    listModel.addElement(i);
                    list.setModel(listModel);
                    text.setText("");

                } else
                {
                    // als de locatie van het item die ingevoerd wordt groter is dan het magazijn, komt er een dialoog met waarschuwing
                    JOptionPane.showMessageDialog(this, getMessage(), "Warning", 2);
                }
            }
        }

        //hier worden alle gegevens in een arraylist gezet zodat het algoritme bepaald kan worden met daarbij de snelste route
        if (e.getSource() == ok)
        {
            int size = listModel.getSize();

            for (StorageSpace sp : thisList)
            {
                thisStorage.addToList(sp);
            }

            result = thisStorage.calcFastest();
            isOk = true;

            dispose();
        }

    }

    public int getValue()
    {
        return slider.getValue();
    }

    public boolean getIsOk()
    {
        return isOk;
    }

    public void LocationToXY(int loc)
    {
        int width = getValue(), length;
        int x = 0, y = 0;

        while (loc > width)
        {
            y++;
            loc -= width;
        }
        x = loc;
        y = (y * (400 / getValue())) + (200 / getValue());
        x = (x * (400 / getValue())) - (200 / getValue());

        y = (y + 40) / 80;
        x = (x + 40) / 80;
        thisList.add(new StorageSpace(x, y));
        System.out.println(x + ", " + y);

    }

    public String getMessage()
    {
        return "Position unreachable in current table size.";
    }

    @Override
    public String toString()
    {
        return "Item: " + thisList + "\n";
    }

}
