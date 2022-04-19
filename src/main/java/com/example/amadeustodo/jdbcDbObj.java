
package com.example.amadeustodo;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Alert;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.sql.*;
        import java.time.LocalDate;


        import java.lang.*;
        import java.time.ZoneId;
        import java.util.ArrayList;
        import java.util.Date;


public class jdbcDbObj {

    public static  String current_user;

    public static void changeScene(ActionEvent event, String fxmlFile, String username){
        Parent root = null;
        if(username != null){
            try{
                FXMLLoader loader = new FXMLLoader(jdbcDbObj.class.getResource(fxmlFile));
                root = loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

        }else {
            try{
                FXMLLoader loader = new FXMLLoader(jdbcDbObj.class.getResource(fxmlFile));
                root = loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage)( (Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,600,400));
        stage.show();

    }


    public static void signUpUser(ActionEvent event, String username, String password, String email){

        try {
            Database database = new Database("amadeus", "root", "");
            String[] columns = {"username", "password", "email"};
            //Object[] firstParams = {"12","Mahtab","iut", "mahtab112@gmail.com"};
            Object[] firstParams = {username,password, email};
            int success1 = database.insert("user",firstParams);
            System.out.println("Insert a user = " + success1);

            changeScene(event, "Login.fxml", username);

        } catch (SQLException ex) {
            System.out.println("error - "+ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/amadeus", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    current_user = username;

                    if(retrievedPassword.equals(password)){
                        changeScene(event,"Dashboard.fxml", username);
                    }
                    else{
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void addNewTask(ActionEvent event, String title, String description, LocalDate deadline, Category category){
        String username = current_user;
        String  cat = "";
        String  stringCategory = category.toString();
        if(stringCategory.equals("Personal"))
            cat += "Personal";

        else if(stringCategory.equals("Work")) cat += "Work";
        try {
            Database database = new Database("amadeus", "root", "");

            String[] columns = {"title", "description", "deadline","username", "category"};
            Object[] firstParams = {title,description, deadline,current_user,category.toString()};
            int success1 = database.insert("task", columns, firstParams);
            System.out.println("Insert a task = " + success1);

            //changeScene(event, "Login.fxml", username);

        } catch (SQLException ex) {
            System.out.println("error - "+ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    public static void addNewNotes(ActionEvent event, String title, String description){
        String username = current_user;
        try {
            Database database = new Database("amadeus", "root", "");
            String[] columns = {"title", "description","username"};
            Object[] firstParams = {title,description,current_user};
            int success1 = database.insert("notes", columns, firstParams);
            System.out.println("Insert a note = " + success1);

            //changeScene(event, "Login.fxml", username);

        } catch (SQLException ex) {
            System.out.println("error - "+ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static ObservableList<Tasks> showPersonalTaskList() throws Exception
    {
        String username = current_user;
        ArrayList<Tasks> tasks = new ArrayList<>();
        Tasks f;
        ObservableList<Tasks> tasksList = FXCollections.observableArrayList();
        try
        {
            Database database = new Database("amadeus", "root", "");
            String[] columns = {"taskid" ,"title", "description", "deadline", "username", "category"};
            Object[] params = {username};
            ResultSet rs = database.select("task", columns, "username = ? ", params);

            if(!rs.isBeforeFirst()) throw new Exception("User NOT FOUND IN THE DATABASE!");

            while(rs.next())
            {
                int taskid = rs.getInt("taskid");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                Date deadline = rs.getDate("deadline");
                //LocalDate date = LocalDate.ofInstant(deadline.toInstant(), ZoneId.systemDefault());
                String usname = rs.getString("username");
                String cat = rs.getString("category");
                Category category = null;
                if(cat.equals("Personal") )
                    category = Category.Personal;
                else if (cat.equals("Work"))
                    category = Category.Work;

//                Tasks task = new Tasks(category, title, desc, Tasks.dateInput("20/4/2022"));
                if(category == Category.Personal) {
                    System.out.println("Title = " + title + ", Desc = " + desc + " deadline = " + deadline + "category = " + category);
                  // Tasks task1 = new Tasks(taskid,category, title, desc, Tasks.dateInput("21/4/2022"));
                    Tasks task1 = new Tasks(taskid,category, title, desc,Tasks.dateInput("20/4/2022") );

                    tasksList.add(task1);
                }

            }

            return tasksList;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Error!");
        }
    }

    public static ObservableList<Tasks> showWorkTaskList() throws Exception
    {
        String username = current_user;

        ObservableList<Tasks> tasksList = FXCollections.observableArrayList();
        try
        {
            Database database = new Database("amadeus", "root", "");
            String[] columns = { "taskid","title", "description", "deadline", "username", "category"};
            Object[] params = {username};
            ResultSet rs = database.select("task", columns, "username = ? ", params);

            if(!rs.isBeforeFirst()) throw new Exception("User NOT FOUND IN THE DATABASE!");

            while(rs.next())
            {
                int taskid = rs.getInt("taskid");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                Date deadline = rs.getDate("deadline");
                //  LocalDate date = LocalDate.ofInstant(deadline.toInstant(), ZoneId.systemDefault());
                String usname = rs.getString("username");
                String cat = rs.getString("category");
                Category category = null;
                if(cat.equals("Personal") )
                    category = Category.Personal;
                else if (cat.equals("Work"))
                    category = Category.Work;

//                Tasks task = new Tasks(category, title, desc, Tasks.dateInput("20/4/2022"));
                if(category == Category.Work) {
                    System.out.println("Title = " + title + ", Desc = " + desc + " deadline = " + deadline + "category = " + category);
                  Tasks task1 = new Tasks(taskid,category, title, desc, Tasks.dateInput("21/4/2022"));
                    //Tasks task1 = new Tasks(taskid,category, title, desc, (java.sql.Date) deadline);
                    tasksList.add(task1);
                }

            }

            return tasksList;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Error!");
        }
    }
    public static ObservableList<Notes> showNotesList() throws Exception
    {
        String username = current_user;

        ObservableList<Notes> notesList = FXCollections.observableArrayList();
        try
        {
            Database database = new Database("amadeus", "root", "");
            String[] columns = { "title", "description","username"};
            Object[] params = {username};
            ResultSet rs = database.select("notes", columns, "username = ? ", params);

            if(!rs.isBeforeFirst()) throw new Exception("User NOT FOUND IN THE DATABASE!");

            while(rs.next())
            {
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String usname = rs.getString("username");
                Notes note = new Notes(title,desc);
                notesList.add(note);


            }

            return notesList;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Error!");
        }
    }

    public static void deletetask(ActionEvent event,int taskid) throws Exception
    {
        try
        {
            String username = current_user;
            Database database = new Database("amadeus", "root", "");
            Object[] params = {taskid};
            database.delete("task", "taskid = ?", params);
            //System.out.println("ID =" + taskid + " deleted." );

            //changeScene(event, "Dashboard.fxml", username);

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw e;
        }

        // DELETE FROM Owner
        // WHERE Username = username;
    }
    public static void updateTask(ActionEvent event,int taskid, String title, String description, LocalDate deadline, Category category) throws Exception
    {

        String username = current_user;
        String  cat = "";
        String  stringCategory = category.toString();
        if(stringCategory.equals("Personal"))
            cat += "Personal";

        else if(stringCategory.equals("Work")) cat += "Work";
        try {
            Database database = new Database("amadeus", "root", "");
            String[] columns = {"title", "description", "deadline","username", "category"};
            Object[] firstParams = {title,description, deadline,current_user,category.toString()};
            int success1 = database.update("task", columns,"username = ?" ,firstParams);
            System.out.println("Update a task = " + success1);

            //changeScene(event, "Login.fxml", username);

        } catch (SQLException ex) {
            System.out.println("error - "+ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // DELETE FROM Owner
        // WHERE Username = username;
    }

    public static void deleteNotes(ActionEvent event,int taskid) throws Exception
    {
        try
        {
            String username = current_user;
            Database database = new Database("amadeus", "root", "");
            Object[] params = {taskid};
            database.delete("notes", "noteid = ?", params);
            System.out.println("ID =" + taskid + " deleted." );

            changeScene(event, "Dashboard.fxml", username);

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw e;
        }

        // DELETE FROM Owner
        // WHERE Username = username;
    }
}


