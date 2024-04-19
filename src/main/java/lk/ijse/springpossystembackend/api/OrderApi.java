package lk.ijse.springpossystembackend.api;

import jakarta.validation.Valid;
import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.dto.OrderRequestDTO;
import lk.ijse.springpossystembackend.entity.CustomerEntity;
import lk.ijse.springpossystembackend.repository.CustomerRep;
import lk.ijse.springpossystembackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderApi {
    @Autowired
    private final OrderService orderService;

    @GetMapping
    public String getOrderId(){
        return orderService.NextOrderId();
    }

    @PostMapping
    public void placeOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        CustomerDTO customer = orderRequestDTO.getOrderDTO().getCustomer();
        CustomerDTO customerDTO = orderService.getCustomer(customer.getId());
        orderRequestDTO.getOrderDTO().setCustomer(customerDTO);
        orderService.saveOrder(orderRequestDTO.getOrderDTO(),orderRequestDTO.getOrderDetailDTOList());
    }
}
