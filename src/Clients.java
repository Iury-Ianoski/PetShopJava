import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Clients implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String phoneNumber;
    private double balance;
    private ArrayList<Pets> pets;

    public Clients(String name, String address, String phoneNumber, double balance, ArrayList<Pets> pets) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\n" +
                "Endereço: " + address + "\n" +
                "Telefone: " + phoneNumber + "\n" +
                "Saldo: " + balance + "\n" +
                "\nPets: \n" + pets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Pets> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pets> pets) {
        this.pets = pets;
    }
}

