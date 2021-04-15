package io.netty.example.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * 初始类
 *
 * @author wuhepeng on 3/23/21
 * @See io.netty.example.http.MyInitializer
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

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
        pipeline.addLast("testServerHandler", new TestServerHandler());
    }
}
