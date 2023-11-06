package com.macloud.bot.messageHolders;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

import java.util.List;

public interface HistoryMessageHolder {
    void addMessage(BotApiMethodMessage message);
    List<BotApiMethodMessage> getHistory();
    BotApiMethodMessage getLastMessage();
}
