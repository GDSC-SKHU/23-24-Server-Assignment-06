package com.gdsc.oauth2assignment.service;

import com.gdsc.oauth2assignment.domain.Text;
import com.gdsc.oauth2assignment.domain.User;
import com.gdsc.oauth2assignment.dto.TextResDto;
import com.gdsc.oauth2assignment.dto.TextSaveReqDto;
import com.gdsc.oauth2assignment.repository.TextRepository;
import com.gdsc.oauth2assignment.repository.UserRepository;
import java.security.Principal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TextService {
    private final TextRepository textRepository;
    private final UserRepository userRepository;

    public TextService(TextRepository textRepository, UserRepository userRepository) {
        this.textRepository = textRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String createText(TextSaveReqDto requestDto, Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        Text text = requestDto.toEntity(user);
        textRepository.save(text);
        return "저장 성공!";
    }

    public TextResDto findTextById(Long id) {
        Text text = textRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        return new TextResDto(text);
    }
}
