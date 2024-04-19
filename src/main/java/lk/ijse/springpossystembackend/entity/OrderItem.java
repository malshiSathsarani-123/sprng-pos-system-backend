package lk.ijse.springpossystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderDetail")
public class OrderItem {

    @Id
    String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_code")
    private ItemEntity item;

    String description;
    Double unitPrice;
    Integer qty;
    Double total;
}
