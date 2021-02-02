package ru.lighthouse.mobile.main.rest.controller.image;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lighthouse.mobile.main.core.file.FileStorageConfig;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Validated
@RestController
@RequestMapping(ImageController.IMAGES_URI)
@RequiredArgsConstructor
@Api(tags = "REST для работы с картинками")
public class ImageController {
    public static final String IMAGES_URI = "/images";

    private final FileStorageConfig fileStorageConfig;

    @GetMapping(
            value =  "/{path}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ApiOperation("Скачивание картинки для квартиры по идентификатору картинки")
    @Transactional
    public byte[] getFlatImage(@PathVariable @NotBlank String path) throws IOException {
        return IOUtils.toByteArray(new FileInputStream(fileStorageConfig.getImagesPath() + File.separator + path));
    }
}
