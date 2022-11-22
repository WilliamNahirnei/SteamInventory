package com.api.WNLS.com.api.WNLS.ItemTransaction;

import com.api.WNLS.com.api.WNLS.Item.ItemModel;
import com.api.WNLS.com.api.WNLS.Item.ItemService;
import com.api.WNLS.com.api.WNLS.User.UserModel;
import com.api.WNLS.com.api.WNLS.User.UserService;
import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemTransactionService {
    final ItemTransactionRepository itemTransactionRepository;
    final UserService userService;
    final ItemService itemService;

    public ItemTransactionService(
            ItemTransactionRepository itemTransactionRepository,
            UserService userService,
            ItemService itemService
    ) {
        this.itemTransactionRepository = itemTransactionRepository;
        this.userService = userService;
        this.itemService = itemService;
    }

    public ItemTransactionModel findById(UUID id) throws ValidationException {
        Optional<ItemTransactionModel> itemTransaction = itemTransactionRepository.findById(id);
        if (!itemTransaction.isPresent())
            throw new ValidationException("Transaction not found", HttpStatus.NOT_FOUND.value(), "Transaction not Found");
        return itemTransaction.get();
    }
    @Transactional
    public ItemTransactionModel save(ItemTransactionDTO itemTransactionDTO) throws ValidationException {

        UserModel user = userService.findById(itemTransactionDTO.getId_user());
        ItemModel item = itemService.findById(itemTransactionDTO.getId_item());

        var itemTransaction = new ItemTransactionModel();
        BeanUtils.copyProperties(itemTransactionDTO, itemTransaction);
        itemTransaction.setItem(item);
        itemTransaction.setUser(user);

        return itemTransactionRepository.save(itemTransaction);
    }

    @Transactional
    public ItemTransactionModel update(UUID id, ItemTransactionDTO itemTransactionDTO) throws ValidationException {
        var itemTransaction = this.findById(id);
        BeanUtils.copyProperties(itemTransactionDTO, itemTransaction);

        return itemTransactionRepository.save(itemTransaction);
    }
}
