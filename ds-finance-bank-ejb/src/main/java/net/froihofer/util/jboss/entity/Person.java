package net.froihofer.util.jboss.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STORED_PERSON")
public class Person implements Serializable{
    @Id
    private int svnr;
    @Column(name="name")
    private String name;

    @Column(name="givenname")
    private String givenname;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public Person() {}

    public Person(int svnr, String name, String givenname, String username, String password) {
        this.svnr = svnr;
        this.name = name;
        this.givenname = givenname;
        this.username = username;
        this.password = password;
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
