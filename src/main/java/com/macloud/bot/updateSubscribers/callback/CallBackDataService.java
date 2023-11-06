package com.macloud.bot.updateSubscribers.callback;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CallBackDataService {

    public CallBackData createCallBackData(String string) {
        return Arrays.stream(CallBackData.values())
                .filter(callBackData -> callBackData.getText().equals(string))
                .findFirst()
                .get();
    }

}
