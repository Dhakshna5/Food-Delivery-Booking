import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodDeliveryCompany
{

    private List<DeliveryExecutives> DeliveryExecutive;
    private List<String> tripHistory;

    public FoodDeliveryCompany(int nofexec) {
        DeliveryExecutive = new ArrayList<>();
        for (int i = 1; i <= nofexec; i++) {
            DeliveryExecutive.add(new DeliveryExecutives("DE" + i));
        }

        tripHistory = new ArrayList<>();
    }

    public void handleBooking(int customerId, String restaurant, String destination, String time) {

        assignDe(50, destination, time, restaurant, customerId);
    }

    private DeliveryExecutives assignDe(int deliveryCharge, String destination, String time, String restaurant, int cid) {
        DeliveryExecutives assignedExe = null;
        int minDeliCharge = Integer.MAX_VALUE, allow = 1;
        for (DeliveryExecutives executive : DeliveryExecutive) {
            if (executive.getDCearned() < minDeliCharge)
                minDeliCharge = executive.getDCearned();
        }
        for (DeliveryExecutives executive : DeliveryExecutive) {
            int currentCharge = executive.getDCearned();
            String prev_dest = executive.getPrevDeliDesti();
            String prev_time = executive.getPrevDeliTime();

            if ( (currentCharge < minDeliCharge) || ((prev_dest == null || destination.equals(prev_dest)) && (isWithin15Minutes(prev_time,time))))
            {
                assignedExe = executive;
                System.out.println("Booking ID "+cid+" is allotted to "+assignedExe.getName());
                assignedExe.setOrders_completed(assignedExe.getOrders_completed()+1);

                if ( (destination.equals(prev_dest)) && (isWithin15Minutes(prev_time,time)) )
                {
                    deliveryCharge = 5;
                    allow=0;

                    for (int i = 0 ; i<tripHistory.size() ; i++)
                    {
                        String[] spl = tripHistory.get(i).split(" - ");
                        if (spl[1].contains(String.valueOf(assignedExe.getName())))
                        {
                            int oldValue = Integer.parseInt(spl[4]);
                            int newValue = oldValue + 1;
                            spl[4] = String.valueOf(newValue);
                            newValue = assignedExe.getDCearned() + 5;
                            spl[7] = String.valueOf(newValue);
                            String mod = String.join(" - " , spl);
                            tripHistory.set(i , mod);
                            break;
                        }
                    }
                }
                else
                {
                    deliveryCharge = 50;
                    allow=1;
                    String pickup = calculateDeliveryTime(time,15);

                    int orderCount = assignedExe.getOrders_completed();
                    tripHistory.add("TRIP - " + assignedExe.getName() + " - " + restaurant + " - " + destination +
                            " - " + orderCount + " - " + pickup + " - " + calculateDeliveryTime( String.valueOf(pickup) ,30) + " - " + (assignedExe.getDCearned() + deliveryCharge));

                }
                break;
            }
        }
        if (assignedExe != null) {
            assignedExe.setPrevDeliDesti(destination);
            assignedExe.setPrevDeliTime(time);
            assignedExe.setAllowance(assignedExe.getAllowance()+10*allow );
            assignedExe.setDCearned(assignedExe.getDCearned() + deliveryCharge);
        }

        return assignedExe;
    }
    private String calculateDeliveryTime(String pickupTime , int i) {
        String[] parts = pickupTime.split(" ");
        String[] timeParts = parts[0].split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        String amPm = parts[1];

        minute += i;
        if (minute >= 60) {
            hour += 1;
            minute %= 60;
        }
        hour %= 12;

        String newTime = String.format("%02d:%02d %s", hour, minute, amPm);
        return newTime;
    }

    public void displayDeliveryHistory() {
        System.out.println("TRIP - EXECUTIVE - RESTAURANT - DESTINATION POINT - ORDERS COMPLETED - PICKUP TIME - DELIVERY TIME - DELIVERY CHARGE");
        for (String trip : tripHistory) {
            System.out.println(trip);
        }
    }

    public void displayDeliveryExecutiveActivity() {
        System.out.println("Executive - Allowance - Delivery Charges - Total");
        for (DeliveryExecutives executive : DeliveryExecutive) {
            int allowance = executive.getAllowance();
            int deliveryCharges = executive.getDCearned();
            int total = allowance + deliveryCharges;
            System.out.println(executive.getName() + " - " + allowance + " - " + deliveryCharges + " - " + total);
        }
    }


    public static boolean isWithin15Minutes(String lastTime, String currentTime) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        if (lastTime==null)
            lastTime = currentTime;
        if (currentTime==null)
            currentTime = lastTime;
        try {
            Date date1 = format.parse(lastTime);
            Date date2 = format.parse(currentTime);

            long differenceInMillis = Math.abs(date1.getTime() - date2.getTime());
            long differenceInMinutes = differenceInMillis / (60 * 1000);

            return differenceInMinutes < 15;
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the exception, e.g., invalid time format
        }
        return false;
    }
}
