package com.api.WNLS.com.api.WNLS.Item;

import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.WNLS.com.api.WNLS.Item.ItemService;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/items")
public class ItemController {

    final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping()
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemDTO itemDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemDTO));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping
    public ResponseEntity<Page<ItemModel>> getAllItems(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable(value = "id") UUID id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(itemService.findById(id));
        }
        catch (ValidationException e){
            return ResponseEntity.status(e.getHttpCode()).body(e.getValidationMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateItem(@PathVariable(value = "id") UUID id,
                                      @RequestBody @Valid ItemDTO itemDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(itemService.upddate(id, itemDTO));
        }
        catch (ValidationException e){
            return ResponseEntity.status(e.getHttpCode()).body(e.getValidationMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
