package lk.ijse.springpossystembackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springpossystembackend.conversion.ConversionData;
import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.dto.OrderDTO;
import lk.ijse.springpossystembackend.dto.OrderItemDTO;
import lk.ijse.springpossystembackend.entity.CustomerEntity;
import lk.ijse.springpossystembackend.entity.ItemEntity;
import lk.ijse.springpossystembackend.entity.OrderItem;
import lk.ijse.springpossystembackend.entity.OrderEntity;
import lk.ijse.springpossystembackend.repository.CustomerRep;
import lk.ijse.springpossystembackend.repository.ItemRep;
import lk.ijse.springpossystembackend.repository.OrderDetailsRep;
import lk.ijse.springpossystembackend.repository.OrderRep;
import lk.ijse.springpossystembackend.service.OrderService;
import lk.ijse.springpossystembackend.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRep orderRep;

    @Autowired
    private final ItemRep itemRep;


    @Autowired
    private final OrderDetailsRep orderDetailsRep;

    @Autowired
    private final CustomerRep customerRep;


    @Autowired
    private final ConversionData conversionData;

    @Override
    public String NextOrderId() {
        String maxId = orderRep.findMaxId();
        if (maxId != null){
            return generateNextOrderId(maxId);
        }else {
            return "O-001";
        }
    }


    @Override
    public void saveOrder(OrderDTO orderDTO, List<OrderItemDTO> orderDetailDTOList) {
        OrderEntity orderEntity = conversionData.convertToOrderEntity(orderDTO);
        orderRep.save(orderEntity);
        List<OrderItem> orderItems = conversionData.convertToOrderDetailEntityList(orderDetailDTOList);
        for (OrderItem orderItem : orderItems) {
            orderItem.setId(UtilMatters.generateId());
            orderItem.setOrders(orderEntity);
            ItemEntity referenceById = itemRep.getReferenceById(orderItem.getItem().getCode());
            int balanceQty = referenceById.getQty() - orderItem.getQty();
            orderItem.setItem(new ItemEntity(
                    orderItem.getItem().getCode(),
                    orderItem.getDescription(),
                    balanceQty,
                    orderItem.getUnitPrice()
            ));
            orderDetailsRep.save(orderItem);
        }
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        CustomerEntity customer = customerRep.findById(id).orElse(null);
        if (customer != null) {
            return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact());
        } else {
            return new CustomerDTO();
        }
    }

    private static String generateNextOrderId(String lastCustomerId) {
        String numericPart = lastCustomerId.substring(2);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "O-" + nextNumericPart;
    }
}
