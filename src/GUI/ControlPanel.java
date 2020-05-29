package GUI;

import Billboard.BillboardList;
import Billboard.DBConnection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private String[] billBoardCols;
    private Object[][] billBoardData;
    private JPanel pnlMenu;
    private JPanel pnlCenter;
    private JLabel lblName;
    private JTextField tfBillboardID;
    private JTextField tfBillboardName;
    private JTextField tfBillboardTitle;
    private JTextField tfBillboardCreator;
    private JTextField tfBillboardSchedule;
    private JPanel pnlSchedule;
    private JPanel pnlUserManagement;
    private JPanel pnlNewBillBoard;
    private JPanel pnlBillboardInformation;
    private JPanel pnlBillBoardList;
    private JPanel pnlBillboard;
    private JPanel pnlBillboardButton;
    private JScrollPane billBoardListScrollPane;
    private JButton btnBillboard;
    private JButton btnSchedule;
    private JButton btnNewBillBoard;
    private JButton btnUserManagement;
    private JButton btnDeleteBb;
    private JButton btnEditBb;
    private JButton btnExportBb;
    private JButton btnCreate;


    //TODO:REMOVE THIS WHEN SERVER IS CREATED
    Connection connection = DBConnection.getInstance();
    CallableStatement st = connection.prepareStatement("call userDisplay");


    public ControlPanel(String title) throws SQLException {
        super(title);
    }

    public String[] getBillBoardCols() {
        return billBoardCols;
    }

    public void setBillBoardCols(String[] billBoardCols) {
        this.billBoardCols = billBoardCols;
    }

    public Object[][] getBillBoardData() {
        return billBoardData;
    }

    public void setBillBoardData(Object[][] billBoardData) {
        this.billBoardData = billBoardData;
    }

    private void createGUI() {
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        lblName = createLabel(Color.WHITE,"Bill Board Control Panel");

        tfBillboardID = new JTextField();
        tfBillboardID.setEditable(false);
        tfBillboardName = new JTextField();
        tfBillboardName.setEditable(false);
        tfBillboardTitle = new JTextField();
        tfBillboardTitle.setEditable(false);
        tfBillboardCreator = new JTextField();
        tfBillboardCreator.setEditable(false);
        tfBillboardSchedule = new JTextField();
        tfBillboardSchedule.setEditable(false);

        pnlMenu = createPanel(Color.LIGHT_GRAY);
        pnlSchedule = createPanel(Color.GREEN);
        pnlUserManagement = createPanel(Color.BLUE);
        pnlBillboard = createPanel(Color.WHITE);
        pnlCenter = createPanel(Color.WHITE);
        btnBillboard = createButton("BillBoard");
        btnSchedule = createButton("Schedule");
        btnNewBillBoard = createButton("Creating Billboard");
        btnUserManagement = createButton("User Management");
        //jTable = createTable();
        ///Components in specific button click
        //Billboard
        btnDeleteBb = createButton("Delete");
        btnDeleteBb.setBackground(Color.RED);
        btnEditBb = createButton("Edit");
        btnEditBb.setBackground(Color.YELLOW);
        btnExportBb = createButton("Export");
        btnExportBb.setBackground(Color.GREEN);
        //Creating billboard
        btnCreate = createButton("Create");

        pnlBillboardInformation = new JPanel(new GridLayout(0,5,0,2));
        pnlBillboardInformation.setOpaque(true);
        pnlBillboardInformation.setBorder(BorderFactory.createTitledBorder("Billboard Information"));

        pnlBillboardButton = new JPanel(new FlowLayout());
        pnlBillboardButton.setOpaque(true);
        pnlBillboardButton.setBorder(BorderFactory.createTitledBorder("Command Panel"));

        pnlBillBoardList = new JPanel(new FlowLayout());
        pnlBillBoardList.setOpaque(true);
        pnlBillBoardList.setBorder(BorderFactory.createTitledBorder("Billboard List"));

        pnlNewBillBoard = new JPanel(new GridBagLayout());
        pnlNewBillBoard.setOpaque(true);
        pnlNewBillBoard.setBorder(BorderFactory.createTitledBorder("Register Form"));


        //Adjust the label in Center
        lblName.setPreferredSize(new Dimension( 900,100));
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setVerticalTextPosition(JLabel.CENTER);
        lblName.setFont(new Font("Serif", Font.PLAIN, 50));
        lblName.setIcon(new ImageIcon(getClass().getResource("/resources/logo.png")));

        //Adjust the label in main menu
        btnBillboard.setPreferredSize(new Dimension(200,100));
        btnBillboard.setBorderPainted(false);
        btnBillboard.setFont(new Font("Serif", Font.PLAIN, 20));
        btnBillboard.setContentAreaFilled(false);
        btnBillboard.setFocusPainted(false);
        btnBillboard.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBillboard.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBillboard.setIcon(new ImageIcon(getClass().getResource("/resources/billboardIcon.png")));

        btnSchedule.setPreferredSize(new Dimension(200,100));
        btnSchedule.setBorderPainted(false);
        btnSchedule.setFont(new Font("Serif", Font.PLAIN, 20));
        btnSchedule.setContentAreaFilled(false);
        btnSchedule.setFocusPainted(false);
        btnSchedule.setIcon(new ImageIcon(getClass().getResource("/resources/scheIcon.png")));
        btnSchedule.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSchedule.setHorizontalTextPosition(SwingConstants.CENTER);

        btnNewBillBoard.setPreferredSize(new Dimension(200,100));
        btnNewBillBoard.setBorderPainted(false);
        btnNewBillBoard.setFont(new Font("Serif", Font.PLAIN, 20));
        btnNewBillBoard.setContentAreaFilled(false);
        btnNewBillBoard.setFocusPainted(false);
        btnNewBillBoard.setIcon(new ImageIcon(getClass().getResource("/resources/newbbIcon.png")));
        btnNewBillBoard.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnNewBillBoard.setHorizontalTextPosition(SwingConstants.CENTER);

        btnUserManagement.setPreferredSize(new Dimension(200,100));
        btnUserManagement.setBorderPainted(false);
        btnUserManagement.setFont(new Font("Serif", Font.PLAIN, 18));
        btnUserManagement.setContentAreaFilled(false);
        btnUserManagement.setFocusPainted(false);
        btnUserManagement.setIcon(new ImageIcon(getClass().getResource("/resources/userIcon.png")));
        btnUserManagement.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnUserManagement.setHorizontalTextPosition(SwingConstants.CENTER);

//        btnDeleteBb.setPreferredSize(new Dimension(100,40));
//        btnDeleteBb.setBorderPainted(true);
//        btnDeleteBb.setFont(new Font("Serif", Font.PLAIN, 15));
//        btnDeleteBb.setContentAreaFilled(false);
//        btnDeleteBb.setFocusPainted(false);
//        btnDeleteBb.setVerticalTextPosition(SwingConstants.BOTTOM);
//        btnDeleteBb.setHorizontalTextPosition(SwingConstants.CENTER);

//        btnCreate.setPreferredSize(new Dimension(100,40));
//        btnCreate.setBorderPainted(true);
//        btnCreate.setFont(new Font("Serif", Font.PLAIN, 15));
//        btnCreate.setContentAreaFilled(false);
//        btnCreate.setFocusPainted(false);
//        btnCreate.setVerticalTextPosition(SwingConstants.BOTTOM);
//        btnCreate.setHorizontalTextPosition(SwingConstants.CENTER);


        setLayout(new BorderLayout());


        this.getContentPane().add(lblName,BorderLayout.NORTH);
        this.getContentPane().add(pnlMenu,BorderLayout.WEST);
        this.getContentPane().add(pnlCenter,BorderLayout.CENTER);

        pnlMenu.setLayout(new GridBagLayout());
        pnlMenu.setPreferredSize(new Dimension(200, 40));
        pnlCenter.setLayout(new GridBagLayout());

        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        //Panel related code will go here
        pnlBillboard.setPreferredSize(new Dimension(680, 460));
        pnlNewBillBoard.setPreferredSize(new Dimension(680, 460));
        pnlSchedule.setPreferredSize(new Dimension(680, 460));
        pnlUserManagement.setPreferredSize(new Dimension(680, 460));

        pnlBillboard.setVisible(true);
        pnlNewBillBoard.setVisible(false);
        pnlUserManagement.setVisible(false);
        pnlSchedule.setVisible(false);

        addToPanel(pnlCenter, pnlBillboard,constraints,0,0,1,1 );
        addToPanel(pnlCenter, pnlNewBillBoard,constraints,0,0,1,1 );
        addToPanel(pnlCenter, pnlSchedule,constraints,0,0,1,1 );
        addToPanel(pnlCenter, pnlUserManagement,constraints,0,0,1,1 );

        pnlBillboard.setLayout(new GridBagLayout());

        billBoardCols = new String[] {"ID","Name","Title","Creator","Schedule"};
        billBoardData = new Object[][] {
                {1,"Billboard A","Advertisement","Jaden","TimeStamp"},
                {2,"Billboard B","Advertisement","Jaden","TimeStamp"},
                {3,"Billboard C","Advertisement","Jaden","TimeStamp"},
                {4,"Billboard D","Advertisement","Jaden","TimeStamp"},
                {5,"Billboard E","Advertisement","Jaden","TimeStamp"},
        };

        addToPanel(pnlBillboardInformation, createLabel(Color.BLACK,"ID"), constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, createLabel(Color.BLACK,"Name"), constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, createLabel(Color.BLACK,"Title"), constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, createLabel(Color.BLACK,"Creator"), constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, createLabel(Color.BLACK,"Schedule"), constraints,1,1,1,1 );

        addToPanel(pnlBillboardInformation, tfBillboardID, constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardName, constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardTitle, constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardCreator, constraints,1,1,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardSchedule, constraints,1,1,1,1 );


        addToPanel(pnlBillboardButton,btnDeleteBb,constraints,1,0,1,1);
        addToPanel(pnlBillboardButton,btnEditBb,constraints,1,1,1,1);
        addToPanel(pnlBillboardButton,btnExportBb,constraints,1,2,1,1);

        pnlBillboardButton.setPreferredSize(new Dimension(1,80));
        //set up the scroll pane
        JTable billBoardTable = createTable(billBoardCols,billBoardData);
        billBoardListScrollPane = new JScrollPane(billBoardTable);
        billBoardListScrollPane.setPreferredSize(new Dimension(500,200));
        ListSelectionModel modelTable = billBoardTable.getSelectionModel();
        modelTable.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                setValueBillboardInfo(billBoardTable);
            }
        });

        addToPanel(pnlBillBoardList, billBoardListScrollPane, constraints,1,0,1,1 );
        addToPanel(pnlBillboard, pnlBillBoardList,constraints,1,0,1,1);
        addToPanel(pnlBillboard, pnlBillboardInformation,constraints,1,1,1,1);
        addToPanel(pnlBillboard, pnlBillboardButton,constraints,1,2,1,1);

        GridBagConstraints createBillboard = new GridBagConstraints();
        createBillboard.fill = GridBagConstraints.NONE;

        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard name"),createBillboard,0,0,1,1);
        addToPanel(pnlNewBillBoard,new JTextField(),createBillboard,1, 0 , 1 , 1);

        addToPanel(pnlMenu, btnBillboard, constraints,2,0,3,1);
        addToPanel(pnlMenu, btnSchedule,constraints,2,1,3,1);
        addToPanel(pnlMenu, btnNewBillBoard,constraints,2,2,3,1);
        addToPanel(pnlMenu, btnUserManagement,constraints,2,3,3,1);


        addToPanel(pnlNewBillBoard,btnCreate,constraints,7,8,3,1);

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

    private JTable createTable(String[] columns, Object[][] data){
        JTable table;
        table = new JTable(data,columns){
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for(int i = 0; i < columns.length; i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        table.setPreferredSize(new Dimension(500,300));
        table.setSelectionBackground(Color.CYAN);
        table.setShowGrid(false);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(30);

        return table;
    }

    private JButton createButton(String text){
        JButton jButton = new JButton();
        jButton.setText(text);
        jButton.addActionListener(this);
        return jButton;
    }


    @Override
    public void run() {
        createGUI();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBillboard){
            clearScreen();
            btnBillboard.setContentAreaFilled(true);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(false);
            pnlBillboard.setVisible(true);
            ;
            //pnlCenter.add();
        }
        else if(e.getSource() == btnNewBillBoard){
            clearScreen();
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(true);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(false);

            pnlNewBillBoard.setVisible(true);
        }

        else if(e.getSource() == btnSchedule){
            clearScreen();
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(true);
            btnUserManagement.setContentAreaFilled(false);
            pnlSchedule.setVisible(true);
            //pnlCenter.add();

        }
        else if(e.getSource() == btnUserManagement){
            clearScreen();
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(true);
            pnlUserManagement.setVisible(true);
            //pnlCenter.add();
        }else if(e.getSource() == btnEditBb){
            setBtnEditBb(true);

            //pnlCenter.add();
        }
    }

    public void clearScreen(){
        pnlNewBillBoard.setVisible(false);
        pnlUserManagement.setVisible(false);
        pnlSchedule.setVisible(false);
        pnlBillboard.setVisible(false);
    }

    public void setBtnEditBb(boolean bool){
        tfBillboardCreator.setEditable(bool);
        tfBillboardID.setEditable(bool);
        tfBillboardName.setEditable(bool);
        tfBillboardSchedule.setEditable(bool);
        tfBillboardTitle.setEditable(bool);
    }

    public void setValueBillboardInfo(JTable table){
        tfBillboardID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
        tfBillboardName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        tfBillboardTitle.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
        tfBillboardCreator.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
        tfBillboardSchedule.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
        setBtnEditBb(false);
    }
}
