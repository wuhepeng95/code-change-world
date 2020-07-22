//package io.netty.example.securechat;
//
// import org.jboss.netty.channel.ChannelEvent;
// import org.jboss.netty.channel.ChannelHandlerContext;
// import org.jboss.netty.channel.ChannelStateEvent;
// import org.jboss.netty.channel.ExceptionEvent;
// import org.jboss.netty.channel.MessageEvent;
// import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
//
///**
// * Handles a client-side channel.
// */
//public class SecureChatClientHandler extends SimpleChannelUpstreamHandler {
//
//    @Override
//    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
//        if (e instanceof ChannelStateEvent) {
//            System.err.println(e);
//        }
//        super.handleUpstream(ctx, e);
//    }
//
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//        System.err.println(e.getMessage());
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
//        e.getCause().printStackTrace();
//        e.getChannel().close();
//    }
//}
