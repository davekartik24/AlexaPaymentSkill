import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import handler.PasscodeOutputHandler;
import handler.SandboxHandler;
import handler.SummaryInvokeHandler;

public class PaymentSkillStreamHandler extends SkillStreamHandler   {

    public PaymentSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandlers(new SandboxHandler(), new SummaryInvokeHandler(), new PasscodeOutputHandler())
                .build());
    }
}
