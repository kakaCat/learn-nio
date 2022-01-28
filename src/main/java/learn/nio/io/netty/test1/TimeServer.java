package learn.nio.io.netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.util.HashMap;
import java.util.Map;

public class TimeServer {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));




    public static void main(String[] args) {
//        final SslContext sslCtx;
//        if (SSL) {
//            SelfSignedCertificate ssc = new SelfSignedCertificate();
//            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
//        } else {
//            sslCtx = null;
//        }

        Map map = new HashMap<>();

        map.put("id", "id");



        EventLoopGroup bossGroup =new NioEventLoopGroup();// (1)
        EventLoopGroup workerGroup =new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup);// (3)
            b.channel(NioServerSocketChannel.class) // (4)     .handler(new LoggingHandler())    // (5)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (6)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // (7)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (8)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(PORT).sync(); // (9)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
