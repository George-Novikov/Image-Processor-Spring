package com.fatemorgan.imgspring.endpoints;

import com.fatemorgan.imgspring.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@ComponentScan("com.fatemorgan.imgspring.services")
@RequestMapping(path = "/image")
public class ImageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @PostMapping(path = "/get_black_and_white", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getBlackAndWhite(@RequestParam("image") MultipartFile image){
        try{
            return imageService.getBlackAndWhite(image.getInputStream());
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return e.getMessage().getBytes();
        }
    }

    @PostMapping(path = "/get_grayscale", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getGrayscale(@RequestParam("image") MultipartFile image,
                                             @RequestParam(name = "threshold", defaultValue = "50") int threshold){
        try {
            return imageService.getGrayscale(image.getInputStream(), threshold);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return e.getMessage().getBytes();
        }
    }

    @PostMapping(path = "/get_segment", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getXYSegment(@RequestParam("image") MultipartFile image,
                                             @RequestParam(name = "x", defaultValue = "0") int x,
                                             @RequestParam(name = "y", defaultValue = "0") int y,
                                             @RequestParam(name = "size", defaultValue = "50") int size){
        try {
            return imageService.getXYSegment(image.getInputStream(), x, y, size);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return e.getMessage().getBytes();
        }
    }
}
