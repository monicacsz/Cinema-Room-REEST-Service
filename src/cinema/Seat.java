package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

@Component
public class Seat
{
    private int row;
    private int column;
    private int price;
    private boolean isOccupied;

    public Seat()
    {

    }

    public Seat(int row, int column, int price)
    {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isOccupied = false;
    }

    // essential for proper serialization/deserialization
    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }


    public int getPrice()
    {
        return price;
    }

    @JsonIgnore
    public boolean isOccupied()
    {
        return isOccupied;
    }

    public void setOccupied(boolean occupied)
    {
        isOccupied = occupied;
    }
}
