package com.example.repository;

import com.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
    Account findByEmail(String email);
    Account findByUserID(String userID);
    Account findByUserIDAndCode(String userID, String code);
    boolean existsByUsername(String username);
    boolean existsByUserID(String userId);
    boolean existsByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.code = ?1 WHERE a.userID = ?2")
    int updateCode(String newCode, String userID);


    @Query("SELECT u FROM Account u JOIN CommentProduct pc ON pc.userID = u.userID WHERE pc.commentID = ?1")
    Account findUserDetailsByCommentId(String commentId);
}