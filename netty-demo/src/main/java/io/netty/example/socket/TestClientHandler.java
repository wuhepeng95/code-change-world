package io.netty.example.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 自定义的处理器
 */
public class TestClientHandler extends SimpleChannelInboundHandler<String> {


    protected int count = 1;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        SocketAddress socketAddress = channel.remoteAddress();
        System.out.println("客户端收到消息" + socketAddress + "===>>>>>" + msg);
        channel.writeAndFlush("客户端返回消息" + count++);

        TimeUnit.SECONDS.sleep(1L);
    }

    // 发生了异常之后，一般是把连接关闭掉
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("我来了");
    }
}
