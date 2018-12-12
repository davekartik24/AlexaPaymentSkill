package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import utils.QueryExecutor;

import java.sql.SQLException;
import java.util.Optional;

public class transactionHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler {
  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(Predicates.intentName("transactionIndent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    String name = "invalid user";
    try {
      name = QueryExecutor.getUserName();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return handlerInput.getResponseBuilder()
        .withSpeech(name)
        .withReprompt(name)
        .build();
  }
}
