package common;

import java.io.Serializable;

public class PersonDTO implements Serializable {


    private int svnr;
    private String name;
    private String givenname;
    private String username;
    private String password;

    public PersonDTO() {
    }

    public PersonDTO(int svnr, String name, String givenname, String username, String password) {
        this.svnr = svnr;
        this.name = name;
        this.givenname = givenname;
        this.username = username;
        this.password = password;
    }

    public PersonDTO(int svnr) {
        this.svnr = svnr;
    }

    public int getSvnr() {
        return svnr;
    }

    public String getName() {
        return name;
    }

    public String getGivenname() {
        return givenname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
