package com.gable.glending.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    @ManyToMany
    @JoinTable(
            name = "borrowing_items",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> borrowingItems = new ArrayList<>();
    private Instant createdAt;

    public void borrowItem(Item item) {
        if (!borrowingItems.contains(item) && item.getRemaining() >= 1) {
            borrowingItems.add(item);
            item.setRemaining(item.getRemaining() - 1);
        }
    }

    public void returnItem(Item item) {
        if (borrowingItems.contains(item)) {
            borrowingItems.remove(item);
            item.setRemaining(item.getRemaining() + 1);
        }
    }
}
