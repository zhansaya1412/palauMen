package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.RoleDto;
import kz.batyrbek.palaumen.palaumen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<RoleDto> getRoles(){
        return roleService.getRoles();
    }
}


