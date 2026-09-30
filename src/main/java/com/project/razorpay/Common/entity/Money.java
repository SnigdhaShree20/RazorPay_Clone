package com.project.razorpay.Common.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable//ek class jiske khudka ka table nhi hota but uske fields kisi dusre entity k table me embedded hote h.
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Money {
     private int amountUnits;
     private String currency;

     private Money(int i, String currency) {
        this.amountUnits = i;
        this.currency = currency;
    }

    public static Money of(int amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public static Money inr(int amountUnits) {
        return new Money(amountUnits, "INR");
    }
    public Money add(Money other)
     {
         if(!this.currency.equals(other.currency))
             throw new IllegalArgumentException("Cannot add Money with Different Currencies");

         return new Money(this.amountUnits+other.amountUnits ,this.currency);
     }

    public Money subtract(Money other)
    {
        if(!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot add Money with Different Currencies");

        return new Money(this.amountUnits-other.amountUnits ,this.currency);
    }

}
