package figus.client;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import figus.item.Item;
import figus.user.User;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Client() {}

    public Client(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    
    @OneToMany(mappedBy = "owner")
    private List<Item> items;
    
    @OneToMany(mappedBy = "owner")
    private List<User> users;
}
