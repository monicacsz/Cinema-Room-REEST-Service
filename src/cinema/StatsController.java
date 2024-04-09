package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatsController
{
    private final String password = "super_secret";

    private final int totalOfSeats = 81;

    @Autowired
    private PurchasedTicketsContainer purchasedTickets;

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam(required = false) String password)
    {
        if (password == null || !password.equals(this.password))
        {
            HttpStatusHandler errorResponse = new HttpStatusHandler("The password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        return ResponseEntity.ok(new StatsResponse(calculateIncome(), calculateAvailability(), purchasedTickets.getPurchasedTickets().size()));
    }

    private int calculateIncome()
    {
        int income = 0;
        for (Map.Entry<String, Seat> seat : purchasedTickets.getPurchasedTickets().entrySet())
        {
            income += seat.getValue().getPrice();
        }
        return income;
    }

    private int calculateAvailability()
    {
        return totalOfSeats - purchasedTickets.getPurchasedTickets().size();
    }

}
