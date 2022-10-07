package com.api.WNLS.com.api.WNLS.Item;

import com.api.WNLS.com.api.WNLS.Utils.Exceptions.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Transactional
    public ItemModel save (ItemDTO itemDTO) {
        var itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDTO, itemModel);
        return itemRepository.save(itemModel);
    }

    public Page<ItemModel> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public ItemModel findById(UUID id) throws ValidationException {
        Optional<ItemModel> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent())
            throw new ValidationException("Item not found", HttpStatus.NOT_FOUND.value(), "Item not Found");
        return itemOptional.get();
    }

    @Transactional
    public ItemModel upddate(UUID id, ItemDTO itemDTO) throws ValidationException {
        ItemModel item = this.findById(id);
        BeanUtils.copyProperties(itemDTO, item);
        return itemRepository.save(item);
    }

    @Transactional
    public void delete(UUID id) throws ValidationException {
        ItemModel item = this.findById(id);
        itemRepository.delete(item);
    }
}
