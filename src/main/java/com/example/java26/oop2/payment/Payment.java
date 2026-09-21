package com.example.java26.oop2.payment;

public class Payment {
    protected double amount;   //Protected allows access from subclasses

    public Payment(double amount) {
        this.amount = amount;
    }

    public void process() {
        IO.println("Payment processing...");
    }

    public double getAmount() {
        return amount;
    }

    static void main() {
        CardNumber cardNumber = new CardNumber("2223000048400011");
        Payment payment = new CardPayment(500, cardNumber);
        payment.process();
    }
}

class CardPayment extends Payment {
    private String cardNumber;

    public CardPayment(double amount, CardNumber cardNumber) {
        super(amount);
        this.cardNumber = cardNumber.cardNumber();
    }

    @Override
    public void process() {
        super.process();
        IO.println("Card payment processing... with card number " + this.cardNumber + " and amount " + amount);
    }
}

class SwishPayment extends Payment {
    private String phoneNumber;

    public SwishPayment(double amount, String phoneNumber) {
        super(amount);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process() {
        super.process();
        IO.println("Swish payment processing... with phone number " + this.phoneNumber + " and amount " + amount);
    }
}

record CardNumber(String cardNumber) {
    public CardNumber {
        if (cardNumber == null || cardNumber.isEmpty() || !isValid(toIntArray(cardNumber))) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid(int[] cardNumber) {
        int sum = 0;
        for (int i = 0; i < cardNumber.length; i++) {
            var digit = cardNumber[i];
            var offsetFromEnd = cardNumber.length - i;
            if (offsetFromEnd % 2 == 1)
                sum = sum + digit;
            else if (digit < 5)
                sum = sum + 2 * digit;
            else
                sum = sum + 2 * digit - 9;
        }
        return (sum % 10) == 0;
    }

    private int[] toIntArray(String s) {
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = Character.getNumericValue(s.charAt(i));
        }
        return result;
    }

}
