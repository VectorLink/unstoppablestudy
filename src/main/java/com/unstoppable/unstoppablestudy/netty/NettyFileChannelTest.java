package com.unstoppable.unstoppablestudy.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author lijun
 * @date 2022/10/22
 **/
@Slf4j
public class NettyFileChannelTest {

    public static void main(String[] args) throws Exception {

        try (FileChannel channel = new FileInputStream("D:\\ideaworkspace\\unstoppablestudy\\src\\main\\resources\\test.sql").getChannel()) {
            ByteBuffer byteBuf=ByteBuffer.allocate(1000);
            while (true) {
                int read = channel.read(byteBuf);
                if (read<0){
                    break;
                }
                byteBuf.flip();
                while (byteBuf.hasRemaining()) {
                     byte[] bytes=new byte[byteBuf.limit()];
                     byteBuf.get(bytes);
                    String s = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(s);
                }
                byteBuf.clear();
            }
        }

    }
}
