package com.macloud.bot.messageHolders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class DefaultAnswerMessageQueue implements AnswerMessageQueue {

    private final HistoryMessageHolder history;
    private final LinkedList<BotApiMethodMessage> queue;
    @Override
    public void addMessage(BotApiMethodMessage message) {
        queue.add(message);
    }

    @Override
    public boolean hasMessage() {
        return !queue.isEmpty();
    }

    @Override
    public BotApiMethodMessage getMessage() {
        final var message = queue.poll();
        history.addMessage(message);
        return message;
    }
}
