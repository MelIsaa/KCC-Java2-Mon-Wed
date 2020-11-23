package edu.kcc.animal.taskHandler;

import edu.kcc.Main;
import edu.kcc.animal.Animal;
import edu.kcc.animal.data.AnimalDataHandler;
import edu.kcc.ui.UIUtility;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Melissa Isaacson
 */
public class FindAnAnimal implements AnimalDataHandler {
    
    private static final int PORT = 5555;
    private static final String HOST_NAME = "localhost";
    
    private static Animal getAnimalFromServer(String name) throws UnknownHostException, IOException{
        Socket socket = new Socket(HOST_NAME, PORT);
        Animal animal = null;
        
        try (   ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
            ) {
            // Tell the server you're looking for an animal
            outputStream.writeObject("MYSQL");
            outputStream.flush();
            // write name to stream that goes to server
            outputStream.writeObject(name);
            outputStream.flush();
            // get animal object from stream coming from server
            animal = (Animal) inputStream.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return animal;
    }
    
    @Override
    public void handleTask() {
        UIUtility.showSectionTitle("Find an Animal");
        
        String prompt = "Enter Animal Name: ";
        String animalName = UIUtility.getUserString(prompt);
        UIUtility.showMessage("Searching for animal name " + animalName +
                "...");
        Animal animal;
        
        try {
            animal = getAnimalFromServer(animalName);
            
            if(animal == null) {
                UIUtility.showMessage("No animal found with the name: " +
                        animalName);
            } else {
                UIUtility.showMessage("Retrieved animal: " + animal);
            }
        } catch (IOException ex) {
            Logger.getLogger(FindAnAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        UIUtility.showMessage("Find an animal complete.");
        UIUtility.pressEnterToContinue();
    }
    
}
