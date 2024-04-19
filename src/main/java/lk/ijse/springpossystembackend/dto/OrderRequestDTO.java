package lk.ijse.springpossystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private OrderDTO orderDTO;
    private List<OrderItemDTO> orderDetailDTOList;
}
