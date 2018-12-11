import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import handler.SandboxHandler;

public class PaymentSkillStreamHandler extends SkillStreamHandler   {

    public PaymentSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandlers(new SandboxHandler())
                .build());
    }
}
