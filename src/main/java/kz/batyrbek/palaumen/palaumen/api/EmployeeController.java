package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.EmployeeDto;
import kz.batyrbek.palaumen.palaumen.mapper.UsersMapper;
import kz.batyrbek.palaumen.palaumen.repository.UserRepository;
import kz.batyrbek.palaumen.palaumen.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersMapper usersMapper;

    @GetMapping
    public List<EmployeeDto> getEmployees(){
        return employeeService.getEmployees();
    }

//    @PostMapping
//    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
//        if (employeeDto.getUsers() != null && employeeDto.getUsers().getId() != null) {
//            Users savedUser = userRepository.findById(employeeDto.getUsers().getId())
//                    .orElseThrow();
//            UsersDto usersDto = usersMapper.toDto(savedUser);
//            employeeDto.setUsers(usersDto);
//        }
//        return employeeService.addEmployee(employeeDto);
//    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @PutMapping
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping(value = "{id}")
    public void deleteEmployee(@PathVariable(name = "id") Long id){
        employeeService.deleteEmployee(id);
    }


    @GetMapping("/search")
    public List<EmployeeDto> searchEmployees(@RequestParam(name = "q")  String query){
        return employeeService.searchEmployees(query);
    }
}

