package lk.ijse.springpossystembackend.service;

import lk.ijse.springpossystembackend.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);

    void updateItem(String code, ItemDTO itemDTO);

    List<ItemDTO> getAllItem();

    void deleteItem(String code);
}
