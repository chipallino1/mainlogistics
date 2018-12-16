package com.samsolutions.logistics.mainlogistics;

import com.samsolutions.logistics.mainlogistics.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class MainLogisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainLogisticsApplication.class, args);
    }
}
