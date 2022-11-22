package com.api.WNLS.com.api.WNLS.ItemTransaction;

import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transactions")
public class ItemTransactionController {

    final ItemTransactionService itemTransactionService;

    public ItemTransactionController(ItemTransactionService itemTransactionService) {
        this.itemTransactionService = itemTransactionService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveTransaction(@RequestBody @Valid ItemTransactionDTO itemTransactionDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemTransactionService.save(itemTransactionDTO));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateTransaction(@PathVariable(value = "id") UUID id,
                                      @RequestBody @Valid ItemTransactionDTO itemTransactionDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(itemTransactionService.update(id, itemTransactionDTO));
        }
        catch (ValidationException e){
            return ResponseEntity.status(e.getHttpCode()).body(e.getValidationMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
