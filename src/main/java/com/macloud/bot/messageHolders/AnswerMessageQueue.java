package com.macloud.bot.messageHolders;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

public interface AnswerMessageQueue {
    void addMessage(BotApiMethodMessage message);
    boolean hasMessage();
    BotApiMethodMessage getMessage();
}
