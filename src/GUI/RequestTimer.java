package GUI;

import Billboard.Request.CurrentBillboardRequest;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
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
        LocalDateTime localDate = LocalDateTime.now();
        System.out.println("Start TimerTask at: " + DayOfWeek.from(localDate)+", "+ localDate.toLocalTime());
        sendRequest();
    }
    public void sendRequest(){
        try{
            oos.writeObject(new CurrentBillboardRequest(LocalDateTime.now()));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
