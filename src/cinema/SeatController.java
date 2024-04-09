package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController
{
    @Autowired
    private Room room;

    @GetMapping("/seats")
    public Room getRoomInfos()
    {
        return room;
    }
}
