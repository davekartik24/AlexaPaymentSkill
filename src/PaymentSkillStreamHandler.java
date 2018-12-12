import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import handler.*;

public class PaymentSkillStreamHandler extends SkillStreamHandler   {

    public PaymentSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandlers(new SandboxStatusHandler(), new SummaryInvokeHandler(), new PasscodeOutputHandler(), new transactionHandler(), new PreviousDaySummaryInvokeHandler())
                .addExceptionHandler(new ExceptionHandler())
                .build());
    }
}
