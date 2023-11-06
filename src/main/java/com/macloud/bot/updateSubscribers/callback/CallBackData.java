package com.macloud.bot.updateSubscribers.callback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum CallBackData {
    ADD_LINK("Add links to post.");

    private final String text;
}
