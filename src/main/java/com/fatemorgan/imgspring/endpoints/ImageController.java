package com.fatemorgan.imgspring.endpoints;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    public byte[] getGrayScale(@RequestParam("image") MultipartFile image,
                               @RequestParam("threshold") @DefaultValue("50") int threshold){

    }
}
