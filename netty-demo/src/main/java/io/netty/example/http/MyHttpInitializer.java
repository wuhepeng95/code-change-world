package io.netty.example.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 初始类
 *
 * @author wuhepeng on 3/23/21
 */
public class MyHttpInitializer extends ChannelInitializer<SocketChannel> {// 实现于ChannelHandler

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 管道
        ChannelPipeline pipeline = ch.pipeline();
        // 编码/解码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 自定义处理器
        pipeline.addLast("myServerHandler", new MyHttpHandler());
    }
}
