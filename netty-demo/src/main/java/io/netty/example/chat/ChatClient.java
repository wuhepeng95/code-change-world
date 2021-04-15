package io.netty.example.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 客户端 连接不上服务端会自动关闭  Connection refused / System.in 手动阻塞不会
 *
 * @author wuhepeng on 4/2/21
 */
public class ChatClient {
    public static void main(String[] args) {
        // 客户端只需要一个事件循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            // 服务端的是ServerBootstrap、NioServerSocketChannel
            Bootstrap bootstrap = new Bootstrap();
            // 客户端不使用childHandler
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 管道
                    ChannelPipeline pipeline = ch.pipeline();

                    //添加的都是handler
                    // 解码器decoder
                    pipeline.addLast("name", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    // 编码器encoder
                    pipeline.addLast(new LengthFieldPrepender(4));

                    // 字符串的编解码器
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

                    // 自定义处理器
                    pipeline.addLast("testClientHandler", new SimpleChannelInboundHandler<String>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                            System.out.println(msg);
                        }

                        // 发生了异常之后，一般是把连接关闭掉
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            cause.printStackTrace();
                            ctx.close();
                        }

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        }
                    });
                }
            });

            // 客户端是connect 服务端是bind
            Channel clintChannel = bootstrap.connect("localhost", 8888).sync().channel();

            for (; ; ) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                clintChannel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
