package com.example.ofertev.controller;

import com.example.ofertev.HelloApplication;
import com.example.ofertev.domain.Client;
import com.example.ofertev.domain.Hotel;
import com.example.ofertev.domain.Location;
import com.example.ofertev.domain.SpecialOffer;
import com.example.ofertev.dtos.SpecialOfferDTO;
import com.example.ofertev.service.HotelService;
import com.example.ofertev.service.LocationService;
import com.example.ofertev.service.ReservationService;
import com.example.ofertev.service.SpecialOfferService;
import com.example.ofertev.utils.Observable;
import com.example.ofertev.utils.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController implements Initializable {
    Client client;
    HotelService hotelService;
    LocationService locationService;
    SpecialOfferService specialOfferService;
    ReservationService reservationService;
    @FXML
    TableView<Hotel> hotelTable;
    @FXML
    ComboBox<Location> locationCombo;
    @FXML
    TableView<SpecialOfferDTO> offersTable;
    @FXML
    private TableColumn<Hotel,String> hotelName;
    @FXML
    public TableColumn<Hotel,Integer> noRooms;
    @FXML
    public TableColumn<Hotel,Double> price;
    @FXML
    public TableColumn<Hotel, Types> type;
    @FXML
    public TableColumn<SpecialOfferDTO, String > hotelForOffer;
    @FXML
    public TableColumn<SpecialOfferDTO, String> locationOffer;
    @FXML
    public TableColumn<SpecialOfferDTO, Date> startdate;
    @FXML
    public TableColumn<SpecialOfferDTO, Date> enddate;

    ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList();
    ObservableList<SpecialOfferDTO> specialOfferObservableList = FXCollections.observableArrayList();
    ObservableList<Location> locationObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelTable.setItems(hotelObservableList);
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        noRooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        price.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        offersTable.setItems(specialOfferObservableList);
        hotelForOffer.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        locationOffer.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        locationCombo.setItems(locationObservableList);

    }
    private void initModel()
    {
        specialOfferObservableList.setAll(StreamSupport.stream(specialOfferService.findByFidelity(this.client.getFidelityGrade()).spliterator(),false).collect(Collectors.toList()));
        locationCombo.getItems().addAll(StreamSupport.stream(locationService.findAll().spliterator(),false).collect(Collectors.toList()));

    }
    public void setConfig(Client client,HotelService hotelService,LocationService locationService, SpecialOfferService specialOfferService, ReservationService reservationService)
    {
        this.client = client;
        this.hotelService = hotelService;
        this.locationService = locationService;
        this.specialOfferService = specialOfferService;
        this.reservationService = reservationService;
        initModel();
        hotelTable.setOnMouseClicked(x->{
            if(!hotelTable.getSelectionModel().getSelectedItems().isEmpty())
            {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/ReservationView.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load());
                    ReservationController reservationController = fxmlLoader.getController();
                    reservationController.setConfig(client.getClientId(), hotelTable.getSelectionModel().getSelectedItem().getHotelID(),specialOfferService, reservationService);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
    public void updatehotelTable()
    {
        if(locationCombo.getSelectionModel().getSelectedItem() != null) {
            Double id = locationCombo.getSelectionModel().getSelectedItem().getLocationId();
            hotelObservableList.setAll(StreamSupport.stream(hotelService.findByLocation(id).spliterator(), false).collect(Collectors.toList()));
        }
    }
}
