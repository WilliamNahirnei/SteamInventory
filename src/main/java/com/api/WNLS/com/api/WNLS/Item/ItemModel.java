package com.api.WNLS.com.api.WNLS.Item;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "item")
public class ItemModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 30)
    private String name;
}
