package com.macloud.bot.commands;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public final class BotCommandsService {
    public boolean isCommand(String string) {
        return Arrays.stream(BotCommands.values()).anyMatch(
                command -> string.equals(command.getText())
        );
    }
    public BotCommands getCommand(String string) {
        return Arrays
                .stream(BotCommands.values())
                .filter(command -> string.equals(command.getText()))
                .findFirst()
                .get();
    }
}
