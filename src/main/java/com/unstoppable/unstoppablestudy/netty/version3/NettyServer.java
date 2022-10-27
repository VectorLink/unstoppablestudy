package com.unstoppable.unstoppablestudy.netty.version3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Slf4j
public class NettyServer {
    public static void main(String[] args) throws IOException {
        // 1.创建selector
        Selector selector = Selector.open();
        //2.创建服务链接并配置非阻塞
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));

        SelectionKey registerKey = serverSocketChannel.register(selector, 0, null);
        registerKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true){
            //阻塞，等待事件发生
            selector.select();
            //获取对应的事件key
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            //处理事件
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                log.info("获取到key:{}",selectionKey);
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel serverChannel= (ServerSocketChannel) selectionKey.channel();
                    SocketChannel accept = serverChannel.accept();
                    log.info("socketChannel:{}",accept);
                    accept.configureBlocking(false);
                    SelectionKey socketChannelKey = accept.register(selector, 0, null);
                    socketChannelKey.interestOps(SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    try {
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        int read = channel.read(allocate);
                        //关闭客户端，触发read事件,正常断开为-1，不正常断开，爆出异常
                        if (read==-1){
                            selectionKey.cancel();
                        }else {
                            allocate.flip();
                            log.info("读取到数据：{}", StandardCharsets.UTF_8.decode(allocate).toString());
                            allocate.clear();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        selectionKey.cancel();
                    }
                }
            }

        }
    }
}
