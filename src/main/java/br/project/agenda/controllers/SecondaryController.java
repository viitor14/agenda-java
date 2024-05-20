package br.project.agenda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.project.agenda.App;
import br.project.agenda.classes.Pessoa;
import br.project.agenda.interfaces.Salvar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class SecondaryController extends Pessoa implements Salvar, Initializable {   //Herdando a classe abastract Pessoa e implementando a interface Salvar

    @FXML
    private Button buttonAddContato;

    @FXML
    private VBox idVboxMain;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputSobrenome;

    @FXML
    private TextField inputTelefone;

    @FXML
    private Button secondaryButton;

    @FXML
    private Label idLabelError;

    public SecondaryController() {
        super("", "", "", ""); // Chama o construtor da classe Pessoa com valores vazios
    }

    @FXML
    void addContato(ActionEvent event) {
        exibirDetalhes();
    }

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException { //Evento do botão para voltar para tela principal
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {       //Controlador que ao abrir a tela ele pode fazer algo
        String imageUrl = getClass().getResource("/img/background.jpg").toExternalForm();
        String fx = String.format("-fx-background-image: url('%s'); -fx-background-size: cover;", imageUrl);
        idVboxMain.setStyle(fx);
    }

    @Override
    public void exibirDetalhes() {    //Função onde vai pegar os valores dos inputs e mandar para função SalvarArquivos em um array
        String pessoa = (inputNome.getText() + " " + inputSobrenome.getText() + " " + inputTelefone.getText() + " " + inputEmail.getText()+ " ");
        if(verificarInputs()){
            SalvarArquivos(pessoa);
        }
    }

    @Override
    public void SalvarArquivos(String pessoa){  //Função para salvar os arquivos e recebe um array com os dados fornecidos

        try {
            FileWriter fileWriter = new FileWriter("dados.txt",true);       //Instacia da classe FileWriter que vai entrar no arquivo e incrementar conteudo, caso mande false ele vai substituir
            BufferedWriter writer = new BufferedWriter(fileWriter);                         //Instacia da classe BufferedWriter que vai ler os dados dentro do arquvio
            writer.write(pessoa);                                                           //Enviando a os dados em string para salvar dentro do arquivo
            writer.newLine();
            writer.close();
            System.out.println("Os dados foram salvos no arquivo com sucesso!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao salvar os dados no arquivo.");
            e.printStackTrace();
        }
    }

    private boolean isNodePresentInVbox(VBox vbox, String nodeId) {    //Função para verificar se existe id especifico
        return vbox.getChildren().stream().anyMatch(node -> nodeId.equals(node.getId()));
    }

    public boolean verificarInputs(){
        if(inputNome.getText().isEmpty() || inputSobrenome.getText().isEmpty() || inputEmail.getText().isEmpty() || inputTelefone.getText().isEmpty()){     //Verifica se os inputs estão vazios, caso estajam vazio ele retorna e não salva

            if(!isNodePresentInVbox(idVboxMain, "idLabelError")){      //Se a mensagem de erro não existe, entra na condição e envia a mensagem na tela
                idVboxMain.getChildren().addAll(labelError());                 //Dentro de idVboxMain esta sendo add a função labelError() que envia mensagem na tela
                idLabelError = (Label) labelError().lookup("#idLabelError");    //Como o label esta sendo criado dinamicamente eu preciso fazer isso aqui para adicionar o id "idLabelError" o label
            }
            return false;
        }
        return true;
    }

    public static Label labelError(){
        Label labelError = new Label("Preencha todos os valores");
        String styles = ("-fx-text-fill: red;");
        labelError.setId("idLabelError");
        labelError.setStyle(styles);
        return labelError;
    }
}