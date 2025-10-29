package com.sportbot.olimp_push.repository;

import com.sportbot.olimp_push.model.PushUpEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PushUpEntryRepository extends JpaRepository<PushUpEntry, Long> {
    List<PushUpEntry> findByChatIdAndTimestampBetween(Long chatId, LocalDateTime start, LocalDateTime end);
}
