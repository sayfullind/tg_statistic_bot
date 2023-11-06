package com.macloud.bot.updateReceiver;

import com.macloud.bot.updateSubscribers.UpdateSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class DefaultUpdateReceiver implements UpdateReceiver {

    private final List<UpdateSubscriber> subscribers;

    @Override
    public void processUpdate(Update update) {
        for (var subscriber: subscribers)
            subscriber.handle(update);
    }
}
