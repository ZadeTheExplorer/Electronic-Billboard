package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JTextField tfNewBillboardID;
    private JTextField tfNewBillboardName;
    private JTextField tfNewBillboardTitle;
    private JTextField tfNewBillboardCreator;
    private JTextField tfNewBillboardSchedule;
    private JTextField tfUserID;
    private JTextField tfUserName;
    private JTextField tfUserUsername;
    private JTextField tfUserPassword;
    private JTextField tfUserPrivilege;
    private JTextField tfScheduleBillBoard;
    private JTextField tfScheduleTimestamp;
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
    private JButton btnCreateNewBb;
    private JButton btnUserDelete;
    private JButton btnUserEdit;
    private JButton btnUserCreate;
    private JButton btnDeleteSchedule;
    private JButton btnEditSchedule;


    //TODO:REMOVE THIS WHEN SERVER IS CREATED
//    Connection connection = DBConnection.getInstance();
//    CallableStatement st = connection.prepareCall("call usersDisplay()");
//    ResultSet result = st.executeQuery();


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

        tfNewBillboardID = new JTextField();
        tfNewBillboardName = new JTextField();
        tfNewBillboardTitle = new JTextField();
        tfNewBillboardCreator = new JTextField();
        tfNewBillboardSchedule = new JTextField();

        tfUserID = new JTextField();
        tfUserID.setEditable(false);
        tfUserUsername = new JTextField();
        tfUserUsername.setEditable(false);
        tfUserName = new JTextField();
        tfUserName.setEditable(false);
        tfUserPassword = new JTextField();
        tfUserPassword.setEditable(false);
        tfUserPrivilege = new JTextField();
        tfUserPrivilege.setEditable(false);

        tfScheduleBillBoard = new JTextField();
        tfScheduleTimestamp = new JTextField();

        pnlMenu = createPanel(Color.LIGHT_GRAY);
        pnlSchedule = createPanel(Color.WHITE);
        pnlUserManagement = createPanel(Color.WHITE);
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
        btnDeleteBb.setFocusPainted(false);
        btnEditBb = createButton("Edit");
        btnEditBb.setBackground(Color.YELLOW);
        btnEditBb.setFocusPainted(false);
        btnExportBb = createButton("Export");
        btnExportBb.setBackground(Color.GREEN);
        btnExportBb.setFocusPainted(false);
        //Creating billboard
        btnCreateNewBb = createButton("Create");

        //User Buttons
        btnUserDelete = createButton("Delete");
        btnUserDelete.setBackground(Color.RED);
        btnUserDelete.setFocusPainted(false);
        btnUserEdit = createButton("Edit");
        btnUserEdit.setBackground(Color.YELLOW);
        btnUserEdit.setFocusPainted(false);
        btnUserCreate = createButton("Create");
        btnUserCreate.setBackground(Color.GREEN);
        btnUserCreate.setFocusPainted(false);

        //Schedule Buttons
        btnDeleteSchedule = createButton("Delete");
        btnDeleteSchedule.setBackground(Color.RED);
        btnDeleteSchedule.setFocusPainted(false);
        btnEditSchedule = createButton("Edit");
        btnEditSchedule.setBackground(Color.YELLOW);
        btnEditSchedule.setFocusPainted(false);

        pnlBillboardInformation = new JPanel(new GridLayout(0,5,2,2));
        pnlBillboardInformation.setOpaque(true);
        pnlBillboardInformation.setBorder(BorderFactory.createTitledBorder("Billboard Information"));
        pnlBillboardInformation.setPreferredSize(new Dimension(1,80));

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

        btnCreateNewBb.setPreferredSize(new Dimension(100,40));
        btnCreateNewBb.setBorderPainted(true);
        btnCreateNewBb.setFont(new Font("Serif", Font.PLAIN, 15));
        btnCreateNewBb.setContentAreaFilled(false);
        btnCreateNewBb.setFocusPainted(false);
        btnCreateNewBb.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCreateNewBb.setHorizontalTextPosition(SwingConstants.CENTER);


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

        addToPanel(pnlBillboardInformation, tfBillboardID, constraints,0,0,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardName, constraints,0,0,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardTitle, constraints,0,0,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardCreator, constraints,0,0,1,1 );
        addToPanel(pnlBillboardInformation, tfBillboardSchedule, constraints,0,0,1,1 );


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


        GridBagConstraints constrainNewBillboard = new GridBagConstraints();
        constrainNewBillboard.fill = GridBagConstraints.NONE;
        constrainNewBillboard.anchor = GridBagConstraints.LINE_START;
        constrainNewBillboard.weightx = 10;
        constrainNewBillboard.weighty = 10;

        tfNewBillboardID.setPreferredSize(new Dimension(200,20));
        tfNewBillboardName.setPreferredSize(new Dimension(200,20));
        tfNewBillboardTitle.setPreferredSize(new Dimension(200,20));
        tfNewBillboardCreator.setPreferredSize(new Dimension(200,20));
        tfNewBillboardSchedule.setPreferredSize(new Dimension(200,20));

        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK,"Billboard Registration:"),constrainNewBillboard,0,0,1,1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard ID"),constrainNewBillboard,1,1,1,1);
        addToPanel(pnlNewBillBoard,tfNewBillboardID,constrainNewBillboard,2, 1 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Name"),constrainNewBillboard,1,2,1,1);
        addToPanel(pnlNewBillBoard,tfNewBillboardName,constrainNewBillboard,2, 2 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Title"),constrainNewBillboard,1,3,1,1);
        addToPanel(pnlNewBillBoard,tfNewBillboardTitle,constrainNewBillboard,2, 3 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Creator"),constrainNewBillboard,1,4,1,1);
        addToPanel(pnlNewBillBoard,tfNewBillboardCreator,constrainNewBillboard,2, 4 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Schedule"),constrainNewBillboard,1,5,1,1);
        addToPanel(pnlNewBillBoard,tfNewBillboardSchedule,constrainNewBillboard,2, 5 , 1 , 1);

        addToPanel(pnlNewBillBoard, btnCreateNewBb,constrainNewBillboard,3,3,1,1);

        JPanel pnlDisplayUserManagement = new JPanel(new FlowLayout());
        String[] userTableCols = new String[] {"ID","Name","Username","Password","Privilege"};
        Object[][] userTableData = new Object[][] {
                {1,"Jaden","jaden","password","admin"},
                {2,"Patrixe","patrixe","password","admin"},
                {3,"Edward","edward","password","admin"},
                {4,"Jinx","jinx","password","user"},
                {5,"Ezreal","ezreal","password","user"}
        };
        JTable userTable = createTable(userTableCols,userTableData);
        JScrollPane userPane = new JScrollPane(userTable);
        userPane.setPreferredSize(new Dimension(500,200));
        ListSelectionModel modelTableUser = userTable.getSelectionModel();
        modelTableUser.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                setValueUserInfo(userTable);
            }
        });

        pnlDisplayUserManagement.setOpaque(true);
        pnlDisplayUserManagement.setBorder(BorderFactory.createTitledBorder("User List"));
        pnlUserManagement.setLayout(new GridBagLayout());

        JPanel pnlUserManagementInfo = new JPanel(new GridLayout(0,5,1,1));
        pnlUserManagementInfo.setOpaque(true);
        pnlUserManagementInfo.setBorder(BorderFactory.createTitledBorder("User Information"));
        pnlUserManagementInfo.setPreferredSize(new Dimension(1,80));

        JPanel pnlUserManagementControl = new JPanel(new FlowLayout());
        pnlUserManagementControl.setOpaque(true);
        pnlUserManagementControl.setBorder(BorderFactory.createTitledBorder("Command Panel"));
        pnlUserManagementControl.setPreferredSize(new Dimension(1,80));

        addToPanel(pnlUserManagementControl, btnUserCreate, constraints,0,0,1,1);
        addToPanel(pnlUserManagementControl, btnUserEdit, constraints,0,0,1,1);
        addToPanel(pnlUserManagementControl, btnUserDelete, constraints,0,0,1,1);

        addToPanel(pnlUserManagementInfo, createLabel(Color.BLACK,"ID"), constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, createLabel(Color.BLACK,"Name"), constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, createLabel(Color.BLACK,"Username"), constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, createLabel(Color.BLACK,"Password"), constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, createLabel(Color.BLACK,"Privilege"), constraints,1,1,1,1 );

        addToPanel(pnlUserManagementInfo, tfUserID, constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, tfUserName, constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, tfUserUsername, constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, tfUserPassword, constraints,1,1,1,1 );
        addToPanel(pnlUserManagementInfo, tfUserPrivilege, constraints,1,1,1,1 );

        addToPanel(pnlDisplayUserManagement,userPane,constraints,1,0,1,1);
        addToPanel(pnlUserManagement,pnlDisplayUserManagement,constraints,0,0,1,1);
        addToPanel(pnlUserManagement,pnlUserManagementInfo,constraints,0,1,1,1);
        addToPanel(pnlUserManagement,pnlUserManagementControl,constraints,0,2,1,1);

        pnlSchedule.setLayout(new GridBagLayout());

        JPanel pnlBillBoardScheduleList = new JPanel();
        pnlBillBoardScheduleList.setOpaque(true);
        pnlBillBoardScheduleList.setBorder(BorderFactory.createTitledBorder("Schedule Information"));
        //pnlBillBoardScheduleList.setPreferredSize(new Dimension(1,80));

        JTable scheduleTable = createTable(new String[]{"Billboard","Schedule"}, new Object[][] {
                {"Billboard 1","Monday"},
                {"Billboard 2","Tuesday"},
                {"Billboard 3","Wednesday"},
                {"Billboard 4","Thursday"},
                {"Billboard 5","Friday"}
        });

        JScrollPane schedulePane = new JScrollPane(scheduleTable);
        schedulePane.setPreferredSize(new Dimension(500,200));
        ListSelectionModel modelTableSchedule = scheduleTable.getSelectionModel();
        modelTableSchedule.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                setValueScheduleInfo(scheduleTable);
            }
        });

        JPanel pnlBillBoardScheduleInformation = new JPanel(new GridLayout(0,2,1,1));
        pnlBillBoardScheduleInformation.setOpaque(true);
        pnlBillBoardScheduleInformation.setBorder(BorderFactory.createTitledBorder("Schedule Information"));
        pnlBillBoardScheduleInformation.setPreferredSize(new Dimension(1,80));

        JPanel pnlBillBoardScheduleControl = new JPanel(new FlowLayout());
        pnlBillBoardScheduleControl.setOpaque(true);
        pnlBillBoardScheduleControl.setBorder(BorderFactory.createTitledBorder("Command Panel"));
        pnlBillBoardScheduleControl.setPreferredSize(new Dimension(1,80));


        addToPanel(pnlBillBoardScheduleInformation, createLabel(Color.BLACK,"Billboard"),constraints,0,0,1,1);
        addToPanel(pnlBillBoardScheduleInformation, createLabel(Color.BLACK,"Schedule"),constraints,0,0,1,1);
        addToPanel(pnlBillBoardScheduleInformation, tfScheduleBillBoard,constraints,0,0,1,1);
        addToPanel(pnlBillBoardScheduleInformation, tfScheduleTimestamp,constraints,0,1,1,1);

        addToPanel(pnlBillBoardScheduleControl,btnEditSchedule,constraints,0,1,1,1);
        addToPanel(pnlBillBoardScheduleControl,btnDeleteSchedule,constraints,0,1,1,1);

        addToPanel(pnlBillBoardScheduleList, schedulePane, constraints, 0,0,1,1);

        addToPanel(pnlSchedule,pnlBillBoardScheduleList,constraints,0,0,1,1);
        addToPanel(pnlSchedule,pnlBillBoardScheduleInformation, constraints, 0,1,1,1);
        addToPanel(pnlSchedule,pnlBillBoardScheduleControl,constraints,0,2,1,1);

        addToPanel(pnlMenu, btnBillboard, constraints,2,0,3,1);
        addToPanel(pnlMenu, btnSchedule,constraints,2,1,3,1);
        addToPanel(pnlMenu, btnNewBillBoard,constraints,2,2,3,1);
        addToPanel(pnlMenu, btnUserManagement,constraints,2,3,3,1);




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

    //TODO: GET THE WHOLE COLUMN
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
//        Connection connection = DBConnection.getInstance();
//        CallableStatement st = connection.prepareCall("call displayAllBillboards()");
//        ResultSet result = st.executeQuery();
//        result.next();
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
        }
        else if(e.getSource() == btnEditBb){
            if(btnEditBb.getText().compareTo("Edit") == 0){
                editBillBoard(true);
                btnEditBb.setText("Save");
            }else{
                editBillBoard(false);
                btnEditBb.setText("Edit");
            }

            //pnlCenter.add();
        }
        else if(e.getSource() == btnUserEdit){
            if(btnUserEdit.getText().compareTo("Edit") == 0){
                editUser(true);
                btnUserEdit.setText("Save");
            }else{
                editUser(false);
                btnUserEdit.setText("Edit");
            }
        }
        else if(e.getSource() == btnUserCreate){
            if(btnUserCreate.getText().compareTo("Create") == 0){
                editUser(true);
                tfUserID.setText(null);
                tfUserName.setText(null);
                tfUserUsername.setText(null);
                tfUserPassword.setText(null);
                tfUserPrivilege.setText(null);
                btnUserCreate.setText("Save");
                btnUserCreate.setText("Save");
            }else{
                editUser(false);
                btnUserCreate.setText("Create");
            }
        }
        else if(e.getSource() == btnEditSchedule){
            if(btnEditSchedule.getText().compareTo("Edit") == 0){
                editSchedule(true);
                btnEditSchedule.setText("Save");
            }else{
                editSchedule(false);
                btnEditSchedule.setText("Edit");
            }
        }
    }

    public void clearScreen(){
        pnlNewBillBoard.setVisible(false);
        pnlUserManagement.setVisible(false);
        pnlSchedule.setVisible(false);
        pnlBillboard.setVisible(false);
    }

    public void editBillBoard(boolean bool){
        tfBillboardCreator.setEditable(bool);
        tfBillboardID.setEditable(bool);
        tfBillboardName.setEditable(bool);
        tfBillboardSchedule.setEditable(bool);
        tfBillboardTitle.setEditable(bool);
    }

    public void editUser(boolean bool){
        tfUserID.setEditable(bool);
        tfUserName.setEditable(bool);
        tfUserUsername.setEditable(bool);
        tfUserPassword.setEditable(bool);
        tfUserPrivilege.setEditable(bool);
    }

    public void editSchedule(boolean bool){
        tfScheduleBillBoard.setEditable(bool);
        tfScheduleTimestamp.setEditable(bool);
        btnEditSchedule.setText("Edit");
    }

    public void setValueBillboardInfo(JTable table){
        tfBillboardID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
        tfBillboardName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        tfBillboardTitle.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
        tfBillboardCreator.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
        tfBillboardSchedule.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
        btnEditBb.setText("Edit");
        editBillBoard(false);
    }

    public void setValueUserInfo(JTable table){
        tfUserID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
        tfUserName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        tfUserUsername.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
        tfUserPassword.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
        tfUserPrivilege.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
        editUser(false);
        btnUserCreate.setText("Create");
        btnUserEdit.setText("Edit");
    }

    public void setValueScheduleInfo(JTable table){
        tfScheduleBillBoard.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
        tfScheduleTimestamp.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        editSchedule(false);
        btnUserCreate.setText("Create");
    }
}
