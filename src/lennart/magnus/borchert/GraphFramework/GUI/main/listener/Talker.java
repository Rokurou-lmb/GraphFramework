package lennart.magnus.borchert.GraphFramework.GUI.main.listener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gery on 08.04.2015.
 */
public abstract class Talker {
    private Set<Listener> listeners;

    public void addListener(Listener l){
        this.listeners = new HashSet<Listener>();
        listeners.add(l);
    }

    protected void talk(){
        for(Listener l : listeners){
            l.act();
        }
    }
}
