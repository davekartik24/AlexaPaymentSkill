package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import utils.QueryExecutor;

import java.sql.SQLException;
import java.util.Optional;

public class SummaryHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler{

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("ChenChang"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        String output = "i got your passcode, searching the summary now";
        try {
            output = QueryExecutor.getUserName();
        } catch (SQLException e) {
            output = e.getMessage();
        } catch (ClassNotFoundException e) {
            output = e.getMessage();
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(output)
                .build();
    }
}
