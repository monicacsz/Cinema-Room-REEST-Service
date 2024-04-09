package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PurchaseController {

    @Autowired
    private PurchasedTicketsContainer purchasedTickets;

    @Autowired
    private Room room;

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody PurchaseRequest request)
    {
        if (request.getRow() < 1 || request.getRow() > room.getRows() ||
                request.getColumn() < 1 || request.getColumn() > room.getColumns())
        {
            HttpStatusHandler errorResponse = new HttpStatusHandler("The number of a row or a column is out of bounds!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        else
        {

            for (Seat aSeat : room.getSeats()) {
                if (aSeat.getRow() == request.getRow() && aSeat.getColumn() == request.getColumn() && aSeat.isOccupied())
                {
                    HttpStatusHandler errorResponse = new HttpStatusHandler("The ticket has been already purchased!");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                }

                if (aSeat.getRow() == request.getRow() && aSeat.getColumn() == request.getColumn() && !aSeat.isOccupied())
                {
                    aSeat.setOccupied(true);
                    PurchaseResponse ticketResponse = new PurchaseResponse(aSeat);
                    String token = ticketResponse.getToken();
                    purchasedTickets.addTicketAndToken(token, aSeat);

                    // Return the purchased ticket details
                    return ResponseEntity.ok(ticketResponse);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

