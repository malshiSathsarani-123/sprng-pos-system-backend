package lk.ijse.springpossystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    String id;
    Double amount;
    Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @OneToMany(
            mappedBy = "orders"
    )
    private List<OrderItem> orderDetailEntities;
}
