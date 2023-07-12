package kz.batyrbek.palaumen.palaumen.service;
import kz.batyrbek.palaumen.palaumen.model.Role;
import kz.batyrbek.palaumen.palaumen.model.Users;
import kz.batyrbek.palaumen.palaumen.repository.RoleRepository;
import kz.batyrbek.palaumen.palaumen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public Users registerUser(String email, String password, String re_password, String fullname, String phone) {
        Users checkUser = userRepository.findByEmail(email);
        if (checkUser != null && checkUser.getPassword()== null) {
            if (password.equals(re_password)) {
                List<Role> roles = new ArrayList<>();
                Role userRole = roleRepository.findByRole("ROLE_USER");
                roles.add(userRole);
                Users user = Users.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .fullname(fullname)
                        .phone(phone)
                        .roles(roles)
                        .build();
                Users savedUser = userRepository.save(user);
                return savedUser;
            }
        }
        return null;
    }
    public Users updatePassword(String oldPassword, String newPassword, String repeatPassword){
        Users currentUser = userService.getCurrentUser();
        if(currentUser!=null){
            if(newPassword.equals(repeatPassword) &&
                    passwordEncoder.matches(oldPassword, currentUser.getPassword())){
                currentUser.setPassword(passwordEncoder.encode(newPassword));
                return  userRepository.save(currentUser);
            }
        }
        return null;
    }
}
