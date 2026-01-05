package utilities;

public class PaymentData {

    private String nameOfCard;
    private String cardNumber;
    private String cvcNumber;
    private String expirationMonth;
    private  String expirationYear;

    public String getNameOfCard() {
        return nameOfCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvcNumber() {
        return cvcNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public PaymentData(String nameOfCard, String cardNumber, String cvcNumber, String expirationMonth, String expirationYear){
        this.nameOfCard = nameOfCard;
        this.cardNumber = cardNumber;
        this.cvcNumber = cvcNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }
}
