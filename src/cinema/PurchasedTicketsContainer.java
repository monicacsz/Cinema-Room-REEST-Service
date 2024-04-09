package cinema;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class PurchasedTicketsContainer
{
    private Map<String, Seat> purchasedTickets = new HashMap<>();

    public void addTicketAndToken(String token, Seat ticket)
    {
        this.purchasedTickets.put(token, ticket);
    }
    public void removeTicketByToken(String token)
    {
        purchasedTickets.remove(token);
    }

    public Map<String, Seat> getPurchasedTickets()
    {
        return this.purchasedTickets;
    }
}
