package com.dondoc.service;

import com.dondoc.dto.Users;
import com.dondoc.dto.auth.LoginRequest;
import com.dondoc.dto.auth.LoginResponse;
import com.dondoc.entity.User;
import com.dondoc.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByUserId(request.getUserId()).orElse(null);

        if(user == null || !user.getUserPassword().equals(request.getUserPassword())) {
            return null;
        } else  {
            return new LoginResponse(
                    user.getId(),
                    user.getName(),
                    user.getAge(),
                    user.getMonthlyIncome(),
                    user.getTargetExpenseRatio(),
                    user.getCurrentPigLevel(),
                    user.getCurrentHouseLevel(),
                    user.getCurrentCharacterLevel()
            );
        }
    }

    public void createUser(Users dto){
        User user = new User(
                null,
                dto.getUserId(),
                dto.getUserPassword(),
                dto.getName(),
                dto.getAge(),
                dto.getCurrentPigLevel(),
                dto.getCurrentHouseLevel(),
                dto.getCurrentCharacterLevel(),
                dto.getMonthlyIncome(),
                dto.getTargetExpenseRatio(),
                dto.getCreatedAt()
        );
        userRepository.save(user);
    }
}
