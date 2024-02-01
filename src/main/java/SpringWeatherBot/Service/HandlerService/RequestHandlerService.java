package SpringWeatherBot.Service.HandlerService;

import SpringWeatherBot.Service.HandlerService.impl.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

@Component
@Slf4j
public class RequestHandlerService {
    private final Set<RequestHandler> requestHandlers;

    @Autowired
    public RequestHandlerService(Set<RequestHandler> requestHandlers) {
        this.requestHandlers = requestHandlers;
    }

    public SendMessage handle(Update update) throws JsonProcessingException{

        for(RequestHandler handler : requestHandlers){
            if(handler.isApplicable(update)){
                return handler.handle(update);
            }
        }
        log.error("Can't find handler");
        throw new UnsupportedOperationException();
    }
}
