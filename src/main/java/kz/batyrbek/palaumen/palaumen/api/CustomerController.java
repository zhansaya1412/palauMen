package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.CustomerDto;
import kz.batyrbek.palaumen.palaumen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value="{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long id){
        return customerService.getCustomer(id);
    }

    @GetMapping
    public List<CustomerDto> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public  CustomerDto addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }
}

