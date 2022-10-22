//package com.unstoppable.unstoppablestudy.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//
//import java.nio.charset.StandardCharsets;
//
//@Slf4j
//public class NettyServer {
//    public static void main(String[] args) throws InterruptedException {
//
//        EventLoopGroup boosGroup = new NioEventLoopGroup();
//        EventLoopGroup workGroup = new NioEventLoopGroup();
//        ServerBootstrap serverBootstrap = new ServerBootstrap().group(boosGroup, workGroup)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        socketChannel.pipeline()
//                                .addLast(new LoggingHandler(LogLevel.DEBUG))
//                                .addLast(new LengthFieldBasedFrameDecoder(1024,12,4,0,0))
//                                .addLast(new HttpServerCodec())
//                                .addLast(new ChannelInboundHandlerAdapter(){
//                                                                        @Override
//                                                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                                                            log.debug("{}",msg.getClass());
//                                                                        }
//                                                                    })
//                                .addLast(new SimpleChannelInboundHandler<HttpRequest>() {
//                                    @Override
//                                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
//                                        log.debug(httpRequest.uri());
//                                        byte[] bytes = "hello my world".getBytes(StandardCharsets.UTF_8);
//                                        DefaultFullHttpResponse response=new DefaultFullHttpResponse(
//                                                httpRequest.protocolVersion(), HttpResponseStatus.FOUND
//                                        );
//                                        response.content().writeBytes(bytes);
//                                        response.headers().setInt(HttpHeaders.CONTENT_LENGTH,bytes.length);
//
//                                        channelHandlerContext.writeAndFlush(response);
//                                    }
//                                });
//                    }
//                });
//
//        ChannelFuture channelFuture = serverBootstrap.bind(8080);
//        channelFuture.channel().closeFuture().sync();
//    }
//}
