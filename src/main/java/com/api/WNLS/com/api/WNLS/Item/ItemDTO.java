package com.api.WNLS.com.api.WNLS.Item;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ItemDTO {

    @NotBlank
    @Size(max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
