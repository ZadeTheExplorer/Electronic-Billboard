package GUI;

import Billboard.Billboard;
import Billboard.Request.CurrentBillboardRequest;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.TimerTask;

public class RequestTimer extends TimerTask {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private BillboardViewer panel;
    private Billboard billboard;
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    public RequestTimer(ObjectOutputStream oos, ObjectInputStream ois, BillboardViewer panel){
        this.oos = oos;
        this.ois = ois;
        this.panel = panel;
    }
    @Override
    public void run() {
        LocalDateTime localDate = LocalDateTime.now();
        System.out.println("Start TimerTask at: " + DayOfWeek.from(localDate)+", "+ localDate.toLocalTime());
        sendRequest();
        getRespond();

    }
    public void sendRequest(){
        try{
            oos.writeObject(new CurrentBillboardRequest(LocalDateTime.now()));
            oos.flush();
            System.out.println("Request current billboard sented!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getRespond(){
        try{
            //TODO: REPLACE this code to a code that create a new billboard with info return from database
            Object o = ois.readObject();
            String[][] table = (String[][]) o;
            System.out.println(Arrays.deepToString(table));
            //TODO
            billboard = new Billboard(table[1][0],table[1][1],table[1][2],table[1][3],table[1][4],table[1][5],table[1][6],table[1][7]);
            panel.setBillboard(billboard);
            System.out.println("[VIEWER] Set new Billboard for BillboardViewer: "+ billboard.toString());
            //TODO:
            panel.repaint();
            panel.revalidate();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
