//package io.netty.example.securechat;
//
// import org.jboss.netty.channel.ChannelPipeline;
// import org.jboss.netty.channel.ChannelPipelineFactory;
// import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
// import org.jboss.netty.handler.codec.frame.Delimiters;
// import org.jboss.netty.handler.codec.string.StringDecoder;
// import org.jboss.netty.handler.codec.string.StringEncoder;
// import org.jboss.netty.handler.ssl.SslContext;
//
// import static org.jboss.netty.channel.Channels.*;
//
///**
// * Creates a newly configured {@link ChannelPipeline} for a new channel.
// */
//public class SecureChatClientPipelineFactory implements ChannelPipelineFactory {
//
//    private final SslContext sslCtx;
//
//    public SecureChatClientPipelineFactory(SslContext sslCtx) {
//        this.sslCtx = sslCtx;
//    }
//
//    @Override
//    public ChannelPipeline getPipeline() {
//        ChannelPipeline pipeline = pipeline();
//
//        // Add SSL handler first to encrypt and decrypt everything.
//        // In this example, we use a bogus certificate in the server side
//        // and accept any invalid certificates in the client side.
//        // You will need something more complicated to identify both
//        // and server in the real world.
//        pipeline.addLast("ssl", sslCtx.newHandler(SecureChatClient.HOST, SecureChatClient.PORT));
//
//        // On top of the SSL handler, add the text line codec.
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());
//
//        // and then business logic.
//        pipeline.addLast("handler", new SecureChatClientHandler());
//
//        return pipeline;
//    }
//}
