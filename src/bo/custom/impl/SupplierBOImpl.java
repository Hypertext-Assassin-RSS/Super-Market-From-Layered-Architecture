package bo.custom.impl;

import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.custom.SupplierDAO;
import dto.SupplierDTO;
import entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPPLIER);


    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSuppliers = new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier supplier : all) {
            allSuppliers.add(new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getAddress()));
        }
        return allSuppliers;
    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(dto.getId(), dto.getName(), dto.getCompany()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName(), dto.getCompany()));
    }

    @Override
    public boolean ifSupplierExist(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.ifSupplierExist(id);
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }
}
