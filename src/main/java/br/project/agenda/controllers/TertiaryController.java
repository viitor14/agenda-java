package br.project.agenda.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.project.agenda.App;
import br.project.agenda.classes.CreateElements;
import br.project.agenda.interfaces.Ler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;

public class TertiaryController extends CreateElements implements Ler, Initializable {
    public TertiaryController() {
        super("", "", "", "","");
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane idGridPane;

    @FXML
    private MenuBar idMenuBar;

    @FXML
    private Menu idMenuOption;

    @FXML
    private VBox idVboxMain;

    @FXML
    private VBox idVbox;

    @FXML
    private HBox idHbox;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSobrenome;

    @FXML
    private Label labelTelefone;

    @FXML
    private Label labelEmail;

    @FXML
    private Button tertiaryButton;



    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {  //Evento de click do botão para voltar para tela inicial
        App.setRoot("primary");
    }

    @FXML
    void switchToQuartiary(ActionEvent event) throws IOException {  //Envento de click do botão para abrir uma nova janela para editar o contato
        App.openNewWindow("quartiary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {    //Controlar que é iniciado assim que a tela abrir
        String imageUrl = getClass().getResource("/img/background.jpg").toExternalForm();
        String fx = String.format("-fx-background-image: url('%s'); -fx-background-size: cover;", imageUrl);
        idVboxMain.setStyle(fx);
        LerArquivos(0);                 //Assim que a tela for aberta a função é executada
    }

    @Override
    public void LerArquivos(int numberContato) {  //Função resposavel por ler os arquivos
        String nomeArquivo = "dados.txt";    //Nome do arquivo que vai ser lido

        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<String> sobrenomes = new ArrayList<>();
        ArrayList<String> telefones = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> indexContato = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {  //Iniciando o Buffere para poder dados do arquivos
            String linha;
            int numContato =0;
            while ((linha = reader.readLine()) != null) {  //Enquanto a linha dentro do arquivo for diferente de nulo ele vai ficar no loop
                String[] partes = linha.split(" "); //Cada caractere com espaço vazio dentro da linha que ele leu, vai dividir em partes e salvar dentro array partes
                nomes.add(partes[0]);                      //Cade index do array vai ser salvado em outro arraylist respectivamente
                sobrenomes.add(partes[1]);
                telefones.add(partes[2]);
                emails.add(partes[3]);
                indexContato.add(String.valueOf(numContato));
                numContato++;
            }

            int rows = 0;
            int column = -1;

            for (int i = 0; i < nomes.size(); i++) {
                CreateElements createElements = new CreateElements(nomes.get(i), sobrenomes.get(i), telefones.get(i),emails.get(i), indexContato.get(i)); //Mandando os dados para classe CreateElements para poder add valores na tela
                if (i % 3 == 0) {                       //Se a condição for verdadeira vai ser criado uma nova coluna para adicionar os contatos, (A CADA 3 CONTATOS UMA NOVA COLUNA VAI SER CRIADA)
                    column++;
                    rows =0;
                    idGridPane.add(createElements.createVBox(), column, rows);  //Vai add ao GridPane um novo contato que vai vim da função createVbox da classe createElements
                    continue;       //Voltar para o loop
                }
                rows++;
                idGridPane.add(createElements.createVBox(), column, rows);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }
}