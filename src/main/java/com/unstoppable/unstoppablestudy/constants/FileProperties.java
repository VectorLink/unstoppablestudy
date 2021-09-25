package com.unstoppable.unstoppablestudy.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class FileProperties {

    @Value("${test.name}")
    String name;
}
