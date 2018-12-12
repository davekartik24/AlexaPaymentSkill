package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import java.sql.SQLException;
import java.util.Optional;
import utils.QueryExecutor;

public class OpenEventHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler {

  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(Predicates.intentName("EventIndent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    try {
      int openEvent = QueryExecutor.getOpenEvent();
      String output = "";
      if(openEvent == 0) output = "Currently there is no open events";
      else output = "Currently there are " + openEvent + " open events";
      return handlerInput.getResponseBuilder()
          .withSpeech(output)
          .withReprompt(output)
          .build();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    String output = "there are some issue when searching for open events, please contact customer service or try again later";
    return handlerInput.getResponseBuilder()
        .withSpeech(output)
        .withReprompt(output)
        .build();

  }

}
