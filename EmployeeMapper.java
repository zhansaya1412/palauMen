package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.EmployeeDto;
import kz.batyrbek.palaumen.palaumen.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeDto employeeDto);
}
