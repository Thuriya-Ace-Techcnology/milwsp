package org.ace.insurance.life.dao.entities;

import org.ace.insurance.common.TableName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Hein Htet Aung
 * @created at 02/02/2022
 **/
@Entity
@Table(name = TableName.OFFICE)
@NamedQueries(
        value = {
                @NamedQuery(name = "Office.findAll", query = "SELECT o FROM Office o ORDER BY o.name ASC"),
                @NamedQuery(name = "Office.findAllParent",query = "SELECT o from Office o where o.parentOffice is null order by o.name ASC ")
        }
)
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OFFICE_TYPE_ID",nullable = false,referencedColumnName = "ID")
    private OfficeType officeType;

    @Version
    @Column(name = "VERSION")
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID",referencedColumnName = "ID")
    private Office parentOffice;

    @OneToMany(mappedBy = "parentOffice",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Office> subOffices   = new HashSet<>();

    public Office(){}

    public Office(String name,String description){
        this.name = name;
        this.description = description;
    }

    public Office(String name,String description,Office parentOffice){
        this.name = name;
        this.description = description;
        this.parentOffice = parentOffice;
    }

    public void addSubOffice(Office sub) {
        if (this.subOffices == null)
            this.subOffices = new HashSet<>();
        this.subOffices.add(sub);
        sub.setParentOffice(this);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(id, office.id) &&
                Objects.equals(name, office.name) &&
                Objects.equals(description, office.description) &&
                Objects.equals(officeType, office.officeType) &&
                Objects.equals(parentOffice, office.parentOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, officeType, parentOffice);
    }

    @Override
    public String toString() {
        return "Department{" +
                "ID =" + id +
                ", Name ='" + name + '\'' +
                ", description='" + description ;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Office getParentOffice() {
        return parentOffice;
    }

    public void setParentOffice(Office parentOffice) {
        this.parentOffice = parentOffice;
    }

    public Set<Office> getSubOffices() {
        return subOffices;
    }

    public void setSubOffices(Set<Office> subOffices) {
        this.subOffices = subOffices;
    }

    public OfficeType getOfficeType() {
        return officeType;
    }

    public void setOfficeType(OfficeType officeType) {
        this.officeType = officeType;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
}
