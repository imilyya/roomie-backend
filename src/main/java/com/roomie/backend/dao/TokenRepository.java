package com.roomie.backend.dao;

import com.roomie.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
      SELECT t FROM Token t INNER JOIN User u\s
      ON t.user.id = u.id\s
      WHERE u.id = :id AND (t.expired = FALSE OR t.revoked = FALSE)\s
      """)

    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}