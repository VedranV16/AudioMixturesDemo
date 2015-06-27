package thymeleafexamples.stsm.business.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vvujcic on 23/06/2015.
 */
public class User implements Serializable {

    private String userName;
    private String password;
    private Long id;
    private List<Location> locations;

    public User() {
        locations = new ArrayList<Location>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        return password.equals(user.password);

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "User [" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", locations=" + locations +
                ']';
    }

    public void add(Location newLocation) {
        this.locations.add(newLocation);
    }
}
