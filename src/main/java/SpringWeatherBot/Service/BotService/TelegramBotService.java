package SpringWeatherBot.Service.BotService;

import SpringWeatherBot.BotConfig.property.BotConfig;
import SpringWeatherBot.Service.HandlerService.RequestHandlerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBotService extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final RequestHandlerService requestHandlerService;
    @Autowired
    public TelegramBotService(BotConfig botConfig, RequestHandlerService requestHandlerService) {
        this.botConfig = botConfig;
        this.requestHandlerService = requestHandlerService;
    }
    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }
    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(requestHandlerService.handle(update));
        } catch (TelegramApiException | JsonProcessingException e) {
            SendMessage errorSendMessage = new SendMessage();
            errorSendMessage.setText("Error occurred: "+ e.getMessage());
            errorSendMessage.setChatId(update.getMessage().getChatId().toString());
            try {
                execute(errorSendMessage);
            } catch (TelegramApiException ex) {
                log.error("Error occurred: "+ex.getMessage());
            }
        }
    }
}
