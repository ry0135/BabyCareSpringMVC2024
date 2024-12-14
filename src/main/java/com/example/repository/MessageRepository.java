package com.example.repository;
import com.example.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE (m.toId = :toId AND m.fromId = :fromId) " +
            "OR (m.toId = :fromId AND m.fromId = :toId) ORDER BY m.timestamp ASC")
    List<Message> findMessagesBetween(
            @Param("toId") String toId,
            @Param("fromId") String fromId);


//    @Query("SELECT m FROM Message m " +
//            "WHERE m.timestamp = (SELECT MAX(m2.timestamp) " +
//            "FROM Message m2 " +
//            "WHERE m2.fromId = :fromId AND m2.toId = m.toId) " +
//            "AND m.fromId = :fromId")
//    List<Message> findLatestMessages(@Param("fromId") String fromId);
@Query("SELECT m FROM Message m " +
        "WHERE m.timestamp = (SELECT MAX(m2.timestamp) " +
        "FROM Message m2 " +
        "WHERE m2.fromId = :fromId AND m2.toId = m.toId) " +
        "AND m.fromId = :fromId " +
        "ORDER BY m.timestamp DESC")
List<Message> findLatestMessages(@Param("fromId") String fromId);

}
