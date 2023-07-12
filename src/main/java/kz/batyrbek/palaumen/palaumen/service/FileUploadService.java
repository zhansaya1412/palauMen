package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.model.Users;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    @Value("build/resources/main/static/images/avatars/")
    private String uploadPathAvatar;
    @Value("static/images/avatars/")
    private String loadPathAvatar;
    @Value("build/resources/main/static/images/foods/")
    private String uploadPathFood;
    @Value("static/images/foods/")
    private String loadPathFood;

    private final UserService userService;

    private final FoodService foodService;
    public void uploadPicture(MultipartFile file){
        try{
            if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")){
                byte bytes[] = file.getBytes();
                String filePath = uploadPathAvatar;
                String filename = DigestUtils.sha1Hex(userService.getCurrentUser().getId()+ "_zhansaya_avatarTheJava") +".jpg";
                Path path = Paths.get(filePath + filename);
                Files.write(path, bytes);

                Users user = userService.getCurrentUser();
                user.setAvatar(filename);
                userService.updateUser(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public byte[] getAvatar(String url) throws IOException {
        String image = null;
        String path = loadPathAvatar;
        if(userService.getCurrentUser().getAvatar()!=null && userService.getCurrentUser().getAvatar().equals(url)){
            image = path + userService.getCurrentUser().getAvatar();
        }
        InputStream in;
        try{
            ClassPathResource resource = new ClassPathResource(image);
            in = resource.getInputStream();
        }catch (Exception e){
            ClassPathResource resource = new ClassPathResource(path +"default_user.jpg");
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
    public void uploadFood(MultipartFile file, Long foodId){
        try{
         if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")){
                byte bytes[] = file.getBytes();
                String filePath = uploadPathFood;
                String filename = DigestUtils.sha1Hex(foodId+ "_food_imageTheJava") +".jpg";
                Path path = Paths.get(filePath + filename);
                Files.write(path, bytes);

                FoodDto foodDto = foodService.getFood(foodId);
                foodDto.setPhoto(filename);
                foodService.saveFood(foodDto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public byte[] getPhotoFood(Long foodId, String url) throws IOException {
        String photo = null;
        String path = loadPathFood;
        if(foodService.getFood(foodId).getPhoto()!=null && foodService.getFood(foodId).getPhoto().equals(url)){
            photo = path + foodService.getFood(foodId).getPhoto();
        }
        InputStream in;
        try{
            ClassPathResource resource = new ClassPathResource(photo);
            in = resource.getInputStream();
        }catch (Exception e){
            ClassPathResource resource = new ClassPathResource(path +"food_default_image.jpg");
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
}
