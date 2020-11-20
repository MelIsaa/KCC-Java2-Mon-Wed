/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.animal.data;

import edu.kcc.animal.Animal;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinayak Deshpande
 */
public class AnimalDAOMySQL implements AnimalDAO {

    private static ArrayList<Animal> animals;

    private Connection buildConnection() throws SQLException {
        String databaseUrl = "localhost";
        String databasePort = "3306";
        String databaseName = "javaii_final_project";
        String userName = "root";
        String password = "NCC1701D";

        String connectionString = "jdbc:mysql://" + databaseUrl + ":"
                + databasePort + "/" + databaseName + "?"
                + "user=" + userName + "&"
                + "password=" + password + "&"
                + "useSSL=false&serverTimezone=UTC";
        
        return DriverManager.getConnection(connectionString);
    }

    private void readFromDataBase() throws AnimalDataException {

        try (Connection conn = buildConnection()) {
            if (conn.isValid(2)) {
                try (
                        CallableStatement callableStatement = conn.prepareCall("Call sp_show_animals();"); 
                        ResultSet resultSet = callableStatement.executeQuery()
                    ) {
                    
                    String id;
                    String name;
                    String species;
                    String gender;
                    int age;
                    boolean fixed;
                    int legs;
                    BigDecimal weight;
                    LocalDate dateAdded;
                    LocalDateTime lastFeedingTime;
                    
                    animals = new ArrayList<Animal>();
                    
                    while (resultSet.next()) {
                        id = resultSet.getString("Id");
                        name = resultSet.getString("Animal_Name");
                        species = resultSet.getString("Species");
                        gender = resultSet.getString("Gender");
                        age = resultSet.getInt("Age");
                        fixed = resultSet.getBoolean("Fixed");
                        legs = resultSet.getInt("Legs");
                        weight = resultSet.getBigDecimal("Weight");
                        dateAdded = resultSet.getObject(
                                "Date_Added", LocalDate.class);
                        lastFeedingTime = resultSet.getObject(
                                "Last_Feeding_Time", LocalDateTime.class);
                        animals.add(new Animal(
                                id,
                                name,
                                species,
                                gender,
                                age,
                                fixed,
                                legs,
                                weight,
                                dateAdded,
                                lastFeedingTime
                        ));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception Message: " + ex.getMessage());
            if (ex instanceof SQLException) {
                SQLException sqlException = (SQLException) ex;
                System.out.println("Error Code: " + sqlException.getErrorCode());
                System.out.println("SQL State: " + sqlException.getSQLState());
            }
        }
    }

    private void verifyAnimalList() throws AnimalDataException {
        if (null == animals) {
            readFromDataBase();
        }
    }

    @Override
    public void createAnimal(Animal animal) throws AnimalDataException {
        verifyAnimalList();
        Animal checkAnimal = getAnimalByAnimalName(animal.getName());
        if (null != checkAnimal) {
            throw new AnimalDataException("Animal names must be unique...");
        }
        animals.add(animal);
        try {
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CAll sp_create_new_animal(?,?,?,?,?,?,?,?);");
            callableStatement.setString("Id", animal.getId());
            callableStatement.setString("Name", animal.getName());
            callableStatement.setString("Species", animal.getSpecies());
            callableStatement.setString("Gender", animal.getGender());
            callableStatement.setInt("Age", animal.getAge());
            callableStatement.setBoolean("Fixed", animal.getFixed());
            callableStatement.setInt("Legs", animal.getLegs());
            callableStatement.setBigDecimal("Weight", animal.getWeight());
            callableStatement.setObject("Date_Added", animal.getDateAdded());
            callableStatement.setObject("Last_Feeding_Time",
                    animal.getLastFeedingTime());
            
            callableStatement.execute();
            callableStatement.close();
            conn.close();
        
        } catch (SQLException ex) {
            throw new AnimalDataException(ex);
        } 
        
    }
    
    @Override
    public ArrayList<Animal> getAllAnimals() throws AnimalDataException {
        verifyAnimalList();
        return animals;
    }
    
    @Override
    public Animal getAnimalByAnimalName(String name) throws AnimalDataException {
        Animal animal = null;
        verifyAnimalList();
        for(Animal tempAnimal : animals) {
            if(tempAnimal.getName().equals(name)) {
                animal = tempAnimal;
                break;
            }
        
        }
        return animal;
    }
    
    @Override
    public void updateAnimal(Animal original, Animal updated) throws AnimalDataException {
        // Code Here
    }
    
    @Override
    public void deleteAnimal(Animal animal) throws AnimalDataException {
        // Code Here
    }
    
    @Override
    public void deleteAnimal(String name) throws AnimalDataException {
        // Code Here
    }
}
