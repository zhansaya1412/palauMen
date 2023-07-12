package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.UsersDto;
import kz.batyrbek.palaumen.palaumen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UsersDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/search")
    public List<UsersDto> searchUser(@RequestParam(name = "q")  String query){
        return userService.searchUser(query);
    }

    @PostMapping
    public UsersDto addUsers(@RequestBody UsersDto usersDto){
        return userService.addUser(usersDto);
    }

}


