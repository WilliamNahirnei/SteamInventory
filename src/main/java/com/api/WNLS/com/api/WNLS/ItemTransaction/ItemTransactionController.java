package com.api.WNLS.com.api.WNLS.ItemTransaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
