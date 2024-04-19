package lk.ijse.springpossystembackend.api;

import jakarta.validation.Valid;
import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerApi {

    @Autowired
    private final CustomerService customerService;

    @PostMapping
    public void saveCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
    }

    @PatchMapping(value = "/{id}")
    public void updateCustomer(@Valid @PathVariable ("id") String id , @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(id,customerDTO);
    }

    @GetMapping(produces = "application/json")
    List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudent(@PathVariable ("id") String id){
        customerService.deleteCustomer(id);
    }
}
