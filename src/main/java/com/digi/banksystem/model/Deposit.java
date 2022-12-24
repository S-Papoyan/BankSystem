package com.digi.banksystem.model;

import com.digi.banksystem.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id")
    private int id;

    @Column(name = "deposit_name")
    private String name;

    private double amount;

    private Currency currency;

    @Column(name = "opening_date")
    private Date openingDate;

    private int period;

    @Column(name = "deposit_annual_nominal_interest_rate")
    private double interestRate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
