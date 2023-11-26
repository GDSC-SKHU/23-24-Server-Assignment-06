package com.gdsc.oauth2assignment.repository;

import com.gdsc.oauth2assignment.domain.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
