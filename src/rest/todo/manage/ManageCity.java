package rest.todo.manage;


import rest.todo.model.City;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

import static rest.todo.utils.Utils.getVariousRandomsDistinctElementsFromAList;

@XmlRootElement
public class ManageCity {
    private static final List<String> allAddresses = Arrays.asList(
        "UGC Ciné Cité Les Halles (Paris)",
        "Pathé La Villette (Paris)",
        "Pathé Quai d'Ivry (Ivry-sur-Seine)",
        "UGC Ciné Cité Bercy (Paris)",
        "Gaumont Disney Village (Marne-la-Vallée)",
        "Pathé Carré de Soie (Vaulx-en-Velin)",
        "UGC Ciné Cité Lyon (Lyon)",
        "Pathé Plan de Campagne (Les Pennes-Mirabeau)",
        "CGR Torcy (Torcy)",
        "Gaumont Aquaboulevard (Paris)"
    );
    private static final List<String> allDays = Arrays.asList("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche");
    private static final List<String> allSchedules = Arrays.asList("11:00", "14:00", "16:00", "18:00", "20:00", "22:00");

    public ManageCity(){

    }

    public static City newCity(String address) {
        return new City(
            address,
            getVariousRandomsDistinctElementsFromAList(allDays, 3),
            getVariousRandomsDistinctElementsFromAList(allSchedules, 2)
        );
    }

    public static List<City> getCities(){
        Random rand = new Random();
        int nbElements = rand.nextInt(5) + 1;
        List<String> addresses = getVariousRandomsDistinctElementsFromAList(allAddresses, nbElements);
        List<City> cities = new ArrayList<>(Collections.emptyList());
        for (int i = 0; i < nbElements; i++) {
            cities.add(newCity(addresses.get(i)));
        }
        return cities;
    }
}