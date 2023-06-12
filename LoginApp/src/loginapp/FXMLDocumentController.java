
package loginapp;

//Imports of everything needed for the controller to work
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
//import com.mysql.cj.xdevapi.PreparableStatement;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView.EditEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class FXMLDocumentController implements Initializable {

    // Making variables for the Buttons, Labels, TextFields, PasswordFields,
    // TableView, Tableview Columns and Database variables in order to connect for
    // the DatabaseConnection.java

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DatabaseConnection connectNow = new DatabaseConnection();

    @FXML
    private TableView<Workers> workerTableView;
    //@FXML
    //private TableColumn<Workers, Integer> nameId;
    @FXML
    private TableColumn<Workers, String> workerName;
    @FXML
    private TableColumn<Workers, String> workerPosition;
    @FXML
    private TableColumn<Workers, String> workerTask;
    @FXML
    private TableColumn<Workers, String> workerArea;
    @FXML
    Workers workers = null;
    @FXML
    ObservableList<Workers> workerList = FXCollections.observableArrayList(
    // new Workers(1, "Paa", "cle", "clean", "build")
    );

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField nameFid;
    @FXML
    private TextField positionFid;
    @FXML
    private TextField taskFid;
    @FXML
    private TextField areaFid;

    int index = -1;
    @FXML
    private TextArea workerText;
    
    @FXML
    private ChoiceBox choiceBoxId;
    private int[] ids= {1,2,3};
    
    //@FXML
    //private workerTextare worekerTextarea;

    // ------------------------End of variables needed for the program-----------

    // Cancel button is the button that Exits the program on the login page
    @FXML
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // loginButtonEvent is connected to the button in order to login to the page
    @FXML
    public void loginButtonEvent(ActionEvent event) throws IOException {
        // loginMessageLabel.setText("Try to login");

        if (usernameTextField.getText().isEmpty() == false && passwordPasswordField.getText().isEmpty() == false) {
            // loginMessageLabel.setText("Try to login");
            validateLogin(event);
        } else {
            loginMessageLabel.setText("Please enter user and pass");
            System.out.println("Please enter user and pass");
        }

    }

    // this is a login validation actionevent in order to test if the connection
    // works
    public void validateLogin(ActionEvent event) {

        // making he connection and creating a query in a String variable called
        // verifyLogin
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE name='" + usernameTextField.getText()
                + "' AND password='" + passwordPasswordField.getText() + "'";
        // String verifyLogin="SELECT count(1) FROM users WHERE name='"++"' AND
        // password=1";

        try {
            // Connection and insert SQL query
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                // checking if the count of the query is (1) in mysql and if it's true log in
                // the programm
                if (queryResult.getInt(1) == 1) {
                    // If logged in trasfer to Menu.fxml
                    System.out.println("Logged in");
                    Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    loginMessageLabel.setText("Wrong Username Or Password. Please Try Again!");
                    System.out.println("Not logged in");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeApp(ActionEvent event) {
    	Platform.exit();
    	System.out.println("Application Closed!");
    }

    // taskManager is an ActionEvent to transfer you to the taskManager.fxml
    public void taskManager(ActionEvent event) throws IOException {
        System.out.println("Task Manager");
        Parent root = FXMLLoader.load(getClass().getResource("taskManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // loadTable();
        // refreshTable();
    }

    // The backToMenu ActionEvent is connected to buttons that take you back to the
    // Menu.fxml of the programm
    public void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // The workerInfo ActionEvent is a button in the menu that takes you to the
    // workerInfo.fxml
    public void workerInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("workerInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    // The deleteTableTask deletes the contents of the table of the tableview and of
    // the Database
    public void deleteTableTask(ActionEvent event) {
        // Make connections and create a query variable to command the database
        System.out.println("Task Deleted");
        conn = connectNow.getConnection();
        String sql = "DELETE FROM workertable WHERE name=?";

        try {
            // make the connection and order it the delete whatever the TextField nameFid
            // text is
            pst = conn.prepareStatement(sql);
            pst.setString(1, nameFid.getText());
            pst.execute();
            refreshTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The addTableTask adds the contents of the table of the tableview and of the
    // Database
    public void addTableTask(ActionEvent event) throws IOException {
        // Make connections and create a query variable to command the database
        System.out.println("Task Added");
        conn = connectNow.getConnection();
        //System.out.println("2");
        String sql = "INSERT INTO workertable (nameId,name,position,task,area) VALUES(0,?,?,?,?)";
        //System.out.println("3");
        try {
            // make the connection and order it to add whatever the TextFields
            // nameFid,positionFid,taskFid,areaFid text is
            //System.out.println("4");
            pst = conn.prepareStatement(sql);
            //System.out.println("5");
            pst.setString(1, nameFid.getText());
            pst.setString(2, positionFid.getText());
            pst.setString(3, taskFid.getText());
            pst.setString(4, areaFid.getText());
            //System.out.println("6");
            pst.execute();
            //System.out.println("7");
            refreshTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The loadtable loads the database to a tableview in the programm (It can also
    // refresh the table)
    @FXML
    private void loadTable() {
        refreshTable();
        //nameId.setCellValueFactory(new PropertyValueFactory<Workers,Integer>("nameId"));
        workerName.setCellValueFactory(new PropertyValueFactory<Workers, String>("name"));
        workerPosition.setCellValueFactory(new PropertyValueFactory<Workers, String>("position"));
        workerTask.setCellValueFactory(new PropertyValueFactory<Workers, String>("task"));
        workerArea.setCellValueFactory(new PropertyValueFactory<Workers, String>("area"));
        workerTableView.setItems(workerList);
        System.out.println(workerTableView);
        System.out.println("Table Loaded");
    }

    // The refreshTable refreshes and checks the contents of the database through a
    // tableview in the programm
    public void refreshTable() {
        // make the connections and make a query to select all the contents of the
        // workertable table of the database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT * FROM workertable";

        try {
            // connect to the database
            workerList.clear();
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
            	
                // make the tableview workerList to show the database columns called
                // nameId,name,position,task,area
                workerList.add(new Workers(queryResult.getInt("nameId"),
                        queryResult.getString("name"),
                        queryResult.getString("position"),
                        queryResult.getString("task"),
                        queryResult.getString("area")));
                System.out.println(workerList);
                workerTableView.setItems(workerList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	 
    }

    // getSelected MouseEvent makes the contents of the tableview to be selected by
    // the click of your mouse
    @FXML
    void getSelected(MouseEvent event) {
        index = workerTableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        nameFid.setText(workerName.getCellData(index).toString());
        positionFid.setText(workerPosition.getCellData(index).toString());
        taskFid.setText(workerTask.getCellData(index).toString());
        areaFid.setText(workerArea.getCellData(index).toString());
    }

    // Edit makes the tableview workerList to edit the contents of the columns of
    // the database and the tableview except the name.
    public void Edit() {
        System.out.println("Task Edited");
        try {
            // Make the connection and make a query to be executed in the database to update
            // the contents of the columns from the Textfields of the programm
            //System.out.println("2");
            conn = connectNow.getConnection();
            //System.out.println("3");
            String value1 = nameFid.getText();
            String value2 = positionFid.getText();
            String value3 = taskFid.getText();
            String value4 = areaFid.getText();
            String sql = "UPDATE workertable SET name='" + value1 +
                    "',position='" + value2 + "',task='" + value3 +
                    "',area='" + value4 + "' WHERE name='" + value1 + "'";
            //System.out.println("4");
            pst = conn.prepareStatement(sql);
            pst.execute();
            //System.out.println("5");
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //---------------------Worker Info--------------------
    
    
    public void selectIds(ActionEvent event) {                  //This Shows at the TextArea the Info for each Worker
    
    	DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //String query = "SELECT * FROM users";

        try {
            	System.out.println(choiceBoxId.getValue());
            	
            	if(choiceBoxId.getValue().equals(1)) {
            		 try {
            			 String query = "SELECT * FROM `users` WHERE userid="+choiceBoxId.getValue()+";";
            			 Statement statement = connectDB.createStatement();
            	            ResultSet queryResult = statement.executeQuery(query);
            	            if (queryResult.next()) {
                                String name = queryResult.getString("name");
                                String lastname = queryResult.getString("lastname");
                                String position = queryResult.getString("position");
                                String age = queryResult.getString("age");
                                String userid = queryResult.getString("userid");
                                System.out.println(name+lastname+userid);
                                workerText.clear();
                                workerText.appendText("User Id:"+userid+"\n"+
                                		"Name:"+name+"\n"+
                                		"Lastname:"+lastname+"\n"+
                                		"Position:"+position+"\n"+
                                		"Age:"+age);
            	           //System.out.println(choiceBoxId);
            	            }
            	            } catch (Exception e) {
            	           e.printStackTrace();
            	           }
            	       }else if(choiceBoxId.getValue().equals(2)) {
            	    	   try {
                  			 String query = "SELECT * FROM `users` WHERE userid="+choiceBoxId.getValue()+";";
                  			 Statement statement = connectDB.createStatement();
                  	            ResultSet queryResult = statement.executeQuery(query);
                  	            if (queryResult.next()) {
                                      String name = queryResult.getString("name");
                                      String lastname = queryResult.getString("lastname");
                                      String position = queryResult.getString("position");
                                      String age = queryResult.getString("age");
                                      String userid = queryResult.getString("userid");
                                      System.out.println(name+lastname+userid);
                                      workerText.clear();
                                      workerText.appendText("User Id:"+userid+"\n"+
                                      		"Name:"+name+"\n"+
                                      		"Lastname:"+lastname+"\n"+
                                      		"Position:"+position+"\n"+
                                      		"Age:"+age);
                  	           //System.out.println(choiceBoxId);
                  	            }
                  	            } catch (Exception e) {
                  	           e.printStackTrace();
            	       }
            	    	   }else if(choiceBoxId.getValue().equals(3)) {
            	    	   try {
                    			 String query = "SELECT * FROM `users` WHERE userid="+choiceBoxId.getValue()+";";
                    			 Statement statement = connectDB.createStatement();
                    	            ResultSet queryResult = statement.executeQuery(query);
                    	            if (queryResult.next()) {
                                        String name = queryResult.getString("name");
                                        String lastname = queryResult.getString("lastname");
                                        String position = queryResult.getString("position");
                                        String age = queryResult.getString("age");
                                        String userid = queryResult.getString("userid");
                                        System.out.println(name+lastname+userid);
                                        workerText.clear();
                                        workerText.appendText("User Id:"+userid+"\n"+
                                        		"Name:"+name+"\n"+
                                        		"Lastname:"+lastname+"\n"+
                                        		"Position:"+position+"\n"+
                                        		"Age:"+age);
                    	           //System.out.println(choiceBoxId);
                    	            }
                    	            } catch (Exception e) {
                    	           e.printStackTrace();
              	       }
            	    	   }else if(choiceBoxId.getValue().equals(4)) {
            	    	   try {
                    			 String query = "SELECT * FROM `users` WHERE userid="+choiceBoxId.getValue()+";";
                    			 Statement statement = connectDB.createStatement();
                    	            ResultSet queryResult = statement.executeQuery(query);
                    	            if (queryResult.next()) {
                                        String name = queryResult.getString("name");
                                        String lastname = queryResult.getString("lastname");
                                        String position = queryResult.getString("position");
                                        String age = queryResult.getString("age");
                                        String userid = queryResult.getString("userid");
                                        System.out.println(name+lastname+userid);
                                        workerText.clear();
                                        workerText.appendText("User Id:"+userid+"\n"+
                                        		"Name:"+name+"\n"+
                                        		"Lastname:"+lastname+"\n"+
                                        		"Position:"+position+"\n"+
                                        		"Age:"+age);
                    	           //System.out.println(choiceBoxId);
                    	            }
                    	            } catch (Exception e) {
                    	           e.printStackTrace();
              	       }}	   
            
            //System.out.println(choiceBoxId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    
    public void textclear() {                                      //clearing the info in the textArea
    	workerText.clear();
    	System.out.println("Erased");
    }
    
   public void loadChoice() {                                     //Making a choice box and giving it the choices of the Id of each worker
	   DatabaseConnection connectNow = new DatabaseConnection();
       Connection connectDB = connectNow.getConnection();
       String query = "SELECT userid FROM users";

       try {
           // connect to the database
           workerList.clear();
           Statement statement = connectDB.createStatement();
           ResultSet queryResult = statement.executeQuery(query);

           while (queryResult.next()) {
        	   choiceBoxId.getItems().addAll(queryResult.getInt("userid"));
        	 }
           System.out.println(choiceBoxId.getValue());
           
           
          
        	   
           
           //System.out.println(choiceBoxId);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
    

}
