package com.gdsc.oauth2assignment.controller;

import com.gdsc.oauth2assignment.dto.TextResDto;
import com.gdsc.oauth2assignment.dto.TextSaveReqDto;
import com.gdsc.oauth2assignment.service.TextService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("text")
public class TextController {

    private final TextService textService;

    @PostMapping("/new")
    public String createText(@RequestBody TextSaveReqDto reqDto, Principal principal) {
        return textService.createText(reqDto, principal);
    }

    @GetMapping("/{id}")
    public TextResDto findTextById(@PathVariable Long id) {
        return textService.findTextById(id);
    }
}
