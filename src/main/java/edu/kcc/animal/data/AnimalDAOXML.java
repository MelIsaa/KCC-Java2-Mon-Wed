package edu.kcc.animal.data;

import edu.kcc.animal.Animal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Melissa Isaacson
 */
public class AnimalDAOXML implements AnimalDAO {
    private static final String FILE_NAME = "../AnimalServer/animalLookupRecords.xml";
    private static HashMap<String, Animal> animals;
    
    private void readFromFile() throws AnimalDataException {
        try ( InputStream inputStream = new FileInputStream(FILE_NAME)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputStream);

            NodeList animalNodeList = document.getElementsByTagName("animal");

            animals = new HashMap<>();
            
            for (int i = 0; i < animalNodeList.getLength(); i++) {
                
                Node currentAnimalNode = animalNodeList.item(i);
                
                NamedNodeMap animalAttributeMap = currentAnimalNode.getAttributes();
                Attr attr = (Attr) animalAttributeMap.getNamedItem("date-lookup");
                
                animals.put(attr.getValue(), buildAnimalFromNode(currentAnimalNode));
            }

        } catch (Exception ex) {
            throw new AnimalDataException(ex);
        }
    }
    
    private static Animal buildAnimalFromNode(Node animalNode) {
        Animal newAnimal = new Animal();

        NamedNodeMap animalAttributeMap = animalNode.getAttributes();
        Attr attr = (Attr) animalAttributeMap.getNamedItem("date-lookup");
        // newAnimal.setId(attr.getValue());

        NodeList animalDataNodeList = animalNode.getChildNodes();
        for (int i = 0; i < animalDataNodeList.getLength(); i++) {
            Node dataNode = animalDataNodeList.item(i);

            if (dataNode instanceof Element) {
                Element dataElement = (Element) dataNode;
                switch (dataElement.getTagName()) {
                    case "id":
                        String idValue = dataElement.getTextContent();
                        newAnimal.setId(idValue);
                        break;
                    case "name":
                        String nameValue = dataElement.getTextContent();
                        newAnimal.setName(nameValue);
                        break;
                    case "species":
                        String speciesValue = dataElement.getTextContent();
                        newAnimal.setSpecies(speciesValue);
                        break;
                    case "gender":
                        String genderValue = dataElement.getTextContent();
                        newAnimal.setGender(genderValue);
                        break;
                    case "age":
                        int ageValue = Integer.parseInt(dataElement.getTextContent());
                        newAnimal.setAge(ageValue);
                        break;
                    case "fixed":
                        boolean fixedValue = Boolean.parseBoolean(dataElement.getTextContent());
                        newAnimal.setFixed(fixedValue);
                        break;
                    case "legs":
                        int legsValue = Integer.parseInt(dataElement.getTextContent());
                        newAnimal.setLegs(legsValue);
                        break;
                    case "weight":
                        BigDecimal weightValue = new BigDecimal(dataElement.getTextContent());
                        newAnimal.setWeight(weightValue);
                        break;
                    case "dateAdded":
                        LocalDate dateAddedValue = LocalDate.parse(dataElement.getTextContent());
                        newAnimal.setDateAdded(dateAddedValue);
                        break;
                    case "lastFeedingTime":
                        LocalDateTime lastFeedingTimeValue = LocalDateTime.parse(dataElement.getTextContent());
                        newAnimal.setLastFeedingTime(lastFeedingTimeValue);
                        break;
                    default:
                        break;
                }
            }

        }

        return newAnimal;
    }
    
    private void saveToFile() throws AnimalDataException {
        try ( FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("animals");
            document.appendChild(rootElement);
            
            animals.forEach((key, value) -> {
                    DocumentFragment animalFragment = buildAnimalFragment(document, value);
                    rootElement.appendChild(animalFragment);
            });

//            for (Animal currentAnimal : animals) {
//                DocumentFragment animalFragment = buildAnimalFragment(document, currentAnimal);
//                rootElement.appendChild(animalFragment);
//            }

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // code via https://stackoverflow.com/questions/1384802/java-how-to-indent-xml-generated-by-transformer
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(source, new StreamResult(fos));
        } catch (Exception ex) {
            throw new AnimalDataException(ex);
        }
    }
    
    private static DocumentFragment buildAnimalFragment(Document document, Animal animal) {
        DocumentFragment recordFragment = document.createDocumentFragment();

        Element animalElement = document.createElement("animal");
        animalElement.setAttribute("date-lookup", LocalDateTime.now().toString());

        Element idElement = document.createElement("id");
        idElement.setTextContent(animal.getId());
        animalElement.appendChild(idElement);

        Element nameElement = document.createElement("name");
        nameElement.setTextContent(animal.getName());
        animalElement.appendChild(nameElement);

        Element speciesElement = document.createElement("species");
        speciesElement.setTextContent(animal.getSpecies());
        animalElement.appendChild(speciesElement);

        Element genderElement = document.createElement("gender");
        genderElement.setTextContent(animal.getGender());
        animalElement.appendChild(genderElement);

        Element ageElement = document.createElement("age");
        ageElement.setTextContent(Integer.toString(animal.getAge()));
        animalElement.appendChild(ageElement);

        Element fixedElement = document.createElement("fixed");
        fixedElement.setTextContent(Boolean.toString(animal.getFixed()));
        animalElement.appendChild(fixedElement);

        Element legsElement = document.createElement("legs");
        legsElement.setTextContent(Integer.toString(animal.getLegs()));
        animalElement.appendChild(legsElement);

        Element weightElement = document.createElement("weight");
        weightElement.setTextContent(animal.getWeight().toString());
        animalElement.appendChild(weightElement);

        Element dateAddedElement = document.createElement("dateAdded");
        dateAddedElement.setTextContent(animal.getDateAdded().toString());
        animalElement.appendChild(dateAddedElement);

        Element lastFeedingTimeElement = document.createElement("lastFeedingTime");
        lastFeedingTimeElement.setTextContent(animal.getLastFeedingTime().toString());
        animalElement.appendChild(lastFeedingTimeElement);


        recordFragment.appendChild(animalElement);
        return recordFragment;
    }
    
    private void verifyAnimalList() throws AnimalDataException {
        if (null == animals) {
            readFromFile();
        }
    }
    
    @Override
    public void createAnimal(Animal animal) throws AnimalDataException {
        verifyAnimalList();
        // Look to see if there is already an animal with the same name
        Animal checkAnimal = getAnimalByAnimalName(animal.getName());
        // If there was a matching animal, throw an exception.  The name
        // is used as a unique identifier in this example.
        if (null != checkAnimal) {
            throw new AnimalDataException("Animal name must be unique.");
        }
        // No other animal has the same name, so we can add this Animal to
        // the data store.
        animals.put(LocalDateTime.now().toString(), animal);
        saveToFile();
    }
    
    @Override
    public Animal getAnimalByAnimalName(String name) throws AnimalDataException {
        verifyAnimalList();
        Animal animal = null;
        
        for(HashMap.Entry<String, Animal> entry: animals.entrySet()) {
            if(entry.getValue().getName().equals(name)) {
                animal = entry.getValue();
                break;
            }
        }
        
        
//        for (Animal animal1 : animals) {
//            // See if the animal has a matching name
//            if (animal1.getName().equals(name)) {
//                // found a match, so it is the animal we want
//                animal = animal1;
//                // leave the loop
//                break;
//            }
//        }
        return animal;
    }
    
    @Override
    public HashMap<String, Animal> getAllAnimals() throws AnimalDataException {
        verifyAnimalList();
        return animals;
    }
    
    @Override
    public void updateAnimal(Animal original, Animal updated) throws AnimalDataException {
        // Code here
    }
    
    @Override
    public void deleteAnimal(Animal animal) throws AnimalDataException {
        // Code here
    }
    
    @Override
    public void deleteAnimal(String animalName) throws AnimalDataException {
        // Code here
    }
    
}
