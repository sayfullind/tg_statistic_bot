package com.macloud.bot.updateSubscribers.text;

import com.macloud.bot.commands.BotCommandsService;

import com.macloud.bot.messageHolders.AnswerMessageQueue;
import com.macloud.bot.updateSubscribers.UpdateSubscriber;
import com.macloud.bot.updateSubscribers.callback.CallBackData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class TextMessageSubscriber implements UpdateSubscriber {

    private final BotCommandsService service;
    private final AnswerMessageQueue queue;

    @Override
    public void handle(Update update) {
        if (update.hasMessage()
                && update.getMessage().hasText()
                && !service.isCommand(update.getMessage().getText())
        ) {

            final var chat_id = update.getMessage().getChatId();
            final var text = update.getMessage().getText();
            final var entities = update.getMessage().getEntities();
            final var keyboard = createDefaultInlineKeyBoard();

            final var answer = SendMessage.builder()
                    .text(text)
                    .entities(entities)
                    .chatId(chat_id)
                    .replyMarkup(keyboard)
                    .build();

            queue.addMessage(answer);
        }
    }

    private InlineKeyboardMarkup createDefaultInlineKeyBoard() {
        return InlineKeyboardMarkup
                .builder()
                .keyboardRow(Collections.singletonList(InlineKeyboardButton
                        .builder()
                        .text(CallBackData.ADD_LINK.getText())
                        .callbackData(CallBackData.ADD_LINK.getText())
                        .build()))
                .build();
    }
}
