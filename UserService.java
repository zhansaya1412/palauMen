package kz.batyrbek.palaumen.palaumen.service;
import kz.batyrbek.palaumen.palaumen.dto.UsersDto;
import kz.batyrbek.palaumen.palaumen.mapper.UsersMapper;
import kz.batyrbek.palaumen.palaumen.model.Users;
import kz.batyrbek.palaumen.palaumen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UsersMapper usersMapper;

    public Users updateUser(Users user){
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User Not Found");
    }
    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }
    public List<UsersDto> getUsers(){
        List<Users> users = userRepository.findAll();
        List<UsersDto> usersDtos = new ArrayList<>();
        users.forEach(user -> usersDtos.add(usersMapper.toDto(user)));
        return usersDtos;
    }

    public List<UsersDto> searchUser(String search) {
        List<Users> users = userRepository.findByEmailOrFullName(search);
        List<UsersDto> usersDtos = new ArrayList<>();
        users.forEach(user -> usersDtos.add(usersMapper.toDto(user)));
        return usersDtos;
    }
    public UsersDto addUser (UsersDto usersDto){
        Users users = usersMapper.toEntity(usersDto);
        return usersMapper.toDto(userRepository.save(users));
    }

}
