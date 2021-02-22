package learn.nio.io.bio.client;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by JAVA on 2019/4/14.
 */
@Slf4j
public class Client {
    private static int default_client_port = 7777;

    private static String default_server_ip="127.0.0.1";



    public static void send(String expression){

        send(default_client_port, expression);
    }

    public static void send(int port,String expression){
        log.info("client send info"+expression);

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(default_server_ip, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);




        }catch (Exception e){

            

        }finally {
            if(in !=null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

                in=null;
            }
            if(out !=null){
                out.close();
                out=null;
            }


        }
    }






}
