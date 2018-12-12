package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.slf4j.Logger;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class ExceptionHandler implements com.amazon.ask.dispatcher.exception.ExceptionHandler {

    @Override
    public boolean canHandle(HandlerInput input, Throwable throwable) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput input, Throwable throwable) {
        return input.getResponseBuilder()
                .withSpeech("Sorry, I can't understand the command. Please say again.")
                .withReprompt("Sorry, I can't understand the command. Please say again.")
                .build();
    }
}
