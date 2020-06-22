package com.tdcs.epg.servicesImp;


import com.tdcs.epg.entities.User;
import com.tdcs.epg.repositories.UserRepository;
import com.tdcs.epg.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
