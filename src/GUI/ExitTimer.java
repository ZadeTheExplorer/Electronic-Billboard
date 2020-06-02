package GUI;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.TimerTask;

public class ExitTimer extends TimerTask {
    private Socket socket;
    ObjectOutputStream oos;
    public ExitTimer(Socket socket, ObjectOutputStream oos){
        this.socket = socket;
        this.oos = oos;
    }
    @Override
    public void run() {
        System.out.println("Start TimerTask Exit");
        sendRequest();
        System.out.println("End TimerTask Exit");
    }
    public void sendRequest(){
        try{
            oos.writeObject("Exit");
            oos.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
