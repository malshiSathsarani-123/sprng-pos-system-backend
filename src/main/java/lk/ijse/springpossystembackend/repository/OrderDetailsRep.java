package lk.ijse.springpossystembackend.repository;

import lk.ijse.springpossystembackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRep extends JpaRepository<OrderItem,String> {
}
