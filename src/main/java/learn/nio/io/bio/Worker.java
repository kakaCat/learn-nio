package learn.nio.io.bio;

import learn.nio.io.bio.server.Server;

import java.io.IOException;
import java.util.Random;

/**
 * Created by JAVA on 2019/4/14.
 */
public class Worker {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100);

        String op = "ss";

        Random random = new Random(System.currentTimeMillis());


        new Thread(()->{while (true){
            String expression = random.nextInt(10)+"op";

        }}).start();





    }

}
