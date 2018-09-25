package figus.project;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import figus.client.Client;

@Entity
public class Project implements Serializable{

  
	private static final long serialVersionUID = 7270304581971822204L;
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    @JsonIgnore 
    private Client client;
    
    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Project() {}

    public Project(String name) {
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
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
