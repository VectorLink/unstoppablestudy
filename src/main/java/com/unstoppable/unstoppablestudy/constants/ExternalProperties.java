package com.unstoppable.unstoppablestudy.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "file:D://test.properties")
@ConfigurationProperties(prefix = "test")
@Data
@RefreshScope
public class ExternalProperties {
    String name;
}
