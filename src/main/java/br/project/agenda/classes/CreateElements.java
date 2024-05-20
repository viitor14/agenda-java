package br.project.agenda.classes;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CreateElements {

    String nome;
    String sobrenome;
    String telefone;
    String email;
    String numContato;

    public CreateElements(String nome, String sobrenome, String telefone, String email, String numContato) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.numContato = numContato;
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

    public String getNumContato() {
        return numContato;
    }

    public void setNumContato(String numContato) {
        this.numContato = numContato;
    }

    public VBox createVBox() {
        VBox vBox = new VBox();

        String styles = ("-fx-background-color:  rgba(246, 2335, 251, 1);" +
                " -fx-background-radius:14;" +
                " -fx-border-radius:10;" +
                " -fx-border-color:#606060;" +
                " -fx-border-width:2;" +
                " -fx-padding:10px;");
        vBox.setId("idVboxDados");
        vBox.setStyle(styles);
        vBox.getChildren().add(insertVbox());
        return vBox;
    }

    public VBox insertVbox() {
        VBox vBox = new VBox();
        ArrayList<Label> labels = new ArrayList<>();
        labels = createLabel();
        for (int i = 0; i < labels.size(); i++) {
            vBox.getChildren().add(labels.get(i));
        }
        return vBox;
    }

    public ArrayList<Label> createLabel() {
        Label labelContato = new Label("Contato: " + this.numContato);
        Label labelNome = new Label("Nome: " + this.nome);
        Label labelSobrenome = new Label("Sobrenome: " + this.sobrenome);
        Label labelTelefone = new Label("Telefone: " + this.telefone);
        Label labelEmail = new Label("Email: " + this.email);

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelContato);
        labels.add(labelNome);
        labels.add(labelSobrenome);
        labels.add(labelEmail);
        labels.add(labelTelefone);
        return labels;
    }

}