package com.gable.glending.service;

import com.gable.glending.dto.ItemDto;
import com.gable.glending.model.Item;
import com.gable.glending.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    //   ----> we are mapping DAO → DTO
    public List<ItemDto> getItems() {
        List<Item> items = repository.findAll();

        List<ItemDto> dtos = items
                .stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());

        return dtos;
    }


    //   ----> we are mapping DTO → DAO
    public void create(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        item.setCreatedAt(Instant.now());
        repository.save(item);
    }
}
