package lk.ijse.springpossystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String code;
    private String description;
    private Integer qty;
    private Double unitPrice;

    @OneToMany(
            mappedBy = "item",fetch = FetchType.EAGER
    )
    private List<OrderItem> orderDetailEntities;

    public ItemEntity(String code, String description, Integer qty, Double unitPrice) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}
