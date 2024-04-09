package cinema;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PurchaseResponse
{
    private String token;
    private Seat ticket;

    public PurchaseResponse(Seat ticket)
    {
        this.token = generateToken();
        this.ticket = ticket;

    }

    public String getToken()
    {
        return token;
    }

    public Seat getTicket()
    {
        return ticket;
    }

    private String generateToken()
    {
        return UUID.randomUUID().toString();
    }
}
