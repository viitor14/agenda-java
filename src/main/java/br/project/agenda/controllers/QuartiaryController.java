package br.project.agenda.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.project.agenda.classes.CreateElements;
import br.project.agenda.classes.EditContato;
import br.project.agenda.interfaces.Ler;
import br.project.agenda.interfaces.SaveEditContato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuartiaryController extends EditContato implements Initializable, Ler, SaveEditContato { 

    public QuartiaryController() {
        super("", "", "", "");
    }

    @FXML
    private Button idButtonSalvar;

    @FXML
    private GridPane idGridPane;

    @FXML
    private VBox idVboxQuartiary;

    @FXML
    private VBox idVboxMain;

    @FXML
    private HBox idHbox;

    @FXML
    private TextField inputContato;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputSobrenome;

    @FXML
    private TextField inputTelefone;
    
    @FXML
    private TextField inputEmail;

    @FXML
    private Label idLabelError;

    @FXML
    void switchToClose(ActionEvent event) {
        if(inputNome.getText().isEmpty() || inputSobrenome.getText().isEmpty() || inputEmail.getText().isEmpty() || inputTelefone.getText().isEmpty()){     //Verifica se os inputs estão vazios, caso estajam vazio ele retorna e não salva
           
            if(!isNodePresentInVbox(idVboxQuartiary, "idLabelError")){      //Se a mensagem de erro não existe, entra na condição e envia a mensagem na tela
            idVboxQuartiary.getChildren().addAll(labelError());                 //Dentro de idVboxQuartiary esta sendo add a função labelError() que envia mensagem na tela 
                idLabelError = (Label) labelError().lookup("#idLabelError");    //Como o label esta sendo criado dinamicamente eu preciso fazer isso aqui para adicionar o id "idLabelError" o label 
           }
            return;
        }
        
        String filePath = "dados.txt"; // caminho do arquivo
        
        int lineNumber =Integer.parseInt(inputContato.getText()); // Index da linha que vai ser substituida
        String newContent = inputNome.getText() + " " + inputSobrenome.getText() + " " + inputTelefone.getText() + " " + inputEmail.getText() + " "; //Nova linha que vai ser substituida

        try {
            List<String> lines = readFile(filePath); // Ler o arquivo e armazenar as linhas em uma lista
                
            if (lineNumber >= 0 && lineNumber < lines.size()) {  // Verificar se a linha existe e substituir o conteúdo
                lines.set(lineNumber, newContent);
            } else {
                return;
            }
            
            writeFile(filePath, lines);  // Escrever as linhas de volta no arquivo            
        }catch (IOException e) {
            System.out.println("Erro ao ler ou escrever o arquivo: " + e.getMessage());
        }    
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();  // Fecha a tela
    }

    @FXML
    void getValueInput(KeyEvent event) { //Evento quando soltar a tecla
        try {
            Stage stage = (Stage) idVboxMain.getScene().getWindow();        
            if (!inputContato.getText().matches("\\d+")) {    //Verifica se o valor do input é um numero
                inputContato.setText(null);                         // Caso não for for um numero, o input fica vazio e consequetemente vai cair no erro
            }
            
            while (idVboxQuartiary.getChildren().size()>2) {        //Verificação para permitir que deixe somente um contato na tela para fazer alteração
                idVboxQuartiary.getChildren().remove(2);        //Remove o 2 Elemento da tela que esta dentro do Vbox
                stage.sizeToScene();                                    //De acordo com os elementos que estão sendo add dentro do Vbox(IdVboxMain) o tamanho da tela é ajustado
                if(idVboxQuartiary.getChildren().size()<3)return;    
            }
            
            if(inputContato.getText() == null)return; //Caso o inputContato esteja fazio ele RETORNA para evitar error na linha abaixo
            
            int numberContato = Integer.parseInt(inputContato.getText()); //Só recebe INT
            LerArquivos(numberContato);             //Chama a função onde vai mostra o contato a qual corresponde o index fornecido no input
            
            stage.sizeToScene();
            
        } catch (Exception e) {
            System.out.println("cai no erro " + e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Controlador que ao abrir a tela ele pode fazer algo    
        String imageUrl = getClass().getResource("/img/background.jpg").toExternalForm();
        String fx = String.format("-fx-background-image: url('%s'); -fx-background-size: cover;", imageUrl);
        idVboxMain.setStyle(fx); 
    }      

    @Override
    public void LerArquivos(int numberContato) {     //Função para ler os contatos
        String nomeArquivo = "dados.txt";            

        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<String> sobrenomes = new ArrayList<>();
        ArrayList<String> telefones = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> indexContato = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {   //Condições que esta criando instancia para poder ler o arquivo e dados dentro desse arquivo
            String linha;
            int numContato = 0;
            while ((linha = reader.readLine()) != null) {   //Se a linha for diferente de nulo ele vai salvar aquela linha que leu na variavel linha
                String[] partes = linha.split(" "); //Sempre que encontra um espaço entre a String na variavel linha ele vai seperar-la e add no array partes
                nomes.add(partes[0]);                     //Cada parte vai ser adicionada respectivamente na ordem que leu  
                sobrenomes.add(partes[1]);
                telefones.add(partes[2]);
                emails.add(partes[3]);
                indexContato.add(String.valueOf(numContato));
                numContato++;
            }
            if(numberContato >= nomes.size() && idVboxQuartiary.getChildren().size()<3){   //Se eu retirar esse if, ele cai no erro pois tenta pegar um index inexistente
                System.out.println("Contato inexistente");  
                return;
            }

            EditContato editContato = new EditContato(nomes.get(numberContato), sobrenomes.get(numberContato),telefones.get(numberContato), emails.get(numberContato));  //Instacia da classe editContato para mandar o contato especifico 
            CreateElements createElements = new CreateElements(nomes.get(numberContato), sobrenomes.get(numberContato),telefones.get(numberContato), emails.get(numberContato), indexContato.get(numberContato));  
            idVboxQuartiary.getChildren().addAll(createElements.createVBox());      //Add no idVboxQuartiary o metodo createVbox() da classe createElements. Onde vai pegar somente um contato e criar uma box com o contato especifico

            VBox vbox = editContato.insertVbox();       //Criando um vbox e add nele o metodo insertVbox() da classe editContato. Onde a classe é responsavel por criar os input para alterar os dados
            inputNome = (TextField) vbox.lookup("#inputNome");  //setando no id inputNome o TextField da classe EditContato
            inputSobrenome = (TextField) vbox.lookup("#inputSobrenome");
            inputTelefone = (TextField) vbox.lookup("#inputTelefone");
            inputEmail = (TextField) vbox.lookup("#inputEmail");
            idVboxQuartiary.getChildren().add(vbox);    //Add ao idVboxQuartiary todo Vbox criado com todos inputs dentro dele com id setado
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private boolean isNodePresentInVbox(VBox vbox, String nodeId) {    //Função para verificar se existe id especifico
        return vbox.getChildren().stream().anyMatch(node -> nodeId.equals(node.getId()));
    }
}
