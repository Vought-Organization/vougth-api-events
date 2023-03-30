package vougth.api.request;

public class NewBuyRequestDto {
    private int idUser;
    private int idTicket;
    private boolean approved;

    public int getIdUser() {
        return idUser;
    }
    public int getIdTicket() {
        return idTicket;
    }
    public boolean isApproved() {
        return approved;
    }
}
