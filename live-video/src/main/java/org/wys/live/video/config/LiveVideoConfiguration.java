package org.wys.live.video.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wys
 * @date 2021/2/4 12:23
 */
@Configuration
@ConfigurationProperties(prefix = "live.video")
@Data
@EnableConfigurationProperties(LiveVideoConfiguration.class)
public class LiveVideoConfiguration {

    private String videoSource;
}
