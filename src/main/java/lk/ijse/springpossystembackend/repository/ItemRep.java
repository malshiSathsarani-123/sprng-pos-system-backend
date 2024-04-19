package lk.ijse.springpossystembackend.repository;

import lk.ijse.springpossystembackend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRep extends JpaRepository<ItemEntity,String> {
    @Query("SELECT MAX(i.code) FROM ItemEntity i")
    String findMaxId();

}
