package ru.lighthouse.mobile.main.rest.controller.image;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.config.FileStorageConfig;
import ru.lighthouse.mobile.main.lang.apachecommonsio.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Validated
@RestController
@RequestMapping(ImageController.IMAGES_URI)
@RequiredArgsConstructor
@Api(tags = "REST для работы с изображениями")
public class ImageController {
    public static final String IMAGES_URI = "/images";

    private final FileStorageConfig fileStorageConfig;

    @GetMapping(value =  "/**", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation("Скачивание изображения по пути к изображению")
    public byte[] getFlatImage(HttpServletRequest request) throws IOException {
        return IOUtils.toByteArray(new FileInputStream(fileStorageConfig.getImagesPath() + File.separator + getRequestURI(request)));
    }

    private String getRequestURI(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.substring(uri.indexOf(IMAGES_URI) + IMAGES_URI.length());
    }
}
