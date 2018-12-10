import com.amazonaws.util.StringInputStream;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class unitTestCheck {

    @org.junit.Test
    public void handleRequest() {
        PaymentSkillHandler handler = new PaymentSkillHandler();
        try {
            StringInputStream inputStream = new StringInputStream("{\n" +
                    "  \"version\": \"1.0\",\n" +
                    "  \"session\": {\n" +
                    "    \"new\": true,\n" +
                    "    \"sessionId\": \"amzn1.echo-api.session.1234567890\",\n" +
                    "    \"application\": {\n" +
                    "      \"applicationId\": \"amzn1.ask.skill.1234567890\"\n" +
                    "    },\n" +
                    "    \"user\": {\n" +
                    "      \"userId\": \"amzn1.ask.account.1234567890\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"context\": {\n" +
                    "    \"AudioPlayer\": {\n" +
                    "      \"playerActivity\": \"IDLE\"\n" +
                    "    },\n" +
                    "    \"Display\": {},\n" +
                    "    \"System\": {\n" +
                    "      \"application\": {\n" +
                    "        \"applicationId\": \"amzn1.ask.skill.1234567890\"\n" +
                    "      },\n" +
                    "      \"user\": {\n" +
                    "        \"userId\": \"amzn1.ask.account.1234567890\"\n" +
                    "      },\n" +
                    "      \"device\": {\n" +
                    "        \"deviceId\": \"amzn1.ask.device.1234567890\",\n" +
                    "        \"supportedInterfaces\": {\n" +
                    "          \"AudioPlayer\": {},\n" +
                    "          \"Display\": {\n" +
                    "            \"templateVersion\": \"1.0\",\n" +
                    "            \"markupVersion\": \"1.0\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"apiEndpoint\": \"https://api.amazonalexa.com\",\n" +
                    "      \"apiAccessToken\": \"1234567890\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"request\": {\n" +
                    "    \"type\": \"IntentRequest\",\n" +
                    "    \"requestId\": \"amzn1.echo-api.request.1234567890\",\n" +
                    "    \"timestamp\": \"2018-04-01T08:32:22Z\",\n" +
                    "    \"locale\": \"en-US\",\n" +
                    "    \"intent\": {\n" +
                    "      \"name\": \"SandboxStatus\",\n" +
                    "      \"confirmationStatus\": \"NONE\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            handler.handleRequest(inputStream, outputStream, null);
            String respond = outputStream.toString();
            Assert.assertNotNull(respond);
            System.out.println(respond);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
