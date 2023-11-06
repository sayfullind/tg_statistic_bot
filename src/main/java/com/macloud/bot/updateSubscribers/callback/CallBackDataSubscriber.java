package com.macloud.bot.updateSubscribers.callback;

import com.macloud.bot.messageHolders.AnswerMessageQueue;
import com.macloud.bot.messageHolders.HistoryMessageHolder;
import com.macloud.bot.updateSubscribers.UpdateSubscriber;
import com.macloud.bot.utils.LinkService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CallBackDataSubscriber implements UpdateSubscriber {

    private final AnswerMessageQueue queue;
    private final HistoryMessageHolder history;

    private final LinkService links;
    private final CallBackDataService service;

    @Override
    public void handle(Update update) {
        if (update.hasCallbackQuery()) {
            final var data = service.createCallBackData(update.getCallbackQuery().getData());

            switch (data) {
                case ADD_LINK -> addLinks();
            }
        }
    }

    private void addLinks() {
        final var message = (SendMessage) history.getLastMessage();
        queue.addMessage(links.getMessage(message));
    }
}
