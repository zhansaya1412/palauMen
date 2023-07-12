package kz.batyrbek.palaumen.palaumen.controller;

import kz.batyrbek.palaumen.palaumen.dto.FoodDto;
import kz.batyrbek.palaumen.palaumen.service.AccountService;
import kz.batyrbek.palaumen.palaumen.service.EmployeeService;
import kz.batyrbek.palaumen.palaumen.service.FileUploadService;
import kz.batyrbek.palaumen.palaumen.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FileUploadService fileUploadService;
    private final AccountService accountService;
    private final EmployeeService employeeService;
    private final FoodService foodService;


    @GetMapping(value = "/")
    public String index(Model model) {
        return "/customer/index";
    }

    @GetMapping(value = "/signin")
    public String signin(Model model) {
        return "signin";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_re_password") String re_password,
                           @RequestParam(name = "user_fullname") String fullname,
                           @RequestParam(name = "user_phone") String phone) {
        if (accountService.registerUser(email, password, re_password, fullname, phone) != null) {
            return "redirect:register?success";
        } else {
            return "redirect:register?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile(Model model, Authentication authentication) {
        List<String> roles = new ArrayList<>(); // Список для хранения ролей пользователя
        // Получаем объект аутентификации (Authentication)
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            userDetails.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
        }
        // Проверяем наличие роли "ROLE_ADMIN"
        if (roles.contains("ROLE_ADMIN")) {
            return "/admin/menu";
        } else {
            return "profile";
        }
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/settings")
    public String settings(Model model) {
        return "settings";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/menu")
    public String menu(Model model) {
        return "/admin/menu";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/employees")
    public String employees(Model model) {
        return "/admin/employees";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addEmployee")
    public String addEmployee(Model model) {
        return "/admin/addEmployee";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addFood")
    public String addFood(Model model) {
        return "/admin/addFood";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/categoryMenu")
    public String categoryMenu (Model model) {
        return "/admin/categoryMenu";
    }

    @GetMapping(value = "/forbidden")
    public String forbidden(Model model) {
        return "/main/forbidden";
    }

    @PostMapping(value = "/update_password")
    public String updatePassword(@RequestParam(name = "user_old_password") String oldPassword,
                                 @RequestParam(name = "user_new_password") String newPassword,
                                 @RequestParam(name = "user_re_password") String repeatPassword) {
        if (accountService.updatePassword(oldPassword, newPassword, repeatPassword) != null) {
            return "redirect:/profile?passwordsuccess";
        } else {
            return "redirect:/profile?passworderror";
        }
    }

    @GetMapping(value = "/dostavka")
    public String dostavka(Model model) {
        return "/info/dostavka";
    }

    @GetMapping(value = "/contact")
    public String contact(Model model) {
        return "/info/contact";
    }

    @GetMapping(value = "/onas")
    public String onas(Model model) {
        return "/info/onas";
    }

    @GetMapping(value = "/address")
    public String address(Model model) {
        return "/info/address";
    }


    @GetMapping(value = "/categories/{url}")
    public String categoryDetails(@PathVariable(name = "url") String url, Model model){
        model.addAttribute("categoryUrl", url);
        return "/customer/categoryDetails";
    }

    //моя ошибка, где я передала урл в атрибут
//    @GetMapping(value = "/foodDetails/{foodUrl}")
//    public String foodDetails1(@PathVariable(name = "foodUrl", required = false) String foodUrl, Model model) {
//        List<FoodDto> foodList = foodService.getFoods();
//        if (foodUrl != null) {
//            for (FoodDto food : foodList) {
//                if (food.getFoodUrl().equals(foodUrl)) {
//                    model.addAttribute("foodUrl", foodUrl);
//                    return "/admin/foodDetails";
//                }
//            }
//        }
//        if (!foodList.isEmpty()) {
//            model.addAttribute("foodUrl", foodList.get(0).getFoodUrl());
//            return "/admin/foodDetails";
//        }
//        return "forbidden"; // отображение страницы с сообщением об ошибке
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/foodDetails/{foodUrl}")
    public String foodDetails(@PathVariable(name = "foodUrl", required = false) String foodUrl, Model model) {
        FoodDto foodDto = foodService.getFoodUrl(foodUrl);
        if (foodDto != null) {
            model.addAttribute("foodDto", foodDto);
            return "/admin/foodDetails";
        }
        return "forbidden";
    }

    @GetMapping(value = "/foodDetailsClient/{foodUrl}")
    public String foodDetailsClient(@PathVariable(name = "foodUrl") String foodUrl, Model model){
        FoodDto foodDto = foodService.getFoodUrl(foodUrl);
        if (foodDto != null) {
            model.addAttribute("foodDto", foodDto);
            return "/customer/foodDetailsClient";
        }
        return "forbidden";
    }

    @PostMapping(value = "/upload_avatar")
    public String upload_avatar(@RequestParam(name = "avatar")MultipartFile file){
        fileUploadService.uploadPicture(file);
        return "redirect:/settings";
    }

    @GetMapping(value = "/view_avatar/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getAvatar(@PathVariable(name = "url") String url) throws IOException {
        return fileUploadService.getAvatar(url);
    }

    @PostMapping (value = "/upload_food")
    public String upload_food(@RequestParam(name = "foodImage")MultipartFile file,
                              @RequestParam(name = "foodId") Long foodId){
        fileUploadService.uploadFood(file, foodId);
        String foodUrl = foodService.getFood(foodId).getFoodUrl();
        return "redirect:/foodDetails/" + foodUrl;
    }

    @GetMapping(value = "/view_food/{foodId}/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getFoodImage(@PathVariable(name = "foodId") Long foodId,
                                             @PathVariable(name = "url") String url) throws IOException {
        return fileUploadService.getPhotoFood(foodId, url);
    }

    @GetMapping(value = "/order")
    public String order(Model model) {
        return "/customer/order";
    }
}
