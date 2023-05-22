package com.gable.glending.dto;

import com.gable.glending.model.Item;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class MemberDto {
    private UUID id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    private List<Item> borrowingItems = new ArrayList<>();
    private Instant createdAt;
}
