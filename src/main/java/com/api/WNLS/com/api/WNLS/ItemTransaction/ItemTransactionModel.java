package com.api.WNLS.com.api.WNLS.ItemTransaction;

import com.api.WNLS.com.api.WNLS.Item.ItemModel;
import com.api.WNLS.com.api.WNLS.User.UserModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "item_transaction")
public class ItemTransactionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
    private float buyPrice;

    @Column(nullable = false, columnDefinition="date default 'now()'")
    private Date buyDate;

    @Column(nullable = true, columnDefinition="Decimal(10,2) default NULL")
    private float sellPrice;

    @Column(nullable = true, columnDefinition="date default NULL")
    private Date sellDate;

    @Enumerated(EnumType.STRING)
    private ItemTransactionPhase transaction_phase;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private ItemModel item;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
