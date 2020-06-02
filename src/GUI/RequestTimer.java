package GUI;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class RequestTimer extends TimerTask {
    private Socket socket;
    ObjectOutputStream oos;
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    public RequestTimer(Socket socket, ObjectOutputStream oos){
        this.socket = socket;
        this.oos = oos;
    }
    @Override
    public void run() {

        System.out.println("Start TimerTask at: " + fortime.format(new Date()));
        sendRequest();
        System.out.println("End TimerTask");
    }
    public void sendRequest(){
        try{
            oos.writeObject("Current");
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
