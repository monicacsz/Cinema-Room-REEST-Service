package cinema;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Room
{
    private final int rows = 9;
    private final int columns = 9;
    private List<Seat> seats;

    public Room()
    {
        this.seats = new ArrayList<>();
        // Initialize seats
        for (int row = 1; row <= rows; row++)
        {
            for (int column = 1; column <= columns; column++)
            {
                seats.add(new Seat(row, column, calculateTicketPrice(row)));
            }
        }
    }

    protected int calculateTicketPrice(int row)
    {
        return row <= 4 ? 10 : 8;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public List<Seat> getSeats()
    {
        return seats;
    }
}
