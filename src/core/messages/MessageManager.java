package core.messages;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class MessageManager {

    public static ArrayList<Message> messages;

    public static void init()
    {
        messages = new ArrayList<Message>();
    }

    public static void addMessage(Message message)
    {
        messages.add(message);
    }

    public static void update()
    {
        for (int i=0; i< messages.size(); i++)
        {
            messages.get(i).update();

            if(messages.get(i).getTimeLeft() ==0)
            {
                messages.remove(i);
                i--;
            }
        }
    }

    public static void render(Graphics g)
    {
        for (Message m: messages)
        {

            m.render(g);
        }
    }
}
