package view.tdm;

public class SupplierTM implements Comparable<SupplierTM> {
    private String id;
    private String name;
    private String address;

    public SupplierTM() {
    }

    public SupplierTM(String id, String name, String address) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compareTo(SupplierTM o) {
        return getId().compareTo(o.getId());
    }
}
