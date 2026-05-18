package org.example.flash_sales;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product extends SellableItem {

    @NotNull
    @Min(0)
    @Max(1)
    private float offer;

    public float getOffer() {
        return offer;
    }

    public void setOffer(float offer) {
        this.offer = offer;
    }
}
