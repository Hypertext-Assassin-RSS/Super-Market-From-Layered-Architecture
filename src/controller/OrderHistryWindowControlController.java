/*
 * Copyright (c) 2021. By Rajith Sanjaya,
 */

package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import view.tdm.OrderHistoryTM;

public class OrderHistryWindowControlController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<OrderHistoryTM> tblOrder;

    @FXML
    private JFXComboBox<?> cmbOrderid;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtCustomerid;






}
