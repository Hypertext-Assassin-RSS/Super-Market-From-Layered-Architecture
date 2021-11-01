/*
 * Copyright (c) 2021. By Rajith Sanjaya,
 */

package controller;

import bo.BoFactory;
import bo.custom.SupplierBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.SupplierDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tdm.SupplierTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SupplierWindowController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnAddNewSupplier;

    @FXML
    private JFXTextField txtSupplierID;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupplierCompany;

    private final SupplierBO supplierBO = (SupplierBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.SUPPPLIER);


    public void initialize() throws ClassNotFoundException {
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        initUI();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);
            if (newValue != null) {
                txtSupplierID.setText(newValue.getId());
                txtSupplierName.setText(newValue.getName());
                txtSupplierCompany.setText(newValue.getAddress());
                txtSupplierID.setDisable(false);
                txtSupplierName.setDisable(false);
                txtSupplierCompany.setDisable(false);
            }
        });
        txtSupplierCompany.setOnAction(event -> btnSave.fire());
            loadAllSuppliers();

    }

    private void loadAllSuppliers() throws ClassNotFoundException {
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allSuppliers = supplierBO.getAllSuppliers();
            for (SupplierDTO supplier : allSuppliers) {
                tblSupplier.getItems().add(new SupplierTM(supplier.getId(), supplier.getName(), supplier.getCompany()));
            }
        } catch (SQLException e) {
        }


    }

    private void initUI() {
        txtSupplierID.clear();
        txtSupplierName.clear();
        txtSupplierCompany.clear();
        txtSupplierID.setDisable(true);
        txtSupplierName.setDisable(true);
        txtSupplierCompany.setDisable(true);
        txtSupplierID.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void goHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("../view/main-window.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) throws SQLException {
        txtSupplierID.setDisable(false);
        txtSupplierName.setDisable(false);
        txtSupplierCompany.setDisable(false);
        txtSupplierID.clear();
        txtSupplierID.setText(generateNewId());
        txtSupplierName.clear();
        txtSupplierCompany.clear();
        txtSupplierName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblSupplier.getSelectionModel().clearSelection();
    }


    public void btnSave_OnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtSupplierID.getText();
        String name = txtSupplierName.getText();
        String address = txtSupplierCompany.getText();
        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            tblSupplier.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, " Supplier From " +id +" is Exists ").show();
                }
                SupplierDTO supplierDTO = new SupplierDTO(id, name, address);
                supplierBO.addSupplier(supplierDTO);
                tblSupplier.getItems().add(new SupplierTM(id, name, address));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            try {
                if (!existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, "Can't Find Supplier From Code " + id).show();
                }
                SupplierDTO supplierDTO = new SupplierDTO(id, name, address);
                supplierBO.updateSupplier(supplierDTO);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            SupplierTM selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
            selectedSupplier.setName(name);
            selectedSupplier.setAddress(address);
            tblSupplier.refresh();
        }
        btnAddNewSupplier.fire();
    }


    boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierBO.ifSupplierExist(id);
    }


    public void btnDelete_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = tblSupplier.getSelectionModel().getSelectedItem().getId();

            if (!existSupplier(id)) {
                new Alert(Alert.AlertType.ERROR, "Can't Find Supplier From Code " + id).show();
            }
            supplierBO.deleteSupplier(id);
            tblSupplier.getItems().remove(tblSupplier.getSelectionModel().getSelectedItem());
            tblSupplier.getSelectionModel().clearSelection();
            initUI();

        }


    private String generateNewId() throws SQLException {
        try {
            return supplierBO.generateNewID();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblSupplier.getItems().isEmpty()) {
            return "S001";
        } else {
            String id = getLastSupplierID();
            int newSupplierId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S%03d", newSupplierId);
        }

    }

    private String getLastSupplierID() {
        List<SupplierTM> tempSupplierList = new ArrayList<>(tblSupplier.getItems());
        Collections.sort(tempSupplierList);
        return tempSupplierList.get(tempSupplierList.size() - 1).getId();
    }
}
