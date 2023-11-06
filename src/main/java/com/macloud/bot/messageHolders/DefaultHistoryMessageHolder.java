package com.macloud.bot.messageHolders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultHistoryMessageHolder implements HistoryMessageHolder{

    private final ArrayList<BotApiMethodMessage> list;

    @Override
    public void addMessage(BotApiMethodMessage message) {
        list.add(message);
    }

    @Override
    public List<BotApiMethodMessage> getHistory() {
        final var hist = new ArrayList<BotApiMethodMessage>(Collections.emptyList());
        hist.addAll(list);
        return hist;
    }

    @Override
    public BotApiMethodMessage getLastMessage() {
        return list.get(list.size() - 1);
    }
}
