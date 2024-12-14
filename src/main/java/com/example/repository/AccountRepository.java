package com.example.repository;

import com.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

    // Tìm tất cả các tài khoản có role = 3 (Khách hàng)
    List<Account> findByRole(int role);

    // Khóa tài khoản (Cập nhật status thành 0)
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.status = 0 WHERE a.userID = ?1")
    int lockAccount(String userID);

    // Mở khóa tài khoản (Cập nhật status thành 1)
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.status = 1 WHERE a.userID = ?1")
    int unlockAccount(String userID);


    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 3")
    int countUserByRole();

    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 4")
    int countCTVByRole();
}
