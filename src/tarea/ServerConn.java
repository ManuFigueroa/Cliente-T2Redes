package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ServerConn implements Runnable {
private BufferedReader in = null;

public ServerConn(Socket server) throws IOException {
    /* obtain an input stream from the server */
    in = new BufferedReader(new InputStreamReader(
                server.getInputStream()));
}

public void run() {
    String msg;
    try {
        /* loop reading messages from the server and show them 
         * on stdout */
        while ((msg = in.readLine()) != null) {
            System.out.println(msg);
        }
    } catch (IOException e) {
        System.err.println(e);
    }
  }
}