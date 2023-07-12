package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.CustomerDto;
import kz.batyrbek.palaumen.palaumen.mapper.CustomerMapper;
import kz.batyrbek.palaumen.palaumen.model.Customer;
import kz.batyrbek.palaumen.palaumen.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerDto getCustomer(Long id){
        return customerMapper.toDto(customerRepository.findById(id).orElseThrow());
    }

    public List<CustomerDto> getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.forEach(employee -> customerDtos.add(customerMapper.toDto(employee)));
        return customerDtos;
    }

    public CustomerDto addCustomer (CustomerDto customerDto){
        Customer customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }
}
