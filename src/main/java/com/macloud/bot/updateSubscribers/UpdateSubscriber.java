package com.macloud.bot.updateSubscribers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateSubscriber {
    void handle(Update update);
}
