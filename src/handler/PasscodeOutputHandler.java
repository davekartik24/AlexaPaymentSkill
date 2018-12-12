package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Map;
import java.util.Optional;

import static utils.QueryExecutor.*;

public class PasscodeOutputHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler{

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        boolean isStarted = false;
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();

        if(sessionAttributes.get("sessionState") != null && sessionAttributes.get("sessionState").equals("STARTED")) {
            isStarted = true;
        }
        return (isStarted && handlerInput.matches(Predicates.intentName("AskPasscode")));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        IntentRequest intentRequest = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        int passcode = Integer.parseInt(intentRequest.getIntent().getSlots().get("number").getValue(), 10);

        String outTest = "";

        int code = validateUser(passcode);
        if(code == 0) {
            outTest = getAdminSummaries();
        }
        else if(code>0){
            outTest = getMerchantSummaries(passcode);
        }
        else{
            outTest = "there seems some issue with your passcode please contact customer service";
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(outTest)
                .build();
    }
}
