package com.personal.photos.controllers;


import com.personal.photos.model.Photo;
import com.personal.photos.service.PhotoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/v1/photos")
public class PhotosController {

    private final PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(path = "/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping(path = "/all")
    public Collection<Photo> allPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping(path = "/byId/{id}")
    public Photo photoById(@PathVariable String id) {
        return photoService.getPhotoById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePhotoById(@PathVariable String id){
        photoService.deletePhotoById(id);
    }

    @PostMapping(path = "/add")
    public Photo createPhoto(@RequestParam("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        return photoService.createPhoto(photo);
    }
}
