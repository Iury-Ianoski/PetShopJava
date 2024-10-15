import java.io.Serial;
import java.io.Serializable;

public class Services implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String serviceName;
    private double price;

    public Services(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    @Override
    public String toString() {
        return  serviceName + " ; " + price + "\n";
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




}
