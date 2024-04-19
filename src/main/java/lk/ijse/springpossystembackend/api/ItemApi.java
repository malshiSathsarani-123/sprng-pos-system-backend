package lk.ijse.springpossystembackend.api;

import jakarta.validation.Valid;
import lk.ijse.springpossystembackend.dto.ItemDTO;
import lk.ijse.springpossystembackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemApi {
    @Autowired
    private final ItemService itemService;

    @PostMapping
    public void saveItem(@Valid @RequestBody ItemDTO itemDTO){
         itemService.saveItem(itemDTO);
    }

    @PatchMapping("/{code}")
    public void updateItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable("code") String code){
        itemService.updateItem(code,itemDTO);
    }

    @GetMapping
    public List<ItemDTO> getAllItem(){
        return itemService.getAllItem();
    }

    @DeleteMapping("/{code}")
    public void deleteItem(@Valid @PathVariable("code") String code){
        itemService.deleteItem(code);
    }
}
