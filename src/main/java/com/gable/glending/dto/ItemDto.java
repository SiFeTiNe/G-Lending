package com.gable.glending.dto;

import com.gable.glending.model.Member;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ItemDto {
    private UUID id;
    private String name;
    private int remaining;

    // Prepare to be used in ItemService
    private boolean isBorrowedByMember = false;
    private List<Member> borrowers;
}
