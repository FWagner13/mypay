package com.mypay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypay.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findUserById(Long id);
    public Optional<User> findUserByCpfCnpj(String cpfCnpj);
    public Optional<User> findUserByEmailAndPassword(String email, String password);
}
