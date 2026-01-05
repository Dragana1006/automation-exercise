package utilities;


public class PersonalInformation {

    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String address2;
    private String state;
    private String city;
    private String zipcode;
    private String mobileNumber;

    public PersonalInformation(String firstName, String lastName, String company, String address, String address2, String state, String city, String zipCode, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.address2 = address2;
        this.state = state;
        this.city = city;
        this.zipcode = zipCode;
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }



}
