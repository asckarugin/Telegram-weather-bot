package SpringWeatherBot.Service.HandlerService.impl;

import SpringWeatherBot.DTO.intenal.ForecastButtonDto;
import SpringWeatherBot.Service.RestService.RestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CallbackRequestHandler implements RequestHandler{
    private final RestService restService;
    ObjectMapper objectMapper = new ObjectMapper();

    public CallbackRequestHandler(RestService restService) {
        this.restService = restService;
    }

    @Override
    public Boolean isApplicable(Update update) {
        return update.hasCallbackQuery();
    }

    @Override
    public SendMessage handle(Update update) throws JsonProcessingException {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        ForecastButtonDto forecastButtonDto = objectMapper.readValue(callbackQuery.getData(), ForecastButtonDto.class);
        String forecast = restService.getForecast(forecastButtonDto.getCity(), forecastButtonDto.getDays());

        return getBuilder()
                .text(forecast)
                .chatId(callbackQuery.getMessage().getChatId().toString())
                .build();
    }
}
