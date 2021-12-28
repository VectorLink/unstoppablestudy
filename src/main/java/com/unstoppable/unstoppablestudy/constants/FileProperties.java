package com.unstoppable.unstoppablestudy.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public  class  FileProperties {

    @Value("${test.name}")
   public  String name;
    @Value("${mq.disable}")
   public static boolean rabbitMqFile;
}
