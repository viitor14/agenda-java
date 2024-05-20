module br.project.agenda {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.project.agenda to javafx.fxml;
    exports br.project.agenda;
    exports br.project.agenda.controllers;
    opens br.project.agenda.controllers to javafx.fxml;
}