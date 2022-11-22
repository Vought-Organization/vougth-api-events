package vougth.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class NewBuy {

    private int idEvent;

    private int idTicket;

    private boolean approved;

    public int getIdEvent() {
        return idEvent;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public boolean isApproved() {
        return approved;
    }
}
