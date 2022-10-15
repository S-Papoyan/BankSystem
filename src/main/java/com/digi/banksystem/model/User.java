package com.digi.banksystem.model;

import com.digi.banksystem.model.enums.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String surname;
    private int year;
    private String email;
    private String password;
    @Column(name = "verification_code")
    private String verify;
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;
    @Column(name = "reset_token")
    private String resetToken;

}
