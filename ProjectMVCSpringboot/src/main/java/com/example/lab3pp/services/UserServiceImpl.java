package com.example.lab3pp.services;


import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.models.User;
import com.example.lab3pp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDetailsService")
@AllArgsConstructor
@Profile(ProfileNames.USERS_IN_DATABASE)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    @Override
    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public void add(User user){userRepo.save(user);}
    public User getById(Long id){return userRepo.getById(id);}
    public List<User> findAll(){return userRepo.findAll();}

    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

    public void saveAll(List<User> userList) {
        userRepo.saveAll(userList);
    }
}
