package com.macloud.bot.updateSubscribers.text;

import com.macloud.bot.commands.BotCommandsService;

import com.macloud.bot.messageHolders.AnswerMessageQueue;
import com.macloud.bot.updateSubscribers.UpdateSubscriber;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
@RequiredArgsConstructor
public class CommandTextMessageSubscriber implements UpdateSubscriber {

    private final AnswerMessageQueue queue;
    private final BotCommandsService service;

    @Override
    public void handle(Update update) {
        if (update.hasMessage()) {
            var message = update.getMessage();
            if (message.hasText() && service.isCommand(message.getText())) {
                final var command = service.getCommand(
                        update.getMessage().getText());

                switch (command) {
                    case START -> start(update);
                }
            }
        }
    }

    private void start(Update update) {
        final var chat_id = update.getMessage().getChatId();
        final var nickname = update.getMessage().getChat().getFirstName();
        final var text = String.format("Hello, %s!", nickname);

        final var message = SendMessage
                .builder()
                .chatId(chat_id)
                .text(text)
                .build();

        queue.addMessage(message);
    }
}
