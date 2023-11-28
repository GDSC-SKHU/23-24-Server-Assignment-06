package com.gdsc.noticeservice.service;

import com.gdsc.noticeservice.domain.User;
import com.gdsc.noticeservice.dto.UserDto;
import com.gdsc.noticeservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // READ : 작성자 이메일로 작성자 찾기
    @Transactional
    public User findConsumerByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("없는 작성자입니다."));
    }


    //DELETE - 작성자의 이메일을 조회해 작성자 삭제
    @Transactional
    public String deleteConsumer(UserDto userDto){
        User user = findConsumerByEmail(userDto.getEmail());
        userRepository.delete(user);
        return "삭제 완료!";
    }

    // 작성자가 존재하는지 안하는지 확인하는 코드
    public boolean doesConsumerExist(String consumerEmail){
        return userRepository.existsByEmail(consumerEmail);
    }
}
