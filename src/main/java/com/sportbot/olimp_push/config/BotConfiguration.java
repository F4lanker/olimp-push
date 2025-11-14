package com.sportbot.olimp_push.config;

import com.sportbot.olimp_push.bot.SportBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfiguration {
        @Bean
        public TelegramBotsApi telegramBotsApi(SportBot sportBot) throws TelegramApiException {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(sportBot);
            return botsApi;
        }
    }

