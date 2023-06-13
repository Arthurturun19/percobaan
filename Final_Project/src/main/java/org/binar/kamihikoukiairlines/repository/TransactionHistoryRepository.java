package org.binar.kamihikoukiairlines.repository;

import org.binar.kamihikoukiairlines.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByUsers_Id(Long userId);
}
