package netty.proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.socksx.v5.DefaultSocks5CommandResponse;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.StringUtil;

import java.net.SocketAddress;

public final class HttpProxyServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        System.out.println("一个连接");
        request.retain();
//                                    System.out.println(request.toString());
        HttpMethod method = request.method();
        System.out.println(method.name());
        System.out.println(request.protocolVersion());

        Promise<Channel> promise = ctx.executor().newPromise();
        promise.addListener(new ConnectSATFutureListener(ctx,this, request));

        // 新建一个用于连接目标的bootstrap
        Bootstrap remoteBootstrap = new Bootstrap();
        remoteBootstrap.group(ctx.channel().eventLoop())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) {
                        // 连接激活事件
                        ctx.pipeline().remove(this);
                        // 把服务器到目标的channel传入promise中
                        promise.setSuccess(ctx.channel());
                    }
                });

        String uri = request.uri();
        String[] split = uri.split(":");
        String host = split[0];
        int port;

        if (split.length < 2) {
            port = 80;
        } else {
            port = Integer.parseInt(split[1]);
        }
        System.out.println(host);
        System.out.println(port);
        remoteBootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        // Connection established use handler provided results
                    } else {
                        // Close the connection if the connection attempt has failed.
                        ctx.channel().writeAndFlush( // 4
                                new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.INTERNAL_SERVER_ERROR));
                        if (ctx.channel().isActive()) {
                            ctx.channel().writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                        }
                    }
                });
        System.out.println();
    }
}
