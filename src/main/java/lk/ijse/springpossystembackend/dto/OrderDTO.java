package lk.ijse.springpossystembackend.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    String id;
    Double amount;
    Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerDTO customer;
    private List<OrderItemDTO> orderDetailDTOS;

}
