package lk.ijse.springpossystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO implements Serializable {
    private String id;
    private OrderDTO orders;
    private ItemDTO item;
    private String description;
    private Double unitPrice;
    private Integer qty;
    Double total;
}
