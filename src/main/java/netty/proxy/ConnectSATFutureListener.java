package netty.proxy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;


/**
 * Description 用于连接服务端和目标服务器的回调listener
 *
 * @author zhaolaiyuan
 * @Date 2020/9/4 13:58
 **/
public final class ConnectSATFutureListener implements FutureListener<Channel> {

    private final ChannelHandlerContext ctx;
    private final HttpProxyServerHandler httpProxyServerHandler;
    private final FullHttpRequest request;

    public ConnectSATFutureListener(ChannelHandlerContext ctx, HttpProxyServerHandler httpProxyServerHandler, FullHttpRequest request) {
        this.ctx = ctx;
        this.httpProxyServerHandler = httpProxyServerHandler;
        this.request = request;
    }

    @Override
    public void operationComplete(Future<Channel> future) {
        // 服务端到目标的channel
        Channel s2tChannel = future.getNow();
        if (future.isSuccess()) {
            ChannelFuture responseFuture = ctx.channel().writeAndFlush(new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK));

            // 监听器，
            // 如果成功了，则联通 请求端和目标端
            responseFuture.addListener(f -> {
                // 进入回调方法

                ctx.pipeline().remove(httpProxyServerHandler);  // 4

                ctx.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                        if (s2tChannel.isActive()) {
                            s2tChannel.writeAndFlush(msg);
                        } else {
                            ReferenceCountUtil.release(msg);
                        }
                    }
                });

                // 请求端到服务端的channel
                Channel c2sChannel = ctx.channel();
                s2tChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                        if (c2sChannel.isActive()) {
                            c2sChannel.writeAndFlush(msg);
                        } else {
                            ReferenceCountUtil.release(msg);
                        }
                    }
                });
            });
        }
    }
}
