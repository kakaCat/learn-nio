package learn.nio.io.bio.server;

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
public class ServerHandler implements Runnable {


    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String expression;
            String result;
            while (true){
                if((expression = in.readLine())==null){
                    break;
                }
                log.info("server info:"+expression);


                result = Calculator.cal(expression);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getLocalizedMessage());
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
