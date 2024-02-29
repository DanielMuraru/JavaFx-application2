package com.example.ofertev.controller;

import com.example.ofertev.domain.Reservation;
import com.example.ofertev.domain.SpecialOffer;
import com.example.ofertev.dtos.SpecialOfferDTO;
import com.example.ofertev.service.ReservationService;
import com.example.ofertev.service.SpecialOfferService;
import com.example.ofertev.utils.MessageAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReservationController implements Initializable {

    Long clientid;
    Double hotelid;
    SpecialOfferService specialOfferService;
    ReservationService reservationService;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public DatePicker endDatePicker;
    @FXML
    public TableView<SpecialOfferDTO> offersTable;
    @FXML
    public TableColumn<SpecialOfferDTO,String> hotelName;
    @FXML
    public TableColumn<SpecialOfferDTO,String> locationID;
    @FXML
    public TableColumn<SpecialOfferDTO, Date> startdate;
    @FXML
    public TableColumn<SpecialOfferDTO, Date> enddate;
    private ObservableList<SpecialOfferDTO> specialOfferDTOModel = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        offersTable.setItems(specialOfferDTOModel);
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        locationID.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

    }
    private void initModel()
    {
        if(!(startDatePicker.getValue()==null || endDatePicker.getValue()==null)) {
            specialOfferDTOModel.setAll(StreamSupport.stream(specialOfferService.findByPeriod(this.hotelid,startDatePicker.getValue().toString(), endDatePicker.getValue().toString()).spliterator(), false).collect(Collectors.toList()));
        }
    }
    public void setConfig(Long clientid,Double hotelid,SpecialOfferService specialOfferService, ReservationService reservationService)
    {
        this.clientid = clientid;
        this.hotelid = hotelid;
        this.specialOfferService = specialOfferService;
        this.reservationService = reservationService;
        startDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && endDatePicker.getValue() != null && newValue.isAfter(endDatePicker.getValue())) {
                MessageAlert.showMessage(null, Alert.AlertType.WARNING,"Warning","Start date cannot be after end date.");
                startDatePicker.setValue(oldValue); // Reset to previous value
            }
        });

        endDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && startDatePicker.getValue() != null && newValue.isBefore(startDatePicker.getValue())) {
                MessageAlert.showMessage(null, Alert.AlertType.WARNING,"Warning","End date cannot be before start date.");
                endDatePicker.setValue(oldValue); // Reset to previous value
            }
        });

        initModel();
    }

    public void updateOfferTable() {
        initModel();
    }
    public void onReservationButton()
    {
        int noNights = endDatePicker.getValue().getDayOfYear()-startDatePicker.getValue().getDayOfYear()-1;
        reservationService.addReservation(1.0,clientid,hotelid,startDatePicker.getValue().atStartOfDay(),noNights);
    }
}
