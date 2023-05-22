package com.gable.glending.dto;

import com.gable.glending.model.ActionType;
import com.gable.glending.model.Item;
import com.gable.glending.model.Member;
import lombok.Data;

import java.time.Instant;

@Data
public class BorrowReturnHistoryDto {

    private Long id;

    private Member member;

    private Item item;


    private ActionType action;

    private Instant timestamp;

    // Constructors, getters, and setters
}

