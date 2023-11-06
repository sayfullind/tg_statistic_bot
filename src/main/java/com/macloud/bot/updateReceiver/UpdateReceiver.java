package com.macloud.bot.updateReceiver;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateReceiver {
    void processUpdate(Update update);
}
