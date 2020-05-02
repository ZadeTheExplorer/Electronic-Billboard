package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private JPanel pnlBtn;
    private JPanel pnlFive;
    private JLabel lblName;

    private ControlPanel(String title){
        super(title);
    }

    private void createGUI() {
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lblName = createLabel(Color.BLACK,"Bill Board Control Panel");
        pnlBtn = createPanel(Color.GRAY);
        pnlFive = createPanel(Color.WHITE);

        lblName.setPreferredSize(new Dimension( 900,100));
        lblName.setForeground(Color.BLACK);
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setVerticalTextPosition(JLabel.CENTER);
        lblName.setFont(new Font("Serif", Font.PLAIN, 34));
        setLayout(new BorderLayout());


        this.getContentPane().add(lblName,BorderLayout.NORTH);
        this.getContentPane().add(pnlBtn,BorderLayout.WEST);
        this.getContentPane().add(pnlFive,BorderLayout.CENTER);

        pnlBtn.setLayout(new GridBagLayout());

        JButton btnLoad = createButton("Load");
        JButton btnUnload = createButton("Unload");
        JButton btnFind = createButton("Find");
        JButton btnSwitch = createButton("Switch");
        pnlBtn.setPreferredSize(new Dimension(200, 40));

        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
//Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        //Panel related code will go here

        addToPanel(pnlBtn, btnLoad,constraints,2,0,3,1);
        addToPanel(pnlBtn, btnUnload,constraints,2,1,3,1);
        addToPanel(pnlBtn, btnFind,constraints,2,2,3,1);
        addToPanel(pnlBtn, btnSwitch,constraints,2,3,3,1);

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

    private JLabel createLabel(Color c, String text){
        JLabel lbl = new JLabel();
        lbl.setBackground(c);
        lbl.setText(text);
        return lbl;
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
