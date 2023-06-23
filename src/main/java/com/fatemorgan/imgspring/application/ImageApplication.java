package com.fatemorgan.imgspring.application;

import com.fatemorgan.imgspring.endpoints.ImageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.fatemorgan.imgspring.*"})
@ComponentScan(basePackageClasses = ImageController.class)
public class ImageApplication {
    public static void main(String[] args){
        SpringApplication.run(ImageApplication.class, args);
    }
}
