package com.macloud.bot.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BotCommands {

    START("/start");

    private final String text;

}
