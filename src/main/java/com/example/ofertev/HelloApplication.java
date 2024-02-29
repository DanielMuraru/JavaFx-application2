package com.example.ofertev;

import com.example.ofertev.controller.UserController;
import com.example.ofertev.domain.Client;
import com.example.ofertev.repository.*;
import com.example.ofertev.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.LongConsumer;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ClientRepository clientRepository = new ClientRepository("postgres","Aluire1@2","jdbc:postgresql://localhost:5432/ofertev");
        HotelRepository hotelRepository = new HotelRepository("postgres","Aluire1@2","jdbc:postgresql://localhost:5432/ofertev");
        SpecialOfferRepository offerRepository = new SpecialOfferRepository("postgres","Aluire1@2","jdbc:postgresql://localhost:5432/ofertev");
        LocationRepository locationRepository = new LocationRepository("postgres","Aluire1@2","jdbc:postgresql://localhost:5432/ofertev");
        ReservationRepository reservationRepository = new ReservationRepository("postgres","Aluire1@2","jdbc:postgresql://localhost:5432/ofertev");

        ClientService clientService = new ClientService(clientRepository);
        HotelService hotelService = new HotelService(hotelRepository);
        LocationService locationService = new LocationService(locationRepository);
        SpecialOfferService specialOfferService = new SpecialOfferService(offerRepository,hotelRepository,locationRepository);
        ReservationService reservationService = new ReservationService(reservationRepository);
        Parameters args = getParameters();
            List<String> clients =args.getRaw();
            clients.forEach(x->{
                Client client = clientService.findOne(Long.parseLong(x.split(":")[1]));
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/UserView.fxml"));
                Stage newStage = new Stage();
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 560, 480);

                    UserController userController = fxmlLoader.getController();
                    userController.setConfig(client,hotelService,locationService,specialOfferService, reservationService);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                newStage.setTitle(client.getName());
                newStage.setScene(scene);
                newStage.show();}
            );


    }

    public static void main(String[] args) {
        launch(args);
    }
}