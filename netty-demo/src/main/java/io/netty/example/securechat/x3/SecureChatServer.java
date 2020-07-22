//package io.netty.example.securechat;
//
// import org.jboss.netty.bootstrap.ServerBootstrap;
// import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
// import org.jboss.netty.handler.ssl.SslContext;
// import org.jboss.netty.handler.ssl.util.SelfSignedCertificate;
//
// import java.net.InetSocketAddress;
// import java.util.concurrent.Executors;
//
//public final class SecureChatServer {
//
//    static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));
//
//    public static void main(String[] args) throws Exception {
//        SelfSignedCertificate ssc = new SelfSignedCertificate();
//        SslContext sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
//
//        // Configure the server.
//        ServerBootstrap bootstrap = new ServerBootstrap(
//                new NioServerSocketChannelFactory(
//                        Executors.newCachedThreadPool(),
//                        Executors.newCachedThreadPool()));
//
//        // Configure the pipeline factory.
//        bootstrap.setPipelineFactory(new SecureChatServerPipelineFactory(sslCtx));
//
//        // Bind and start to accept incoming connections.
//        bootstrap.bind(new InetSocketAddress(PORT));
//    }
//}
