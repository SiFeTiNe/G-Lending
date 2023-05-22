package com.gable.glending.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private int remaining;
    @ManyToMany(mappedBy = "borrowingItems")
    private List<Member> borrowers;
    private Instant createdAt;

    public void addBorrower(Member member) {
        borrowers.add(member);
    }
}
