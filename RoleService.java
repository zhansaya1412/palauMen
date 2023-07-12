package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.RoleDto;
import kz.batyrbek.palaumen.palaumen.mapper.RoleMapper;
import kz.batyrbek.palaumen.palaumen.model.Role;
import kz.batyrbek.palaumen.palaumen.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleDto> getRoles(){
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(role -> roleDtos.add(roleMapper.toDto(role)));
        return roleDtos;
    }

}
