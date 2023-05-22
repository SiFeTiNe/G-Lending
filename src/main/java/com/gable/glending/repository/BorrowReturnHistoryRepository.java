package com.gable.glending.repository;

import com.gable.glending.model.BorrowReturnHistory;
import com.gable.glending.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowReturnHistoryRepository extends JpaRepository<BorrowReturnHistory, Long> {

    List<BorrowReturnHistory> findByMember(Member user);
}
