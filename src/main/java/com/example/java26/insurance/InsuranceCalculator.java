package com.example.java26.insurance;

import com.example.java26.Methods;

public class InsuranceCalculator {
    static void main() {

        String model = IO.readln("Bilmodell: ");
        model = model.toLowerCase();
        int annualMileage = Integer.parseInt(IO.readln("Årlig körsträcka i mil: "));
        int accidents = Integer.parseInt(IO.readln("Antal olyckor: "));
        int yearsOfDrivingExperience;
        yearsOfDrivingExperience = Integer.parseInt(IO.readln("Antal år som förare: "));
        if (annualMileage < 0 || accidents < 0 || yearsOfDrivingExperience < 0) {
            IO.println("Ogiltig input. Alla värden måste vara större eller lika med 0");
            Methods.printShutDownMessage();
            return;
        }
        int basePrice = getBasePrice(model);

        double mileageAdjustment = getMileageAdjustment(annualMileage, basePrice);

        double accidentAdjustment = getAccidentAdjustment(basePrice, accidents);

        double experienceAdjustment = getExperienceAdjustment(yearsOfDrivingExperience, basePrice);

        double finalPrice = basePrice + mileageAdjustment + accidentAdjustment + experienceAdjustment;
        IO.println("Ditt pris: " + finalPrice);
    }

    private static int getBasePrice(String model) {
        return switch (model) {
            case "tesla", "vw" -> 3500;
            case "volvo" -> 4000;
            case "kia" -> 3000;
            case "porsche" -> 5000;
            default -> 0;
        };
    }

    private static double getExperienceAdjustment(int yearsOfDrivingExperience, int basePrice) {
        double experienceAdjustment = 0;
        if (yearsOfDrivingExperience < 3)
            experienceAdjustment = basePrice * 0.2;
        else if (yearsOfDrivingExperience >= 10)
            experienceAdjustment = basePrice * -0.1;
        return experienceAdjustment;
    }

    private static double getAccidentAdjustment(int basePrice, int accidents) {
        double accidentAdjustment = basePrice * -0.1;
        if (accidents == 1) {
            accidentAdjustment = basePrice * 0.1;
        } else if (accidents > 1)
            accidentAdjustment = basePrice * 0.25;
        return accidentAdjustment;
    }

    private static double getMileageAdjustment(int annualMileage, int basePrice){
        if (annualMileage < 1000)
            return basePrice * -0.05;
        else if (annualMileage > 2000)
            return basePrice * 0.1;
        return 0.0;
    }

}
