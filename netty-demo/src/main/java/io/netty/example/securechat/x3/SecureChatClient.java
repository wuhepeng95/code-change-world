//package io.netty.example.securechat;
//
//import org.jboss.netty.bootstrap.ClientBootstrap;
//import org.jboss.netty.channel.Channel;
//import org.jboss.netty.channel.ChannelFuture;
//import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
//import org.jboss.netty.handler.ssl.SslContext;
//import org.jboss.netty.handler.ssl.util.InsecureTrustManagerFactory;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
///**
// * 官方demo 3.10
// */
//public final class SecureChatClient {
//
//    static final String HOST = System.getProperty("host", "127.0.0.1");
//    static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));
//
//    public static void main(String[] args) throws Exception {
//
//        // Configure SSL.
//        final SslContext sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
//
//        // Configure the client.
//        ClientBootstrap bootstrap = new ClientBootstrap(
//                new NioClientSocketChannelFactory(
//                        Executors.newCachedThreadPool(),
//                        Executors.newCachedThreadPool()));
//
//        try {
//            // Configure the pipeline factory.
//            bootstrap.setPipelineFactory(new SecureChatClientPipelineFactory(sslCtx));
//
//            // Start the connection attempt.
//            ChannelFuture future = bootstrap.connect(new InetSocketAddress(HOST, PORT));
//
//            // Wait until the connection attempt succeeds or fails.
//            Channel channel = future.sync().getChannel();
//
//            // Read commands from the stdin.
//            ChannelFuture lastWriteFuture = null;
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            for (; ; ) {
//                String line = in.readLine();
//                if (line == null) {
//                    break;
//                }
//
//                // Sends the received line to the server.
//                lastWriteFuture = channel.write(line + "\r\n");
//
//                // If user typed the 'bye' command, wait until the server closes
//                // the connection.
//                if ("bye".equals(line.toLowerCase())) {
//                    channel.getCloseFuture().sync();
//                    break;
//                }
//            }
//
//            // Wait until all messages are flushed before closing the channel.
//            if (lastWriteFuture != null) {
//                lastWriteFuture.sync();
//            }
//
//            // Close the connection.  Make sure the close operation ends because
//            // all I/O operations are asynchronous in Netty.
//            channel.close().sync();
//        } finally {
//            // Shut down all thread pools to exit.
//            bootstrap.releaseExternalResources();
//        }
//    }
//}
