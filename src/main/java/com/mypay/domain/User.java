package com.mypay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mypay.dto.NewAccountDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Account account;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String cpfCnpj;

    @Column(unique = true)
    private String email;

    private String password;

    public User(NewAccountDTO account) {
        this.firstName = account.firstName();
        this.lastName = account.lastName();
        this.cpfCnpj = account.cpfCnpj();
        this.email = account.email();
        this.password = account.password();
    }
}
