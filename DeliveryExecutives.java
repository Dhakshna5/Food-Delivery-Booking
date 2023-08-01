
public class DeliveryExecutives
{
    private String name , prevDeliDesti , prevDeliTime;
    private int DCearned , allowance , orders_completed;

    public DeliveryExecutives(String name )
    {
        this.name = name;
        this.prevDeliDesti = null;
        this.prevDeliTime = null;
        this.DCearned = 0;
        this.allowance = 0;
        this.orders_completed = 0;
    }

    public String getName() {
        return name;
    }

    public String getPrevDeliDesti() {
        return prevDeliDesti;
    }
    public void setPrevDeliDesti(String prevDeliDesti) {
        this.prevDeliDesti = prevDeliDesti;
    }
    public String getPrevDeliTime() {
        return prevDeliTime;
    }
    public void setPrevDeliTime(String prevDeliTime) {
        this.prevDeliTime = prevDeliTime;
    }
    public int getDCearned() {
        return DCearned;
    }
    public void setDCearned(int DCearned) {
        this.DCearned = DCearned;
    }
    public int getAllowance() {
        return allowance;
    }
    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }
    public int getOrders_completed() {
        return orders_completed;
    }
    public void setOrders_completed(int orders_completed) {
        this.orders_completed = orders_completed;
    }
}
