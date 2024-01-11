package common.dto;

import java.io.Serializable;

public class CustomerDTO extends PersonDTO implements Serializable {

    private int bankDepotId;

    public CustomerDTO(int customerNr, String name, String givenName, String address, int bankDepotId) {
        this.svnr = customerNr;
        this.name = name;
        this.givenname = givenName;
        this.address = address;
        this.bankDepotId = bankDepotId;
    }

    public CustomerDTO(int customerNr, String name, String givenName, String address, int bankDepotId, String username, String password) {
        this.svnr = customerNr;
        this.name = name;
        this.givenname = givenName;
        this.address = address;
        this.bankDepotId = bankDepotId;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "bankDepotId=" + bankDepotId +
                ", svnr=" + svnr +
                ", name='" + name + '\'' +
                ", givenname='" + givenname + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
