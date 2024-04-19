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
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private Integer contact;

    @OneToMany(
            mappedBy = "customer",fetch = FetchType.EAGER
    )
    private List<OrderEntity> orders;
}
