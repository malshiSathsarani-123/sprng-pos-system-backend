package lk.ijse.springpossystembackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springpossystembackend.conversion.ConversionData;
import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.entity.CustomerEntity;
import lk.ijse.springpossystembackend.exception.NotFoundException;
import lk.ijse.springpossystembackend.repository.CustomerRep;
import lk.ijse.springpossystembackend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRep customerRep;
    @Autowired
    private final ConversionData conversionData;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(NextCustomerId());
        conversionData.convertToCustomerDTO(customerRep.save(conversionData.convertToCustomerEntity(customerDTO)));
    }

    @Override
    public void updateCustomer( String id, CustomerDTO customerDTO) {
        Optional<CustomerEntity> tmpCustomer = customerRep.findById(id);
        if (!tmpCustomer.isPresent())throw new NotFoundException("CUSTOMER NOT FOUND");
        tmpCustomer.get().setName(customerDTO.getName());
        tmpCustomer.get().setAddress(customerDTO.getAddress());
        tmpCustomer.get().setEmail(customerDTO.getEmail());
        tmpCustomer.get().setContact(customerDTO.getContact());
    }

    @Override
    public String NextCustomerId() {
        String maxId = customerRep.findMaxId();
        if (maxId != null){
            return generateNextCustomerId(maxId);
        }else {
            return "C001";
        }
    }

    private static String generateNextCustomerId(String lastCustomerId) {
        String numericPart = lastCustomerId.substring(1);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "C" + nextNumericPart;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<CustomerEntity> customers = customerRep.findAll();
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertToDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setAddress(customerEntity.getAddress());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setContact(customerEntity.getContact());
        customerDTO.setOrders(null);
        return customerDTO;
    }

    @Override
    public void deleteCustomer(String id) {
        customerRep.deleteById(id);
    }

}
