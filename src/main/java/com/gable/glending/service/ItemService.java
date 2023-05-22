package com.gable.glending.service;

import com.gable.glending.dto.ItemDto;
import com.gable.glending.model.Item;
import com.gable.glending.model.Member;
import com.gable.glending.repository.ItemRepository;
import com.gable.glending.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository memberRepository;

    //   ----> we are mapping DAO → DTO
    public List<ItemDto> getItems(Authentication auth) {
        List<Item> items = itemRepository.findAll();

        List<ItemDto> dtos = items
                .stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());

        Member member = memberRepository.findByUsername(auth.getName());
        for(ItemDto dto : dtos) {
            if (dto.getBorrowers().contains(member)) {
                dto.setBorrowedByMember(true);
            }
        }

        return dtos;
    }


    //   ----> we are mapping DTO → DAO
    public void create(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        item.setCreatedAt(Instant.now());
        itemRepository.save(item);
    }

    public void borrowItem(Authentication auth, UUID itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            Member member = memberRepository.findByUsername(auth.getName());
            member.borrowItem(item.get());
            memberRepository.save(member);
        }

        // System.out.println(member.getUsername());
    }

    public void returnItem(Authentication auth, UUID itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            Member member = memberRepository.findByUsername(auth.getName());
            member.returnItem(item.get());
            memberRepository.save(member);
        }
    }

    public List<Member> getBorrowers(UUID itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            return item.get().getBorrowers();
        }
        return List.of();
    }

    public ItemDto getItemDtoById(UUID itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            return modelMapper.map(item.get(), ItemDto.class);
        }
        return null;
    }

    public List<ItemDto> getItemsByUsername(Authentication auth, String username) {
        Member member = memberRepository.findByUsername(username);

        List<Item> items = member.getBorrowingItems();

        List<ItemDto> dtos = items
                .stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());


        Member authMember = memberRepository.findByUsername(auth.getName());
        for(ItemDto dto : dtos) {
            if (dto.getBorrowers().contains(authMember)) {
                dto.setBorrowedByMember(true);
            }
        }

        return dtos;
    }
}
