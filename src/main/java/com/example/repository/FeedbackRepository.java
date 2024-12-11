package com.example.repository;

import com.example.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByServiceID(int serviceID); // Tìm phản hồi theo ID dịch vụ

    @Query("SELECT SUM(f.satisfactionLevel) FROM Feedback f WHERE f.serviceID = ?1")
    Integer findTotalRatingByServiceID(int serviceID); // Tính tổng đánh giá

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.serviceID = ?1")
    Integer countFeedbackByServiceID(int serviceID); // Tính số lượng phản hồi

    @Query("SELECT AVG(f.satisfactionLevel) FROM Feedback f WHERE f.serviceID = ?1")
    Double findAverageRatingByServiceID(int serviceID); // Tính đánh giá trung bình

    default Feedback saveFeedback(Feedback feedback) {
        return save(feedback); // Gọi save() của JpaRepository
    }
}
