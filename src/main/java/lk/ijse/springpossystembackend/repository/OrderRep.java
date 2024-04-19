package lk.ijse.springpossystembackend.repository;

import lk.ijse.springpossystembackend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRep extends JpaRepository<OrderEntity,String> {
    @Query("SELECT MAX(o.id) FROM OrderEntity o")
    String findMaxId();
}
