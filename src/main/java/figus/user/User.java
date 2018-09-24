package figus.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import figus.client.Client;
import figus.project.Project;

@Entity
@Table(name="`User`")
public class User implements Serializable{

   
	private static final long serialVersionUID = 7378726688506782812L;
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String paa;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID")
    private Project actualProject;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
    private Client owner;
    
    
    public User() {}

    public User(String name,String email) {
        this.name = name;
        this.name = email;
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
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                   ", email='" + email + '\'' +
                    ", paa='" + paa + '\'' +
                '}';
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaa() {
		return paa;
	}

	public void setPaa(String paa) {
		this.paa = paa;
	}

	public Project getActualProject() {
		return actualProject;
	}

	public void setActualProject(Project actualProject) {
		this.actualProject = actualProject;
	}
	
	
}
