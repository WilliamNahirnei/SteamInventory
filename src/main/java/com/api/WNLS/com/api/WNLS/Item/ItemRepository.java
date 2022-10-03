package com.api.WNLS.com.api.WNLS.Item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemModel, UUID> {
}
