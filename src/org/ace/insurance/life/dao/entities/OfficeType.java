package org.ace.insurance.life.dao.entities;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.common.occupation.Occupation;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Hein Htet Aung
 * @created at 04/03/2022
 **/
@Entity
@Table(name = TableName.OFFICETYPE)
@NamedQueries(
        value = {
                @NamedQuery(name = "OfficeType.findAll", query = "SELECT o FROM OfficeType o ORDER BY o.name ASC")
        }
)
public class OfficeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DESCRIPTION")
    private String description;

    @Version
    @Column(name = "VERSION")
    private int version;

    public OfficeType(){}
    
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

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        //if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        //OfficeType that = (OfficeType) o;
        //return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficeType other = (OfficeType) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version != other.version)
			return false;
		return true;
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + version;
		return result;
    }

    
}
