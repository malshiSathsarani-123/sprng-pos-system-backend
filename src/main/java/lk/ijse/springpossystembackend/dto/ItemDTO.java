package lk.ijse.springpossystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements Serializable {
    private String code;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private List<OrderItemDTO> orderDetailEntities;
}
