package com.sportbot.olimp_push.repository;

import com.sportbot.olimp_push.model.PushUpEntry;
import com.sportbot.olimp_push.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


    public interface UserRepository extends JpaRepository<User, Long> {

    }
