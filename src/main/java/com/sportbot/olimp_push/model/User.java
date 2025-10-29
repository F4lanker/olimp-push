package com.sportbot.olimp_push.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "app_user")
@Data
public class User {
    @Id
    private long chatId;

    private String userName;
    private String firstName;
    // Вес в килограммах, например: 72.50
    private BigDecimal weight;
    // Рост в сантиметрах
    private int height;

}
