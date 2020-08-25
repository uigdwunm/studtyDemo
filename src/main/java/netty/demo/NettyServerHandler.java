package netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashSet;
import java.util.Set;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel，管道pipeline
     * @param msg 就是客户端发送的数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
//
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel == ch) {
                // 给自己的
                ch.writeAndFlush(msg);
            } else {
                // 广播的
                ch.writeAndFlush(ch.remoteAddress() + "发送的消息：" + msg + "\r\n");
            }
        });
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        //将 msg 转成一个 ByteBuf，类似NIO 的 ByteBuffer
//        Channel channel = ctx.channel();
//        String m = buf.toString(CharsetUtil.UTF_8);
//        System.out.println(channel.remoteAddress() + "发送的消息：" + m);
//        channelGroup.forEach(ch -> {
//            if (channel == ch) {
//                // 给自己的
//                ch.writeAndFlush(Unpooled.copiedBuffer(m, CharsetUtil.UTF_8));
//            } else {
//                // 广播的
//                ByteBuf byteBuf = Unpooled.copiedBuffer(ch.remoteAddress() + "发送的消息：" + m, CharsetUtil.UTF_8);
//                ch.writeAndFlush(byteBuf);
//            }
//        });
//    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("服务器-" + channel.remoteAddress() + "加入");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("断开连接 " + channel.remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("下线 " + channel.remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("上线 " + channel.remoteAddress());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
