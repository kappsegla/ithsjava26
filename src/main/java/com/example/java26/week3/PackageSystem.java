package com.example.java26.week3;

public class PackageSystem {
    static void main() {
        Parcel[] parcels = new Parcel[10];
        int parcelCount = 0;

        while (parcelCount < 10) {
            //Registrera paket och deras dimensioner
            int width = Integer.parseInt(IO.readln("Please enter the width of the box:"));
            int height = Integer.parseInt(IO.readln("Please enter the height of the box:"));
            int length = Integer.parseInt(IO.readln("Please enter the length of the box:"));

            //Checka så paketen inte är större än 100x100x100 cm
            if (width > 100 || height > 100 || length > 100 || length <= 0 || width <= 0 || height <= 0) {
                IO.println("Invalid dimensions. Must be between 1 and 100.");
                continue;
            }
            Box box = new Box(width, height, length);

            int price = calculateShippingPrice(box);
            IO.println("Price is " + price);

            //Lagra kostnaden tillsammans med paketets information?? Får inte uppdatera Box..
            Parcel parcel = new Parcel(box, price);
            parcels[parcelCount++] = parcel;
        }
    }

    /**
     * Calculates the shipping price for a given box based on its dimensions.
     * The pricing rules are as follows:
     * - Boxes with all sides less than or equal to 20 cm cost 100 kr.
     * - Boxes with any side greater than 20 cm cost 200 kr.
     * - Bulky boxes, where the ratio between the largest and smallest side exceeds 1:2,
     *   incur an additional cost of 50 kr.
     *
     * @param box the box for which the shipping price is to be calculated, including its dimensions (width, height, length)
     * @return the total shipping price in kr
     */
    public static int calculateShippingPrice(Box box) {
        int price = 0;
        if (box.width() <= 20 && box.height() <= 20 && box.length() <= 20) {
            price = 100;
        } else
            price = 200;
        int maxSide = Math.max(Math.max(box.width(), box.height()), box.length());
        int minSide = Math.min(Math.min(box.width(), box.height()), box.length());
        if (maxSide > 2 * minSide) {
            price += 50;
        }
        return price;
    }
}
