package org.binar.kamihikoukiairlines.service;

import org.binar.kamihikoukiairlines.model.TransactionHistory;
import org.binar.kamihikoukiairlines.model.Users;
import org.binar.kamihikoukiairlines.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;


    public TransactionService(TransactionHistoryRepository transactionHistoryRepository){
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public List<TransactionHistory> getTransactionByUser(Long userId){
        return transactionHistoryRepository.findByUsers_Id(userId);
    }
}
