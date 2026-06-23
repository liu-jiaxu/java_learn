package a2_OOP;

public class OOP6_Customer {

    private String firstName;
    private String lastName;
    private OOP6_Account account;

    public OOP6_Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
    }

    public OOP6_Account getAccount() {
        return account;
    }

    public void setAccount(OOP6_Account account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
