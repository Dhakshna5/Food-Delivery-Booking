public class Main {
    public static void main(String[] args)
    {
        FoodDeliveryCompany company = new FoodDeliveryCompany(5);

        company.handleBooking(1, "A", "D", "9:00 AM");
        company.displayDeliveryExecutiveActivity();
        System.out.println("----------------------------------------");

        company.handleBooking(2, "B", "A", "10:00 AM");
        company.displayDeliveryExecutiveActivity();
        System.out.println("----------------------------------------");

        company.handleBooking(3, "B", "A", "10:10 AM");
        company.displayDeliveryExecutiveActivity();
        System.out.println("----------------------------------------");

        company.handleBooking(4, "D", "C", "10:35 AM");
        company.displayDeliveryExecutiveActivity();
        System.out.println("----------------------------------------");

        company.displayDeliveryHistory();
    }
}
