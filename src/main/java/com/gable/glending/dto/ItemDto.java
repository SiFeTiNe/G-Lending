package com.gable.glending.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemDto {
    private UUID id;
    private String name;
    private int remaining;
}
