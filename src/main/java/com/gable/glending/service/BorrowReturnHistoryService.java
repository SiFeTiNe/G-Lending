package com.gable.glending.service;

import com.gable.glending.dto.BorrowReturnHistoryDto;
import com.gable.glending.dto.ItemDto;
import com.gable.glending.model.BorrowReturnHistory;
import com.gable.glending.model.Item;
import com.gable.glending.model.Member;
import com.gable.glending.repository.BorrowReturnHistoryRepository;
import com.gable.glending.repository.ItemRepository;
import com.gable.glending.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BorrowReturnHistoryService {

    @Autowired
    private BorrowReturnHistoryRepository historyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository memberRepository;

    //   ----> we are mapping DAO → DTO
    public List<BorrowReturnHistoryDto> getHistories() {
        List<BorrowReturnHistory> histories = historyRepository.findAll();

        List<BorrowReturnHistoryDto> dtos = histories
                .stream()
                .map(history -> modelMapper.map(history, BorrowReturnHistoryDto.class))
                .collect(Collectors.toList());
        Collections.reverse(dtos);

        return dtos;
    }

    public List<BorrowReturnHistoryDto> getHistory(Authentication auth) {
        List<BorrowReturnHistory> histories = historyRepository.findByMember(memberRepository.findByUsername(auth.getName()));

        List<BorrowReturnHistoryDto> dtos = histories
                .stream()
                .map(history -> modelMapper.map(history, BorrowReturnHistoryDto.class))
                .collect(Collectors.toList());
        Collections.reverse(dtos);

        return dtos;
    }
}
