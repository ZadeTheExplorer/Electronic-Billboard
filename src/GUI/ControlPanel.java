package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private JPanel pnlMenu;
    private JPanel pnlCenter;
    private JLabel lblName;
    private JLabel lblBillboard;
    private JLabel lblSchedule;
    private JLabel lblNewBillBoard;
    private JLabel lblUserManagement;

    private ControlPanel(String title){
        super(title);
    }

    private void createGUI() {
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lblName = createLabel(Color.WHITE,"Bill Board Control Panel");
        pnlMenu = createPanel(Color.GRAY);
        pnlCenter = createPanel(Color.WHITE);
        lblBillboard = createLabel(Color.BLUE, "BillBoard");
        lblSchedule = createLabel(Color.BLUE, "Schedule");
        lblNewBillBoard = createLabel(Color.BLUE, "New Billboard");
        lblUserManagement = createLabel(Color.BLUE, "User Management");

        //Adjust the label in Center
        lblName.setPreferredSize(new Dimension( 900,50));
        lblName.setForeground(Color.BLACK);
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setVerticalTextPosition(JLabel.CENTER);
        lblName.setFont(new Font("Serif", Font.PLAIN, 34));

        //Adjust the label in main menu
        lblBillboard.setFont(new Font("Serif", Font.PLAIN, 18));
        lblSchedule.setFont(new Font("Serif", Font.PLAIN, 18));
        lblNewBillBoard.setFont(new Font("Serif", Font.PLAIN, 18));
        lblUserManagement.setFont(new Font("Serif", Font.PLAIN, 18));
        setLayout(new BorderLayout());


        this.getContentPane().add(lblName,BorderLayout.NORTH);
        this.getContentPane().add(pnlMenu,BorderLayout.WEST);
        this.getContentPane().add(pnlCenter,BorderLayout.CENTER);

        pnlMenu.setLayout(new GridBagLayout());

        pnlMenu.setPreferredSize(new Dimension(200, 40));

        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
//Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        //Panel related code will go here

        addToPanel(pnlMenu, lblBillboard, constraints,2,0,3,1);
        addToPanel(pnlMenu, lblSchedule,constraints,2,1,3,1);
        addToPanel(pnlMenu, lblNewBillBoard,constraints,2,2,3,1);
        addToPanel(pnlMenu, lblUserManagement,constraints,2,3,3,1);

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
