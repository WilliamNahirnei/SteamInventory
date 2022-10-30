package com.api.WNLS.com.api.WNLS.ItemTransaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemTransactionRepository extends JpaRepository<ItemTransactionModel, UUID> {
}
