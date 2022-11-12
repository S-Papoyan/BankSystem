package com.digi.banksystem.model;

import com.digi.banksystem.model.enums.AccountName;
import com.digi.banksystem.model.enums.Currency;
import com.digi.banksystem.model.enums.Status;
import com.digi.banksystem.model.enums.StatusAccount;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;
    @Column(name = "account_name")
    @Enumerated(value = EnumType.STRING)
    private AccountName accountName;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @Column(name = "account_number")
    private Long accountNumber;
    private Double balance;
    @Enumerated(value = EnumType.STRING)
    private StatusAccount status;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
