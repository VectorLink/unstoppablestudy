package com.unstoppable.unstoppablestudy.netty.version2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NettyClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));

        socketChannel.write(StandardCharsets.UTF_8.encode("hello"));
        log.info("finished");
        socketChannel.close();
    }
}
