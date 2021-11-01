package dao.custom.impl;

import dao.CrudUtil;
import dao.SuperDAO;
import dao.custom.SupplierDAO;
import entity.Customer;
import entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean add(Supplier dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Supplier (id,name, address) VALUES (?,?,?)", dto.getId(), dto.getName(), dto.getAddress());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Supplier WHERE id=?", id);
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Supplier SET name=?, address=? WHERE id=?", dto.getName(), dto.getAddress(), dto.getId());
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Supplier WHERE id=?", id);
        rst.next();
        return new Supplier(id, rst.getString("name"), rst.getString("address"));
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSuppliers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Supplier");
        while (rst.next()) {
            allSuppliers.add(new Supplier(rst.getString("id"), rst.getString("name"), rst.getString("address")));
        }
        return allSuppliers;
    }

    @Override
    public boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT id FROM Supplier WHERE id=?", id).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT id FROM Supplier ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newSupplierId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S%03d", newSupplierId);
        } else {
            return "S001";
        }
    }
}
