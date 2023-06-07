package rest.todo.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class Utils {
    public static String getOneRandomElementFromAList(List<String> list){
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static List<String> getVariousRandomsDistinctElementsFromAList(List<String> list, int nbElements){
        Collections.shuffle(list);
        Random rand = new Random();
        List<String> randomElements = new ArrayList<String>();
        for (int i = 0; i < nbElements; i++) {
            randomElements.add(list.get(i));
        }
        return randomElements;
    }

    public static Date getUnbookingDate(Date releaseDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        Random rand = new Random();

        int nbElements = rand.nextInt(5) + 2;
        calendar.add(Calendar.WEEK_OF_YEAR, nbElements);
        return calendar.getTime();
    }

    public static String setDateFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
