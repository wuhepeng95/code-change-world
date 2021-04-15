package io.netty.example.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    // channel组
    // new DefaultChannelGroup(GlobalEventExecutor.INSTANCE)
    // ||
    // \/
    // this("group-0x" + Integer.toHexString(nextId.incrementAndGet()), executor, stayClosed);
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【" + channel.remoteAddress() + "】:" + msg);

        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("【" + channel.remoteAddress() + "】:" + msg);
            } else {
                channel.writeAndFlush("【我】：" + msg);
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("<<<<<<======【系统消息】" + channel.remoteAddress() + "准备出发======>>>>>>>\n");
        System.out.println("<<<<<<======【系统消息】" + channel.remoteAddress() + "准备出发======>>>>>>>\n");
        // 不需要手动remove TODO 寻找源码
        // channelGroup.remove(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("<<<<<<======【系统消息】" + channel.remoteAddress() + "已经离开======>>>>>>>\n");
        System.out.println("<<<<<<======【系统消息】" + channel.remoteAddress() + "已经离开======>>>>>>>\n");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("<<<<<<======【系统消息】" + channel.remoteAddress() + "进入了快乐星球======>>>>>>>\n");
        channelGroup.add(channel);
        System.out.println("<<<<<<======【系统消息】" + channel.remoteAddress() + "进入了快乐星球======>>>>>>>\n");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("<<<<<<======【系统消息】" + channel.remoteAddress() + "退出了快乐星球======>>>>>>>\n");
        System.out.println("<<<<<<======【系统消息】" + channel.remoteAddress() + "退出了快乐星球======>>>>>>>\n");
    }

    // 发生了异常之后，一般是把连接关闭掉
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
