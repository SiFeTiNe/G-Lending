package com.gable.glending.service;

import com.gable.glending.dto.MemberDto;
import com.gable.glending.model.Member;
import com.gable.glending.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MemberDto> getMembers() {
        List<Member> members = memberRepository.findAll();

        List<MemberDto> dtos = members
                .stream()
                .map(item -> modelMapper.map(item, MemberDto.class))
                .collect(Collectors.toList());

        return dtos;
    }
}
