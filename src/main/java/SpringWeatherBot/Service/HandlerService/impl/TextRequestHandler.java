package SpringWeatherBot.Service.HandlerService.impl;

import SpringWeatherBot.Service.KeyboardService.KeyboardService;
import SpringWeatherBot.Service.RestService.RestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.regex.Pattern;
@Component
@Slf4j
public class TextRequestHandler implements RequestHandler{
    private static final Pattern CORRECT_SYMBOLS_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я- ]*");
    private final KeyboardService keyboardService;
    private final RestService restService;

    public TextRequestHandler(KeyboardService keyboardService, RestService restService) {
        this.keyboardService = keyboardService;
        this.restService = restService;
    }

    @Override
    public Boolean isApplicable(Update update) {
        return update.hasMessage() && !update.getMessage().getText().startsWith("/");
    }

    @Override
    public SendMessage handle(Update update) throws JsonProcessingException {
        Message message = update.getMessage();
        String text = message.getText();

        if(!CORRECT_SYMBOLS_PATTERN.matcher(text).matches()){
            log.error("Cannot find city.", text);
        }

        return  getBuilder()
                .chatId(message.getChatId())
                .text("Что тебя интересуют")
                .replyMarkup(keyboardService.getForecastButtons(text))
                .build();
    }
}
