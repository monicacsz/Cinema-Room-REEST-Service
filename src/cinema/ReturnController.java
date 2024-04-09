package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController
{
    @Autowired
    private PurchasedTicketsContainer purchasedTickets;

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody ReturnRequest request)
    {
        if(purchasedTickets.getPurchasedTickets().containsKey(request.getToken()))
        {
            Seat seat = purchasedTickets.getPurchasedTickets().get(request.getToken());
            seat.setOccupied(false);

            ReturnResponse returnResponse = new ReturnResponse(seat);
            purchasedTickets.removeTicketByToken(request.getToken());

            return ResponseEntity.ok(returnResponse); //Ticket returned.
        }

        HttpStatusHandler errorResponse = new HttpStatusHandler("Wrong token!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
