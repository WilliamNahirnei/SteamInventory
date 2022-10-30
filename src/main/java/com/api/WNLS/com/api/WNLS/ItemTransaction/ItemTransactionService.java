package com.api.WNLS.com.api.WNLS.ItemTransaction;

import com.api.WNLS.com.api.WNLS.Item.ItemModel;
import com.api.WNLS.com.api.WNLS.Item.ItemService;
import com.api.WNLS.com.api.WNLS.User.UserModel;
import com.api.WNLS.com.api.WNLS.User.UserService;
import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public ItemTransactionModel save(ItemTransactionDTO itemTransactionDTO) throws ValidationException {

        UserModel user = userService.findById(itemTransactionDTO.getId_user());
        ItemModel item = itemService.findById(itemTransactionDTO.getId_item());

        var itemTransaction = new ItemTransactionModel();
        BeanUtils.copyProperties(itemTransactionDTO, itemTransaction);
        itemTransaction.setItem(item);
        itemTransaction.setUser(user);
//        System.out.println(itemTransaction.getUser().getId());
        return itemTransactionRepository.save(itemTransaction);
    }
}
