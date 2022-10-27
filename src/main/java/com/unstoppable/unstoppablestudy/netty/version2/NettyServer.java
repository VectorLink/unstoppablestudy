package com.unstoppable.unstoppablestudy.netty.version2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Slf4j
public class NettyServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8080));
        socketChannel.configureBlocking(false); //非阻塞

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        List<SocketChannel> socketChannels=new ArrayList<>();
        while (true) {
            log.info("client is connecting......");
            //这个地方会一直阻塞
            SocketChannel accept = socketChannel.accept();
            if (Objects.nonNull(accept)){
                accept.configureBlocking(false);// 这里配置了，可以让下面的read方法变为非阻塞的
                socketChannels.add(accept);
            }
            log.info("client has connected");
            Iterator<SocketChannel> iterator = socketChannels.iterator();
            while (iterator.hasNext()){
                SocketChannel channel=iterator.next();
                if (Objects.isNull(channel)){
                    continue;
                }
                try {
                    int read = channel.read(byteBuffer);
                    if (read>0){
                        byteBuffer.flip();
                        log.info("begin to receive message");
                        String message = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                        log.info("finish to get message：{}",message);
                        byteBuffer.clear();
                    }
                } catch (IOException e) {
                    iterator.remove();
                    e.printStackTrace();
                }

            }
            Thread.sleep(2000);
        }
    }
}
