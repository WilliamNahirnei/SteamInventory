package com.api.WNLS.com.api.WNLS.ItemTransaction;

import com.api.WNLS.com.api.WNLS.CustomValidations.Enum.ValueOfEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class ItemTransactionDTO {

    @Min(0)
    private float buyPrice;

    @DateTimeFormat()
    private Date buyDate;

    @Min(0)
    private float sellPrice = 0;

    @DateTimeFormat()
    private Date sellDate;

    @NotNull()
//    @ValueOfEnum(enumClass = ItemTransactionPhase.class, message = "Invalid transaction status")
    private ItemTransactionPhase transaction_phase;

    private UUID id_item;
    
    private UUID id_user;

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public ItemTransactionPhase getTransaction_phase() {
        return transaction_phase;
    }

    public void setTransaction_phase(ItemTransactionPhase transaction_phase) {
        this.transaction_phase = transaction_phase;
    }

    public UUID getId_item() {
        return id_item;
    }

    public void setId_item(UUID id_item) {
        this.id_item = id_item;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }
}
