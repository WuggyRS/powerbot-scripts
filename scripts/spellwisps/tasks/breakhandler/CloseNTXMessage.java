package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

public class CloseNTXMessage extends Task<ClientContext> {
    private static Component NTXComponent;

    public CloseNTXMessage(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return (NTXComponent = ctx.widgets.component(906, 558)).visible();
    }

    @Override
    public void execute() {
        NTXComponent.click();
    }
}