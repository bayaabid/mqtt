package com.horaire.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.horaire.model.PasswordResetToken;
import com.horaire.model.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}
