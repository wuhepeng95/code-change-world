package io.netty.example.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端 连接不上服务端会自动关闭  Connection refused
 *
 * @author wuhepeng on 4/2/21
 */
public class TestClient {
    public static void main(String[] args) {
        // 客户端只需要一个事件循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            // 服务端的是ServerBootstrap、NioServerSocketChannel
            Bootstrap bootstrap = new Bootstrap();
            // 客户端不使用childHandler
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new TestClientInitializer());

            // 客户端是connect 服务端是bind
            ChannelFuture sync = bootstrap.connect("localhost", 8888).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
