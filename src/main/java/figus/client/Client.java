package figus.client;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import figus.item.Item;
import figus.project.Project;
import figus.user.User;

@Entity
public class Client implements Serializable{

	
	private static final long serialVersionUID = 3212438988685078453L;
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
                    ", projects='" + projects + '\'' +
                '}';
    }
    
    @OneToMany
    private List<Item> items;
    
    @OneToMany
    private List<User> users;

    @OneToMany(mappedBy="client")
    private List<Project> projects;

	public List<Project> getProjects() {
		// TODO Auto-generated method stub
    	return projects;
	}
}
