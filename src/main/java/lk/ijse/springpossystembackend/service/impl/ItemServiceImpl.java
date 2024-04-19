package lk.ijse.springpossystembackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springpossystembackend.conversion.ConversionData;
import lk.ijse.springpossystembackend.dto.CustomerDTO;
import lk.ijse.springpossystembackend.dto.ItemDTO;
import lk.ijse.springpossystembackend.entity.CustomerEntity;
import lk.ijse.springpossystembackend.entity.ItemEntity;
import lk.ijse.springpossystembackend.exception.NotFoundException;
import lk.ijse.springpossystembackend.repository.ItemRep;
import lk.ijse.springpossystembackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRep itemRep;

    @Autowired
    private final ConversionData conversionData;
    public String nextItemCode() {
        String maxId = itemRep.findMaxId();
        if (maxId != null){
            return generateNextItemCode(maxId);
        }else {
            return "I001";
        }
    }

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setCode(nextItemCode());
        conversionData.convertToItemDTO(itemRep.save(conversionData.convertToItemEntity(itemDTO)));
    }

    @Override
    public void updateItem(String code, ItemDTO itemDTO) {
        Optional<ItemEntity> tmpItem = itemRep.findById(code);
        if (!tmpItem.isPresent())throw new NotFoundException("ITEM IS NOT FOUND!!!");
        tmpItem.get().setDescription(itemDTO.getDescription());
        tmpItem.get().setUnitPrice(itemDTO.getUnitPrice());
        tmpItem.get().setQty(itemDTO.getQty());
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<ItemEntity> items = itemRep.findAll();
        return items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ItemDTO convertToDTO(ItemEntity itemEntity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCode(itemEntity.getCode());
        itemDTO.setDescription(itemEntity.getDescription());
        itemDTO.setQty(itemEntity.getQty());
        itemDTO.setUnitPrice(itemEntity.getUnitPrice());
        itemDTO.setOrderDetailEntities(null);
        return itemDTO;
    }

    @Override
    public void deleteItem(String code) {
        itemRep.deleteById(code);
    }

    private static String generateNextItemCode(String lastCustomerId) {
        String numericPart = lastCustomerId.substring(1);
        int nextNumericValue = Integer.parseInt(numericPart) + 1;
        String nextNumericPart = String.format("%03d", nextNumericValue);
        return "I" + nextNumericPart;
    }
}
