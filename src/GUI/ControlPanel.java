package GUI;

import ElectronicBillboardObject.Billboard;
import ElectronicBillboardObject.User;
import Request.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    static ObjectOutputStream output;
    static ObjectInputStream input;
    private static Object[][] billBoardData;
    private static Object[][] userData;
    private static Object[][] scheduleData;
    private JPanel pnlMenu;
    private JPanel pnlCenter;
    private JLabel lblName;
    private JLabel lblUserPassword;
    private JTextField tfBillboardName;
    private JTextField tfBillboardUsername;
    private JTextField tfBillboardBGColor;
    private JTextField tfBillboardTitleColor;
    private JTextField tfBillboardDescriptionColor;
    private JTextField tfBillboardURL;
    private JTextField tfBillboardTitle;
    private JTextField tfBillboardDescription;
    private JTextField tfNewBillboardName;
    private JTextField tfNewBillboardUsername;
    private JTextField tfNewBillboardBGColor;
    private JTextField tfNewBillboardTitleColor;
    private JTextField tfNewBillboardDescriptionColor;
    private JTextField tfNewBillboardURL;
    private JTextField tfNewBillboardTitle;
    private JTextField tfNewBillboardDescription;
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
    private JTable billboardTable;
    private JTable userTable;
    private JTable scheduleTable;
    private DefaultTableModel modelTableBillboard;
    private DefaultTableModel modelTableUser;
    private DefaultTableModel modelTableSchedule;
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

    private void createGUI() {
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        lblName = createLabel(Color.WHITE,"Bill Board Control Panel");

        tfBillboardName = new JTextField();
        tfBillboardName.setEditable(false);
        tfBillboardUsername = new JTextField();
        tfBillboardUsername.setEditable(false);
        tfBillboardBGColor = new JTextField();
        tfBillboardBGColor.setEditable(false);
        tfBillboardTitleColor = new JTextField();
        tfBillboardTitleColor.setEditable(false);
        tfBillboardDescriptionColor = new JTextField();
        tfBillboardDescriptionColor.setEditable(false);
        tfBillboardURL = new JTextField();
        tfBillboardURL.setEditable(false);
        tfBillboardTitle= new JTextField();
        tfBillboardTitle.setEditable(false);
        tfBillboardDescription = new JTextField();
        tfBillboardDescription.setEditable(false);

        tfNewBillboardName = new JTextField();
        tfNewBillboardUsername = new JTextField();
        tfNewBillboardBGColor = new JTextField();
        tfNewBillboardTitleColor = new JTextField();
        tfNewBillboardDescriptionColor = new JTextField();
        tfNewBillboardURL = new JTextField();
        tfNewBillboardTitle = new JTextField();
        tfNewBillboardDescription = new JTextField();

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
        tfScheduleBillBoard.setEditable(false);
        tfScheduleTimestamp = new JTextField();
        tfScheduleTimestamp.setEditable(false);

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

        pnlBillboardInformation = new JPanel(new GridLayout(0,8,2,2));
        pnlBillboardInformation.setOpaque(true);
        pnlBillboardInformation.setBorder(BorderFactory.createTitledBorder("Billboard Information"));
        pnlBillboardInformation.setPreferredSize(new Dimension(1,80));

        pnlBillboardButton = new JPanel(new FlowLayout());
        pnlBillboardButton.setOpaque(true);
        pnlBillboardButton.setBorder(BorderFactory.createTitledBorder("Command Panel"));

        pnlBillBoardList = new JPanel(new GridLayout(0,1,1,1));
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

        String[] billBoardCols = new String[] {"Name","User","BackgroundColor","TitleColor","DesColor","URL","Title","Description"};

        addToPanelWithLabelArray(billBoardCols,pnlBillboardInformation,constraints);

        JTextField[] billboardTextFields = new JTextField[] {tfBillboardName, tfBillboardUsername,
                tfBillboardBGColor,tfBillboardTitleColor,tfBillboardDescriptionColor,tfBillboardURL,tfBillboardTitle,tfBillboardDescription};

        addToPanelWithComponentArray(billboardTextFields,pnlBillboardInformation,constraints);

        JButton[] buttons = new JButton[] {btnDeleteBb, btnEditBb,btnExportBb};
        addToPanelWithComponentArray(buttons,pnlBillboardButton,constraints);

        pnlBillboardButton.setPreferredSize(new Dimension(1,80));
        //CREATE THE TABLE
        billboardTable = createTable(billBoardCols,billBoardData);
        //SET UP THE SCROLLPANE
        modelTableBillboard = new DefaultTableModel(billBoardData,billBoardCols);
        billboardTable.setModel(modelTableBillboard);
        //CENTER THE CELL'S DATA
        centerRowData(billboardTable);
        JScrollPane billBoardListScrollPane = new JScrollPane(billboardTable);
        billBoardListScrollPane.setPreferredSize(new Dimension(500,200));
        ListSelectionModel modelTableSelection = billboardTable.getSelectionModel();
        //SET UP SELECTION MODEL
        modelTableSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTableSelection.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    setValueBillboardInfo(billboardTable);
                }
            }
        });

        addToPanel(pnlBillBoardList, billBoardListScrollPane, constraints,0,0,1,1 );

        addToPanel(pnlBillboard, pnlBillBoardList,constraints,1,0,1,1);
        addToPanel(pnlBillboard, pnlBillboardInformation,constraints,1,1,1,1);
        addToPanel(pnlBillboard, pnlBillboardButton,constraints,1,2,1,1);


        GridBagConstraints constrainNewBillboard = new GridBagConstraints();
        constrainNewBillboard.fill = GridBagConstraints.NONE;
        constrainNewBillboard.anchor = GridBagConstraints.LINE_START;
        constrainNewBillboard.weightx = 10;
        constrainNewBillboard.weighty = 10;

        tfNewBillboardName.setPreferredSize(new Dimension(200,20));
        tfNewBillboardUsername.setPreferredSize(new Dimension(200,20));
        tfNewBillboardBGColor.setPreferredSize(new Dimension(200,20));
        tfNewBillboardTitleColor.setPreferredSize(new Dimension(200,20));
        tfNewBillboardDescriptionColor.setPreferredSize(new Dimension(200,20));
        tfNewBillboardURL.setPreferredSize(new Dimension(200,20));
        tfNewBillboardTitle.setPreferredSize(new Dimension(200,20));
        tfNewBillboardDescription.setPreferredSize(new Dimension(200,20));

        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK,"Billboard Registration:"),constrainNewBillboard,0,0,1,1);

        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Name"),constrainNewBillboard,1,1,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardName,constrainNewBillboard,2, 1 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Username"),constrainNewBillboard,1,2,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardUsername,constrainNewBillboard,2, 2 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Background Color"),constrainNewBillboard,1,3,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardBGColor,constrainNewBillboard,2, 3 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Title Color"),constrainNewBillboard,1,4,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardTitleColor,constrainNewBillboard,2, 4 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Description Color"),constrainNewBillboard,1,5,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardDescriptionColor,constrainNewBillboard,2, 5 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard URL"),constrainNewBillboard,1,6,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardURL,constrainNewBillboard,2, 6 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Title"),constrainNewBillboard,1,7,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardTitle,constrainNewBillboard,2, 7 , 1 , 1);
        addToPanel(pnlNewBillBoard,createLabel(Color.BLACK, "Billboard Description"),constrainNewBillboard,1,8,1,1);
        addToPanel(pnlNewBillBoard, tfNewBillboardDescription,constrainNewBillboard,2, 8 , 1 , 1);

        addToPanel(pnlNewBillBoard, btnCreateNewBb,constrainNewBillboard,3,4,1,1);

        JPanel pnlDisplayUserManagement = new JPanel(new FlowLayout());
        String[] userTableCols = new String[] {"Username","Privilege"};

        userTable = createTable(userTableCols,userData);
        JScrollPane userPane = new JScrollPane(userTable);
        userPane.setPreferredSize(new Dimension(500,200));
        modelTableUser = new DefaultTableModel(userData,userTableCols);
        userTable.setModel(modelTableUser);
        centerRowData(userTable);
        ListSelectionModel modelTableUser = userTable.getSelectionModel();
        modelTableUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTableUser.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    setValueUserInfo(userTable);
                }
            }
        });

        pnlDisplayUserManagement.setOpaque(true);
        pnlDisplayUserManagement.setBorder(BorderFactory.createTitledBorder("User List"));
        pnlUserManagement.setLayout(new GridBagLayout());

        JPanel pnlUserManagementInfo = new JPanel(new GridLayout(0,3,1,1));
        pnlUserManagementInfo.setOpaque(true);
        pnlUserManagementInfo.setBorder(BorderFactory.createTitledBorder("User Information"));
        pnlUserManagementInfo.setPreferredSize(new Dimension(1,80));

        JPanel pnlUserManagementControl = new JPanel(new FlowLayout());
        pnlUserManagementControl.setOpaque(true);
        pnlUserManagementControl.setBorder(BorderFactory.createTitledBorder("Command Panel"));
        pnlUserManagementControl.setPreferredSize(new Dimension(1,80));

        JButton[] userButtons = new JButton[] {btnUserCreate,btnUserEdit,btnUserDelete};
        addToPanelWithComponentArray(userButtons,pnlUserManagementControl,constraints);
        lblUserPassword = createLabel(Color.BLACK,"Password");
        JLabel[] userLabels = new JLabel[] {createLabel(Color.BLACK,"Username")
                ,createLabel(Color.BLACK,"Privilege"),lblUserPassword};
        addToPanelWithComponentArray(userLabels,pnlUserManagementInfo,constraints);

        JTextField[] userTextFields = new JTextField[]{tfUserName,tfUserPrivilege,tfUserPassword};
        addToPanelWithComponentArray(userTextFields,pnlUserManagementInfo,constraints);

        lblUserPassword.setVisible(false);
        tfUserPassword.setVisible(false);

        addToPanel(pnlDisplayUserManagement,userPane,constraints,1,0,1,1);
        addToPanel(pnlUserManagement,pnlDisplayUserManagement,constraints,0,0,1,1);
        addToPanel(pnlUserManagement,pnlUserManagementInfo,constraints,0,1,1,1);
        addToPanel(pnlUserManagement,pnlUserManagementControl,constraints,0,2,1,1);

        pnlSchedule.setLayout(new GridBagLayout());

        JPanel pnlBillBoardScheduleList = new JPanel();
        pnlBillBoardScheduleList.setOpaque(true);
        pnlBillBoardScheduleList.setBorder(BorderFactory.createTitledBorder("Schedule Information"));
        //pnlBillBoardScheduleList.setPreferredSize(new Dimension(1,80));

        JTable scheduleTable = createTable(new String[]{"ElectronicBillboardObject","Schedule"}, new Object[][] {
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


        addToPanel(pnlBillBoardScheduleInformation, createLabel(Color.BLACK, "ElectronicBillboardObject"),constraints,0,0,1,1);
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

    private void addToPanelWithLabelArray(String[] array, JPanel pnl, GridBagConstraints constraints){
        for(int i = 0; i < array.length; i ++){
            addToPanel(pnl, createLabel(Color.BLACK,array[i]), constraints,1,1,1,1 );
        }
    }

    private void addToPanelWithComponentArray(Component[] array, JPanel pnl, GridBagConstraints constraints){
        for(int i = 0; i < array.length; i ++){
            addToPanel(pnl, array[i], constraints,1,1,1,1 );
        }
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

    private static JTable createTable(String[] columns, Object[][] data){
        JTable table;
        table = new JTable(data,columns){
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        table.setPreferredSize(new Dimension(700,300));
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

    public static void getBillboardData(){
        try{
            output.writeObject(new DisplayAllBillboardsRequest());
            output.flush();
            Object list = input.readObject();
            String[][] table = (String[][]) list;
            System.out.println(Arrays.deepToString(table));
            billBoardData = new Object[table.length - 1][table[0].length];
            for(int i = 0; i < table.length - 1; i++){
                billBoardData[i] = table[i+1];
            }
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("ERROR");
        }
    }

    public static void deleteABillboard(String billboardName){
        try{
            output.writeObject(new DeleteBillboardRequest(billboardName));
            output.flush();
            System.out.println("Deleted!");
        } catch (IOException ioException) {
            System.out.println("ERROR");
        }
    }

    public static void editABillboard(Billboard billboard){
        try{
            output.writeObject(new EditBillboardRequest(billboard));
            output.flush();
            System.out.println("Edited!");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public static void createABillboard(Billboard billboard){
        try{
            output.writeObject(new AddBillboardRequest(billboard));
            output.flush();
            System.out.println("Created!");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public static void getUserData(){
        try{
            output.writeObject(new DisplayAllUsersRequest());
            output.flush();
            Object list = input.readObject();
            String[][] table = (String[][]) list;
            System.out.println(Arrays.deepToString(table));
            userData = new Object[table.length - 1][table[0].length];
            for(int i = 0; i < table.length - 1; i++){
                userData[i] = table[i+1];
            }
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("ERROR");
        }
    }

    public static void createAUser(User user){
        try{
            output.writeObject(new AddUserResquest(user));
            output.flush();
            System.out.println("Created!");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public static void deleteAUser(String username){
        try{
            output.writeObject(new DeleteUserRequest(username));
            output.flush();
            System.out.println("Deleted User!");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public static void updateAUserPassword(String username, String password){
        try{
            output.writeObject(new SetUserPassword(username,password));
            output.flush();
            System.out.println("Update Password!");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    public static void updateAUserPrivilege(String username, String password){
        try{
            output.writeObject(new SetUserPrivilegesRequest(username));
            output.flush();
            System.out.println("Update Password!");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }



    @Override
    public void run() {
        createGUI();

    }

    //TODO: GET THE WHOLE COLUMN
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Socket socketControlPanel = new Socket("localhost", 1234);

        output = new ObjectOutputStream(socketControlPanel.getOutputStream());
        input = new ObjectInputStream(socketControlPanel.getInputStream());

        output.writeObject("ControlPanel");
        output.flush();
        System.out.println("Identified!");
        System.out.println(input.readObject());
        
        getBillboardData();
        getUserData();
        SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));



//        Connection connection = DBConnection.getInstance();
//        CallableStatement st = connection.prepareCall("call displayAllBillboards()");
//        ResultSet result = st.executeQuery();
//        result.next();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //SELECT BILLBOARD SECTION
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
        //SELECT NEW BILLBOARD SECTION
        else if(e.getSource() == btnNewBillBoard){
            clearScreen();
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(true);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(false);

            pnlNewBillBoard.setVisible(true);
        }
        //SELECT SCHEDULE SECTION
        else if(e.getSource() == btnSchedule){
            clearScreen();
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(true);
            btnUserManagement.setContentAreaFilled(false);
            pnlSchedule.setVisible(true);
            //pnlCenter.add();

        }
        //SELECT USER SECTION
        else if(e.getSource() == btnUserManagement){
            clearScreen();
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(true);
            pnlUserManagement.setVisible(true);
            //pnlCenter.add();
        }

        //DELETE BILLBOARD
        else if(e.getSource() == btnDeleteBb){
            String billboardName = billboardTable.getValueAt(billboardTable.getSelectedRow(), 0).toString();
            deleteABillboard(billboardName);
            modelTableBillboard.removeRow(billboardTable.getSelectedRow());
            tfBillboardName.setText(null);
            tfBillboardUsername.setText(null);
            tfBillboardBGColor.setText(null);
            tfBillboardTitleColor.setText(null);
            tfBillboardDescriptionColor.setText(null);
            tfBillboardURL.setText(null);
            tfBillboardTitle.setText(null);
            tfBillboardDescription.setText(null);
        }

        //EDIT BILLBOARD
        else if(e.getSource() == btnEditBb){
            if(btnEditBb.getText().compareTo("Edit") == 0){
                editBillBoard(true);
                btnEditBb.setText("Save");
            }else{
                //create a new billboard after modified
                Billboard target = new Billboard(tfBillboardName.getText()
                        ,tfBillboardUsername.getText()
                        ,tfBillboardBGColor.getText(),tfBillboardTitleColor.getText()
                        ,tfBillboardDescriptionColor.getText()
                        ,tfBillboardURL.getText(),tfBillboardTitle.getText()
                        ,tfBillboardDescription.getText());
                System.out.println(target.toString());
                editABillboard(target);
                JTextField[] textFields = new JTextField[] {tfBillboardName,tfBillboardUsername,tfBillboardBGColor
                ,tfBillboardTitleColor,tfBillboardDescriptionColor,tfBillboardURL,tfBillboardTitle,tfBillboardDescription};
                setUpdateValue(modelTableBillboard,billboardTable,textFields);
                editBillBoard(false);
                btnEditBb.setText("Edit");
            }
        }
        //CREATE NEW BILLBOARD
        else if(e.getSource() == btnCreateNewBb){
            Billboard newBillboard = new Billboard(tfNewBillboardName.getText()
                    ,tfNewBillboardUsername.getText()
                    ,tfNewBillboardBGColor.getText(),tfNewBillboardTitleColor.getText()
                    ,tfNewBillboardDescriptionColor.getText()
                    ,tfNewBillboardURL.getText(),tfNewBillboardTitle.getText()
                    ,tfNewBillboardDescription.getText());
            System.out.println(newBillboard.toString());
            createABillboard(newBillboard);
            modelTableBillboard.addRow(new String[]{tfNewBillboardName.getText(),tfNewBillboardUsername.getText(),
            tfNewBillboardBGColor.getText(),tfNewBillboardTitleColor.getText(),tfNewBillboardDescriptionColor.getText(),
                    tfNewBillboardURL.getText(),tfNewBillboardTitle.getText(),tfNewBillboardDescription.getText()});
            tfNewBillboardName.setText(null);
            tfNewBillboardUsername.setText(null);
            tfNewBillboardBGColor.setText(null);
            tfNewBillboardTitleColor.setText(null);
            tfNewBillboardDescriptionColor.setText(null);
            tfNewBillboardURL.setText(null);
            tfNewBillboardTitle.setText(null);
            tfNewBillboardDescription.setText(null);
            btnBillboard.doClick();
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
                lblUserPassword.setVisible(true);
                tfUserPassword.setVisible(true);
                tfUserName.setText(null);
                tfUserPassword.setText(null);
                tfUserPrivilege.setText(null);
                btnUserCreate.setText("Save");
            }else{
                editUser(false);
                String privileges = tfUserPrivilege.getText();
                ArrayList<String> privilege = new ArrayList<>(Arrays.asList(privileges.split(",")));
                try {
                    User newUser = new User(tfUserName.getText(),tfUserPassword.getText(),privilege);
                    createAUser(newUser);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                modelTableUser.addRow(new String[] {tfUserName.getText(),tfUserPrivilege.getText()});
                lblUserPassword.setVisible(false);
                tfUserPassword.setVisible(false);
                btnUserCreate.setText("Create");
            }
        }
        else if(e.getSource() == btnUserDelete){
                String username = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
                deleteAUser(username);
                modelTableUser.removeRow(userTable.getSelectedRow());
                tfUserName.setText(null);
                tfUserPassword.setText(null);
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

    public void centerRowData(JTable table){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void editBillBoard(boolean bool){
        tfBillboardUsername.setEditable(bool);
        tfBillboardBGColor.setEditable(bool);
        tfBillboardTitleColor.setEditable(bool);
        tfBillboardDescriptionColor.setEditable(bool);
        tfBillboardURL.setEditable(bool);
        tfBillboardTitle.setEditable(bool);
        tfBillboardDescription.setEditable(bool);
    }

    public void editUser(boolean bool){
        tfUserName.setEditable(bool);
        tfUserPassword.setEditable(bool);
        tfUserPrivilege.setEditable(bool);
    }

    public void editSchedule(boolean bool){
        tfScheduleBillBoard.setEditable(bool);
        tfScheduleTimestamp.setEditable(bool);
        btnEditSchedule.setText("Edit");
    }

    public void setUpdateValue(TableModel tableModel, JTable table, JTextField[] textFields){
        int selectedRow = table.getSelectedRow();
        for(int i =0; i < textFields.length; i++){
            tableModel.setValueAt(textFields[i].getText(),selectedRow,i);
        }
    }

    public void setValueBillboardInfo(JTable table){
        JTextField[] textFields = new JTextField[]{tfBillboardName,tfBillboardUsername,tfBillboardBGColor,
                tfBillboardTitleColor,tfBillboardDescriptionColor,tfBillboardURL,tfBillboardTitle,tfBillboardDescription};
        setTextFields(billboardTable,textFields);
        btnEditBb.setText("Edit");
        editBillBoard(false);
    }

    public void setValueUserInfo(JTable table){
        JTextField[] textFields = new JTextField[]{tfUserName,tfUserPrivilege};
        setTextFields(userTable,textFields);
        editUser(false);
        tfUserPassword.setVisible(false);
        lblUserPassword.setVisible(false);
        btnUserCreate.setText("Create");
        btnUserEdit.setText("Edit");
    }

    public void setValueScheduleInfo(JTable table){
        JTextField[] textFields = new JTextField[]{tfScheduleBillBoard,tfScheduleTimestamp};
        setTextFields(scheduleTable,textFields);
        editSchedule(false);
        btnUserCreate.setText("Create");
    }

    public void setTextFields(JTable table, JTextField[] textFields){
        if(table.getSelectedRow() != -1 ){
            for(int i = 0; i < textFields.length; i++){
                textFields[i].setText(table.getValueAt(table.getSelectedRow(), i).toString());
            }
        }
    }
}
