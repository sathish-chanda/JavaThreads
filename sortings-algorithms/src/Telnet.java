import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Telnet {
    String host;
    int port;

    public static void main(String[] args) {
        new Telnet().talkTo(args);
    }
    private void talkTo(String[] av) {
        if(av.length >= 1) {
           host = av[0];
        } else {
            host = "localhost";
        }
        if(av.length >= 2) {
            port = Integer.parseInt(av[1]);
        } else {
            port = 23;
        }
        System.out.println("Host " + host + "; Port " + port);
        try {
            Socket socket = new Socket(host,port);
            new Pipe(socket.getInputStream(),System.out).start();
            new Pipe(System.in, socket.getOutputStream()).start();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Connected OK");
    }
}
class Pipe extends Thread {
    BufferedReader br;
    PrintStream pr;

    Pipe(InputStream is, OutputStream os) {
        br = new BufferedReader(new InputStreamReader(is));
        pr = new PrintStream(os);
    }

    public void run() {
        String line;
        try {
            while((line = br.readLine()) != null) {
                pr.print(line);
                pr.print("\r\n");
                pr.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
