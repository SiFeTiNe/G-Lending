package com.gable.glending.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int remaining;
    private Instant createdAt;
}
