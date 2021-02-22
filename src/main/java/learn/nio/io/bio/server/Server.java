package learn.nio.io.bio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by JAVA on 2019/4/14.
 */
@Slf4j
public class Server {


    private static int default_port = 7777;

    private static ServerSocket serverSocket;


    public static void start() throws IOException{
        start(default_port);
    }


    public synchronized static void start(int port) throws IOException{
        if(serverSocket!=null){
            return;
        }
        try {

            serverSocket = new ServerSocket();
            log.info("server is starting ,port:"+port);
            while (true){
                Socket socket = serverSocket.accept();

                new Thread(new ServerHandler(socket)).start();

            }
        }finally {
            if(serverSocket!=null){
                log.info("server stop");
                serverSocket.close();
                serverSocket = null;

            }



        }
    }

}
