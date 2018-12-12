package handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

public class SandboxStatusHandler implements com.amazon.ask.dispatcher.request.handler.RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("SandboxStatus"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = sandboxStatus(handlerInput);
        return handlerInput.getResponseBuilder().withSpeech(speechText).build();
    }

    public String sandboxStatus(HandlerInput handlerInput) {

        try {
            URL siteURL = new URL("https://www.testvantivcnp.com/sandbox/status");
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            if (connection.getResponseCode() == 200) {
                return "  Sandbox is online thanks to genius interns";
            } else {
                return "  Sandbox is offline";
            }
        } catch (Exception e) {
            return "error in connecting please contact world pay";
        }
    }
}
