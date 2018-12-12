package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

public class SummaryInvokeHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler{

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AskSummary"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("sessionState", "STARTED");

        return handlerInput.getResponseBuilder()
                .withSpeech("  Please provide your passcode")
                .withReprompt("  Please provide with your passcode")
                .build();
    }
}
