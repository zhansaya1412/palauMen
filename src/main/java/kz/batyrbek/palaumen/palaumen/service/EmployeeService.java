package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.EmployeeDto;
import kz.batyrbek.palaumen.palaumen.dto.RoleDto;
import kz.batyrbek.palaumen.palaumen.mapper.EmployeeMapper;
import kz.batyrbek.palaumen.palaumen.mapper.RoleMapper;
import kz.batyrbek.palaumen.palaumen.model.Employee;
import kz.batyrbek.palaumen.palaumen.model.Role;
import kz.batyrbek.palaumen.palaumen.model.Users;
import kz.batyrbek.palaumen.palaumen.repository.EmployeeRepository;
import kz.batyrbek.palaumen.palaumen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    private final RoleMapper roleMapper;


    public List<EmployeeDto> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employees.forEach(employee -> employeeDtos.add(employeeMapper.toDto(employee)));
        return employeeDtos;
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);

        if (employee.getUsers() != null && employee.getUsers().getId() != null) {
            Users existingUser = userRepository.findById(employee.getUsers().getId())
                    .orElseThrow();

            String newFullname = employeeDto.getUsers().getFullname();
            String newEmail = employeeDto.getUsers().getEmail();
            String newPhone = employeeDto.getUsers().getPhone();
            List<RoleDto> newRoles = employeeDto.getUsers().getRoles();
            List<Role> roleEntities = new ArrayList<>();
            for (RoleDto roleDto : newRoles) {
                Role role = roleMapper.toEntity(roleDto);
                roleEntities.add(role);
            }

            existingUser.setFullname(newFullname);
            existingUser.setEmail(newEmail);
            existingUser.setPhone(newPhone);
            existingUser.setRoles(roleEntities);

            Users savedUser = userService.updateUser(existingUser);
            employee.setUsers(savedUser);
        }

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> searchEmployees(String search) {
        List<Employee> employees = employeeRepository.findByEmailOrFullnameOrPosition(search);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employees.forEach(employee -> employeeDtos.add(employeeMapper.toDto(employee)));
        return employeeDtos;
    }
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
