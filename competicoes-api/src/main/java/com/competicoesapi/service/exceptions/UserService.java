package com.competicoesapi.service.exceptions;

import com.competicoesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public Long getNextKriptonUserId() {
        return userRepository.getNextKriptonUserId();
    }
}
