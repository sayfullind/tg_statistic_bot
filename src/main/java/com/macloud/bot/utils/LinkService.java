package com.macloud.bot.utils;

import lombok.extern.log4j.Log4j2;

import org.jsoup.Jsoup;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class LinkService {

    public SendMessage getMessage(SendMessage target) {
        final var entities = target.getEntities();
        final var text = new StringBuilder(target.getText()).append("\n\nИнтересные материалы от: ");
        final var chat_id = Long.valueOf(target.getChatId());

        return preparing(text, entities, chat_id);
    }


    private SendMessage preparing(
            StringBuilder text, List<MessageEntity> entities, Long chatId) {

        var urls = entities.stream()
                .filter(entity -> entity.getUrl() != null).toList().stream()
                .map(MessageEntity::getUrl).toList().stream()
                .map(string -> string.substring(0, string.lastIndexOf("/"))).toList().stream().
                filter(s -> !s.equals("https://t.me")).toList();

        var names = urls
                .stream()
                .map(this::getName)
                .toList();

        names.forEach(name -> text.append(name).append(", "));

        text.deleteCharAt(text.lastIndexOf(", "));

        for(int i = 0; i < names.size(); i++)
            entities.add(MessageEntity
                    .builder()
                    .text(names.get(i))
                    .url(urls.get(i))
                    .offset(text.indexOf(names.get(i)))
                    .length(names.get(i).length())
                    .type("text_link")
                    .build());

        return SendMessage
                .builder()
                .text(text.toString())
                .entities(entities)
                .chatId(chatId)
                .build();
    }

    private String getName(String url) {
        try {
            return  Jsoup
                    .connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("https://www.google.com")
                    .get()
                    .getElementsByTag("meta")
                    .get(2)
                    .attr("content");
        }
        catch (IOException e) {
            log.error(String.format("Unable to get channel's name from url: %s.", url));
            log.error(e);
            return null;
        }
    }
}
