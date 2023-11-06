package com.macloud.bot.bot;

import com.macloud.bot.messageHolders.AnswerMessageQueue;
import com.macloud.bot.updateReceiver.UpdateReceiver;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j2
@Component
public final class Bot extends TelegramLongPollingBot {

    private final UpdateReceiver receiver;
    private final AnswerMessageQueue queue;

    @Value("${bot.username}")
    private String username;

    public Bot(
            @Value("${bot.token}") String token,
            UpdateReceiver receiver,
            AnswerMessageQueue queue)
    {
        super(token);
        this.receiver = receiver;
        this.queue = queue;
    }

    @Override
    public void onUpdateReceived(Update update) {
        receiver.processUpdate(update);

        while (queue.hasMessage())
            send(queue.getMessage());
    }

    private void send(BotApiMethodMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Unable to send the message: ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return
                username;
    }
}
