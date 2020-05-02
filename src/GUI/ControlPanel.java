package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private JPanel pnlOne;
    private JPanel pnlTwo;
    private JPanel pnlBtn;
    private JPanel pnlFour;
    private JPanel pnlFive;

    private ControlPanel(String title){
        super(title);
    }

    private void createGUI() {
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnlOne = createPanel(Color.LIGHT_GRAY);
        pnlTwo = createPanel(Color.LIGHT_GRAY);
        pnlBtn = createPanel(Color.BLUE);
        pnlFour = createPanel(Color.LIGHT_GRAY);
        pnlFive = createPanel(Color.WHITE);

        setLayout(new BorderLayout());



        this.getContentPane().add(pnlOne,BorderLayout.SOUTH);
        this.getContentPane().add(pnlTwo,BorderLayout.EAST);
        this.getContentPane().add(pnlBtn,BorderLayout.WEST);
        this.getContentPane().add(pnlFour,BorderLayout.NORTH);
        this.getContentPane().add(pnlFive,BorderLayout.CENTER);

        pnlBtn.setLayout(new GridBagLayout());

        JButton btnLoad = createButton("Load");
        JButton btnUnload = createButton("Unload");
        JButton btnFind = createButton("Find");
        JButton btnSwitch = createButton("Switch");


        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
//Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        //Panel related code will go here

        addToPanel(pnlBtn, btnLoad,constraints,0,0,2,1);
        addToPanel(pnlBtn, btnUnload,constraints,3,0,2,1);
        addToPanel(pnlBtn, btnFind,constraints,0,2,2,1);
        addToPanel(pnlBtn, btnSwitch,constraints,3,2,2,1);

    }

    /**
     *
     * A convenience method to add a component to given grid bag
     * layout locations. Code due to Cay Horstmann
     *
     * @param c the component to add
     * @param constraints the grid bag constraints to use
     * @param x the x grid position
     * @param y the y grid position
     * @param w the grid width of the component
     * @param h the grid height of the component
     */
    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    private JPanel createPanel(Color c) {
        //Create a JPanel object and store it in a local var
        JPanel jp = new JPanel();
        jp.setBackground(c);
        return jp;
        //set the background colour to that passed in c
        //Return the JPanel object
    }

    private JButton createButton(String text){
        JButton jButton = new JButton();
        jButton.setText(text);
        jButton.addActionListener(this);
        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        createGUI();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
    }
}
