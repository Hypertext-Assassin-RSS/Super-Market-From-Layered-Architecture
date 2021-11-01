package bo.custom;

import bo.SuperBO;
import dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    boolean addSupplier(SupplierDTO supplierDTO ) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO supplierDTO ) throws SQLException, ClassNotFoundException;

    boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
