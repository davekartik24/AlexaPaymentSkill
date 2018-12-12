package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Map;
import java.util.Optional;

public class PreviousDaySummaryInvokeHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AskPrvDaySum"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        IntentRequest intentRequest = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        int days = Integer.parseInt(intentRequest.getIntent().getSlots().get("days").getValue(), 10);

        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("sessionState", "STARTED");
        sessionAttributes.put("days", "" + days);

        return handlerInput.getResponseBuilder()
                .withSpeech("please provide with your passcode")
                .withReprompt("please provide with your passcode")
                .build();
    }
}