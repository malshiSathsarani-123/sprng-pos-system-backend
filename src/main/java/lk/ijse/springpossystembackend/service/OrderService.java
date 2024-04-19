package lk.ijse.springpossystembackend.service;

import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.dto.OrderDTO;
import lk.ijse.springpossystembackend.dto.OrderItemDTO;

import java.util.List;

public interface OrderService {
    public String NextOrderId();
    void saveOrder(OrderDTO orderDTO, List<OrderItemDTO> orderDetailDTOList);

    CustomerDTO getCustomer(String id);
}
