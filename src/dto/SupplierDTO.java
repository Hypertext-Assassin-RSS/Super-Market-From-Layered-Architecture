package dto;

import java.io.Serializable;

public class SupplierDTO implements Serializable {
    private String id;
    private String name;
    private String company;

    public SupplierDTO() {
    }

    public SupplierDTO(String id, String name, String company) {
        this.setId(id);
        this.setName(name);
        this.setCompany(company);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
