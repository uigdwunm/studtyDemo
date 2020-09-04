package netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class NettyClient {
    public static void main(String[] args) {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //加入处理器
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
//                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("netty client start");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String name = bufferedReader.readLine();
            channel.writeAndFlush(Unpooled.copiedBuffer(name + " 进入", CharsetUtil.UTF_8));;
            //对关闭通道进行监听
//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
