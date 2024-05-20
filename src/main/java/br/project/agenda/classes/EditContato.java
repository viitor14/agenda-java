package br.project.agenda.classes;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditContato {
    String nome;
    String sobrenome;
    String telefone;
    String email;

    public EditContato(String nome, String sobrenome, String telefone, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VBox insertVbox() {
        VBox vBox = new VBox();

        vBox.getChildren().add(createHBox());

        return vBox;
    }

    @FXML
    public ArrayList<Label> createLabel() {
        Label labelNome = new Label("Nome: ");
        Label labelSobrenome = new Label("Sobrenome: ");
        Label labelTelefone = new Label("Telefone: ");
        Label labelEmail = new Label("Email: ");
        
        labelSobrenome.setStyle("-fx-padding: 0 0 0 10;");
        labelTelefone.setStyle("-fx-padding: 0 0 0 10;");
        labelEmail.setStyle("-fx-padding: 0 0 0 10;");

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelNome);
        labels.add(labelSobrenome);
        labels.add(labelEmail);
        labels.add(labelTelefone);
        return labels;
    }

    @FXML
    public ArrayList<TextField> createTextField() {
        ArrayList<TextField> TextFields = new ArrayList<>();
        TextField textFieldNome = new TextField();
        TextField textFieldSobrenome = new TextField();
        TextField textFieldTelefone = new TextField();
        TextField textFieldEmail = new TextField();

        textFieldNome.setId("inputNome");
        textFieldSobrenome.setId("inputSobrenome");
        textFieldTelefone.setId("inputTelefone");
        textFieldEmail.setId("inputEmail");

        TextFields.add(textFieldNome);
        TextFields.add(textFieldSobrenome);
        TextFields.add(textFieldEmail);
        TextFields.add(textFieldTelefone);
        return TextFields;
    }

    @FXML
    public HBox createHBox() {
        HBox hbox = new HBox();
        String styles = ("-fx-background-color:  #f5fffb;" +
                " -fx-background-radius:10;" +
                " -fx-border-radius: 10;" +
                " -fx-border-color:  #d23;" +
                " -fx-padding: 10px;" +
                " -fx-opacity: 1;");
        hbox.setId("idhboxDados");
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle(styles);
        
        for (int i = 0; i < 4; i++) {
            hbox.getChildren().addAll(createLabel().get(i));
            hbox.getChildren().addAll(createTextField().get(i));
        }
        return hbox;
    }

    @FXML
    public static Label labelError(){
        Label labelError = new Label("Preencha todos os valores");
        String styles = ("-fx-text-fill: red;");
        labelError.setId("idLabelError");
        labelError.setStyle(styles);
        return labelError;
    }
}
