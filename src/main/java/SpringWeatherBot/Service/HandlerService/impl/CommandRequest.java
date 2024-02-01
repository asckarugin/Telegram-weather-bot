package SpringWeatherBot.Service.HandlerService.impl;

import SpringWeatherBot.Enums.BotCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class CommandRequest implements RequestHandler {
    @Override
    public Boolean isApplicable(Update update) {
        return update.hasMessage() && update.getMessage().getText().startsWith("/");
    }

    @Override
    public SendMessage handle(Update update) throws JsonProcessingException {
        Message message = update.getMessage();
        String commandText = message.getText();

        BotCommand botCommand = BotCommand.ALL.get(commandText.toLowerCase());
        if(botCommand == null){
            log.error("Unknown command", commandText);

        }
        return getBuilder()
                .text(botCommand.getCommandLine())
                .chatId(message.getChatId())
                .build();
    }
}
