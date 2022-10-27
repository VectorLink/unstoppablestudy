package com.unstoppable.unstoppablestudy.netty.version1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NettyServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8080));

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        List<SocketChannel> socketChannels=new ArrayList<>();
        while (true) {
            log.info("client is connecting......");
            //这个地方会一直阻塞
            socketChannels.add(socketChannel.accept());
            log.info("client has connected");
            for (SocketChannel channel : socketChannels) {
                int read = channel.read(byteBuffer);
                if (read>0){
                    byteBuffer.flip();
                    log.info("begin to receive message");
                    String message = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                    log.info("finish to get message：{}",message);
                    byteBuffer.clear();
                }
            }
        }
    }
}
