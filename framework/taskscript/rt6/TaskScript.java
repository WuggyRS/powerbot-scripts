package framework.taskscript.rt6;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TaskScript extends PollingScript<ClientContext> {
    public ArrayList<Task> tasks = new ArrayList<>();
    public abstract void onStart();

    @Override
    public void start() {
        onStart();
    }

    @Override
    public void poll() {
        for (Task t : tasks) {
            if (t.validate()) {
                t.execute();
                return;
            }
        }
    }

    public void add(Task... t) {
        tasks.addAll(Arrays.asList(t));
    }
}
