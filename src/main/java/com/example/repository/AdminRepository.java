package com.example.repository;

import com.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Account, Long> {

    // Đếm tổng số người dùng
    @Query("SELECT COUNT(a) FROM Account a  WHERE a.role = 3")
    long countUsers();

    // Đếm số cộng tác viên (role = 2)
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 2")
    long countEm();

    // Đếm số cộng tác viên (role = 2)
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 4")
    long countCollaboratorsProduct();

    // Đếm số cộng tác viên (role = 1)
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 5")
    long countCollaboratorsSv();

    // Giả định bạn có sẵn các giá trị từ cơ sở dữ liệu

    // Tính tổng giá trị đơn hàng trong ngày

}