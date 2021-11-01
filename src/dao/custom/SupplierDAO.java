package dao.custom;

import dao.CrudDAO;
import entity.Supplier;

import java.sql.SQLException;


public interface SupplierDAO extends CrudDAO<Supplier, String> {
    boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}