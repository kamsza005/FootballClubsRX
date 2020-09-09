package footballclubs;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import footballclubs.api.apk.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Controller {
    private static String season;
    @FXML private TableView<Club> tableClubList;
    @FXML private TableColumn<Club, String> Klub;
    @FXML private TableColumn<Club, String> Kod;
    @FXML private TableColumn<Club, String> Kraj;

    @FXML private TableView<Round> tableMatchList;
    @FXML private TableColumn<Round, String> matchDay;
    @FXML private TableColumn<Round, String> dataMatch;
    @FXML private TableColumn<Round, String> gospodarzMatch;
    @FXML private TableColumn<Round, String> goscMatch;
    @FXML private TableColumn<Match, String> wynikMatch;


    @FXML private ComboBox comboBoxLeague;
    @FXML private ComboBox comboBoxSeason;
    @FXML private Label NameClub;

    private Club selectedClub;
    private String liga;
    private int index;
    private int petla=10;
    String team1,team2;
    Integer losowa;
    Integer losowa2;

    @FXML
    public void initialize() {
       comboBoxLeague.getItems().removeAll(comboBoxLeague.getItems());
       comboBoxLeague.getItems().addAll("en.1", "en.2", "es.1", "es.2", "de.1", "de.2", "it.1", "it.2");

        comboBoxSeason.getItems().removeAll(comboBoxSeason.getItems());
        comboBoxSeason.getItems().addAll("2019-20","2018-19","2017-18","2016-17","2015-16");

        ListaKlubow();
        ListaMeczy();
    }

    private void ListaKlubow() {

        JavaFxObservable.actionEventsOf(comboBoxSeason).map(keyEvent -> comboBoxLeague.getValue())
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    Platform.runLater(() -> {
                        tableClubList.setItems(FXCollections.observableList(getClubList((String) s)));
                    });
                });

        JavaFxObservable.actionEventsOf(comboBoxLeague).map(keyEvent -> comboBoxLeague.getValue())
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribe(s -> {
                    Platform.runLater(() -> {

                        tableClubList.setItems(FXCollections.observableList(getClubList((String) s)));
                        liga= (String) s;

                        if(liga == "de.1" ||liga == "de.2" ){
                            petla=9;
                        }else
                            petla=10;

                    });
                });

        JavaFxObservable.valuesOf(tableClubList.getSelectionModel().selectedItemProperty()).subscribe(g -> {
            Platform.runLater(() -> {
                selectedClub = g;
                NameClub.setText(String.valueOf(g));
                tableMatchList.setItems(FXCollections.observableList(getMatchList(String.valueOf(liga))));
                setupTablesRounds();

            });
        });

    }

    private void ListaMeczy() {
        Klub.setCellValueFactory(club -> {
            Club g = club.getValue();

            return Bindings.createStringBinding(() -> g.getName());

        });

        Kod.setCellValueFactory(club -> {
            Club g = club.getValue();

            return Bindings.createStringBinding(() -> g.getCode());
        });
        Kraj.setCellValueFactory(club -> {
            Club g = club.getValue();

            return Bindings.createStringBinding(() -> g.getCountry());
        });

    }

    private void setupTablesRounds() {

        matchDay.setCellValueFactory(round -> {
            Round r = round.getValue();
            return Bindings.createStringBinding(() -> r.getName());

        });

        dataMatch.setCellValueFactory(round-> {

            Round r1=round.getValue();

            for(int i=0;i<petla;i++){
                team1=r1.getMatches().get(i).getTeam1();
                team2=r1.getMatches().get(i).getTeam2();

                if(team1.equals(""+ selectedClub) || team2.equals(""+ selectedClub))
                    index=i;
            }

            return Bindings.createStringBinding(() -> r1.getMatches().get(index).getDatee());

        });

        gospodarzMatch.setCellValueFactory(round-> {
            Round r1=round.getValue();

            for(int i=0;i<petla;i++){
                team1=r1.getMatches().get(i).getTeam1();
                team2=r1.getMatches().get(i).getTeam2();
                if(team1.equals(""+ selectedClub) || team2.equals(""+ selectedClub))
                    index=i;
            }

            return Bindings.createStringBinding(() -> r1.getMatches().get(index).getTeam1());

        });

        goscMatch.setCellValueFactory(round-> {

            Round r1=round.getValue();




            for(int i=0;i<petla;i++){
                team1=r1.getMatches().get(i).getTeam1();
                team2=r1.getMatches().get(i).getTeam2();
                if(team1.equals(""+ selectedClub) || team2.equals(""+ selectedClub))
                    index=i;
            }
            return Bindings.createStringBinding(() -> r1.getMatches().get(index).getTeam2());

        });


        wynikMatch.setCellValueFactory(round-> {

            Match r1=round.getValue();


            Random liczba = new Random();
            losowa = liczba.nextInt(3);
            losowa2 = liczba.nextInt(3);


            return Bindings.createStringBinding(() -> String.valueOf(losowa+":"+losowa2));

        });




    }


    private List<Club> getClubList(String s) {
        List<Club> clubs = new ArrayList<>();


        try {
            clubs =  Arrays.asList(ClubList.withName(s).getClubs());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return clubs;
    }

    private List<Round> getMatchList(String s) {
        List<Round> round = new ArrayList<>();


        try {
            round =  Arrays.asList(RoundList.withName(s).getRounds());
        } catch (Exception e) {
            e.printStackTrace();
        }



        return round;
    }


    public void choosen(ActionEvent actionEvent) {
        String abc=comboBoxLeague.getValue().toString();
    }

    public void choosen2(ActionEvent actionEvent) {
        season=comboBoxSeason.getValue().toString();
    }

    public static String getting(){
        return season;
    }


}
