package lk.ijse.springpossystembackend.service;

import lk.ijse.springpossystembackend.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);

    void updateCustomer( String id, CustomerDTO customerDTO);

    String NextCustomerId();

    List<CustomerDTO> getAllCustomer();

    void deleteCustomer(String id);
}
