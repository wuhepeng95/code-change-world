package io.netty.example.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty HelloWord
 * 服务器
 * 测试方法：
 * 1、浏览器 http://localhost:8888/ 执行了俩次 （http://localhost:8888/favicon.ico请求图标）
 * 2、命令行 curl localhost:8888 执行了一次
 */
public class MyHttpServer {
    public static void main(String[] args) throws InterruptedException {
        // boss线程组：完成连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker线程组：完成任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 启动
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 方法链编程风格
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 请求处理器
                    .childHandler(new MyHttpInitializer());

            // 绑定端口
            ChannelFuture sync = bootstrap.bind(8888).sync();
            // sync 会等待
            sync.channel().closeFuture().sync();
        } finally {
            // 优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
