package com.gable.glending.service;

import com.gable.glending.dto.SignupDto;
import com.gable.glending.model.Member;
import com.gable.glending.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Service
public class SignupService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public int createMember(SignupDto member) {
        Member newMember = modelMapper.map(member, Member.class);
        newMember.setCreatedAt(Instant.now());

        String hashedPassword = passwordEncoder.encode(member.getPassword());

        newMember.setPassword(hashedPassword);

        repository.save(newMember);
        return 1;
    }

    public Member getMember(String username) {
        return repository.findByUsername(username);
    }
}
