package com.gable.glending.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gable.glending.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
