package com.sportbot.olimp_push.bot;

import com.sportbot.olimp_push.model.User;
import com.sportbot.olimp_push.repository.PushUpEntryRepository;
import com.sportbot.olimp_push.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import lombok.*;

@Component
public class SportBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;
    private final UserRepository userRepository;

    public SportBot(
            @Value("${TELEGRAM_BOT_TOKEN}") String botToken,
            @Value("${TELEGRAM_BOT_USERNAME}") String botUsername,
            UserRepository userRepository
    ){
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.userRepository = userRepository;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String text = message.getText();

            // Сохраняем/обновляем пользователя
            User user = new User();
            user.setChatId(chatId);
            user.setUserName(message.getFrom().getUserName());
            user.setFirstName(message.getFrom().getFirstName());
            userRepository.save(user);

            // Простой ответ
            if (text.equals("/start")) {
                sendText(chatId, "Привет! Отправь количество отжиманий (например: 20).");
            } else if (text.matches("\\d+")) {
                int count = Integer.parseInt(text);
                String grade = getGrade(count);
                sendText(chatId, "Отлично! " + count + " отжиманий — это " + grade + " 💪");
            } else {
                sendText(chatId, "Пришли число — сколько отжался. Или /start для помощи.");
            }
        }
    }

    private String getGrade(int count) {
        if (count >= 50) return "идеально! 🏆";
        if (count >= 40) return "отлично! 🥇";
        if (count >= 30) return "хорошо! 🥈";
        if (count >= 20) return "неплохо! 🥉";
        if (count >= 10) return "пойдёт! 👍";
        return "старт задан! 💪";
    }

    private void sendText(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
