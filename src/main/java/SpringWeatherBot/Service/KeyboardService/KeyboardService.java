package SpringWeatherBot.Service.KeyboardService;

import SpringWeatherBot.BotConfig.property.ForecastConfig;
import SpringWeatherBot.DTO.intenal.ForecastButtonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardService {
    private final ForecastConfig forecastConfig;
    private final ObjectMapper objectMapper;

    @Autowired
    public KeyboardService(ForecastConfig forecastConfig, ObjectMapper objectMapper) {
        this.forecastConfig = forecastConfig;
        this.objectMapper = objectMapper;
    }

    public InlineKeyboardMarkup getForecastButtons(String cityName) throws JsonProcessingException {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (ForecastConfig.Button buttonProperties : forecastConfig.getButtons()) {
            ForecastButtonDto buttonDto = new ForecastButtonDto();
            buttonDto.setDays(buttonProperties.getDays());
            buttonDto.setCity(cityName);
            String serializedButtonDto = objectMapper.writeValueAsString(buttonDto);

            InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
            keyboardButton.setText(buttonProperties.getText());
            keyboardButton.setCallbackData(serializedButtonDto);

            List<InlineKeyboardButton> buttonRow = new ArrayList<>();
            buttonRow.add(keyboardButton);
            rowList.add(buttonRow);
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rowList);
        return keyboardMarkup;
    }
}
