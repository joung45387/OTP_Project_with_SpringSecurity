package com.otp.otpproject.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.otp.otpproject.Domain.Model.Image;
import com.otp.otpproject.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    @GetMapping("/upload")
    public String t(){
        return "testUpload";
    }
    @PostMapping("/upload")
    public String form(
            @RequestParam MultipartFile file, String serialNum) throws IOException {
        if (!file.isEmpty()) {
            file.getBytes();
            Image image = Image.builder().image(new String(encodeBase64(file.getBytes()))).serialNum(serialNum).uploadTIme(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();
            imageRepository.saveAndFlush(image);
        }
        return "testUpload";
    }

    @GetMapping("/getImages")
    public String getImages(String serialNum, Model model){
        List<Image> images = imageRepository.findBySerialNum(serialNum);
        model.addAttribute("li", images);
        return "ImageList";
    }
    @ResponseBody
    @GetMapping("/api/getImage")
    public String getImagesAPI(String serialNum, Model model){
        List<Image> images = imageRepository.findBySerialNum(serialNum);
        JSONObject obj = new JSONObject();
        obj.put("li", images);
        return obj.toString();
    }
}
