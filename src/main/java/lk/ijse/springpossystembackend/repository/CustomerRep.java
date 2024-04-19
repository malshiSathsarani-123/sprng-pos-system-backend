package lk.ijse.springpossystembackend.repository;

import lk.ijse.springpossystembackend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerRep extends JpaRepository<CustomerEntity,String> {
    @Query("SELECT MAX(c.id) FROM CustomerEntity c")
    String findMaxId();
}
