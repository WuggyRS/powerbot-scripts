package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;

public class SkipValidateEmail extends Task<ClientContext> {
    public SkipValidateEmail(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.widgets.widget(906).component(492).visible();
    }

    @Override
    public void execute() {

    }
}