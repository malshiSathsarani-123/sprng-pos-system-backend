package lk.ijse.springpossystembackend.conversion;

import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.dto.ItemDTO;
import lk.ijse.springpossystembackend.dto.OrderDTO;
import lk.ijse.springpossystembackend.dto.OrderItemDTO;
import lk.ijse.springpossystembackend.entity.CustomerEntity;
import lk.ijse.springpossystembackend.entity.ItemEntity;
import lk.ijse.springpossystembackend.entity.OrderItem;
import lk.ijse.springpossystembackend.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversionData {
   final private ModelMapper  modelMapper;

   @Autowired
   public ConversionData(ModelMapper modelMapper){
       this.modelMapper=modelMapper;
   }

   //Customer object mapping
    public CustomerDTO convertToCustomerDTO(CustomerEntity customerEntity){
       return modelMapper.map(customerEntity,CustomerDTO.class);
    }
    public CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO){
       return modelMapper.map(customerDTO,CustomerEntity.class);
    }
    public List<CustomerDTO> getCustomerDTOList(List<CustomerEntity>customerEntityList){
       return modelMapper.map(customerEntityList, List.class);
    }

    //Item object mapping
    public ItemDTO convertToItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity,ItemDTO.class);
    }
    public ItemEntity convertToItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO,ItemEntity.class);
    }
    public List<ItemDTO> getItemDTOList(List<ItemEntity>itemEntityList){
        return modelMapper.map(itemEntityList, List.class);
    }

    //Item object mapping
    public OrderEntity convertToOrderEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO,OrderEntity.class);
    }

    public List<OrderItem> convertToOrderDetailEntityList(List<OrderItemDTO> orderDetailDTOList) {
        return orderDetailDTOList.stream()
                .map(this::convertToOrderDetailEntity)
                .collect(Collectors.toList());
    }

    private OrderItem convertToOrderDetailEntity(OrderItemDTO orderDetailDTO) {
        return modelMapper.map(orderDetailDTO, OrderItem.class);
    }
}
