package capitaltwo;

import java.util.List;
import java.util.ArrayList;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.Json;
import javax.persistence.EntityManager;

import java.math.BigInteger;
import java.sql.Timestamp;

public class QueryUtils {

    private QueryUtils() {}

    public static String queryResults(EntityManager em, String query, String[] columns) {
        JsonArray jsonArray =
            toJson(em.createNativeQuery(query).getResultList(), columns);
        return Json.createObjectBuilder().add("results", jsonArray)
            .build().toString();
    }

    public static JsonArray toJson(List<Object[]> queryResults,
                          String[] fieldNames) {
        System.out.println(queryResults);
        System.out.println(fieldNames);
        return toJsonBuilder(queryResults, fieldNames).build();
    }

    public static JsonArrayBuilder toJsonBuilder(List<Object[]> queryResults,
                          String[] fieldNames) {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        if (queryResults.size() == 0) {
            return jsonArray;
        }
        if (queryResults.get(0).length != fieldNames.length) {
            System.out.println("ERROR: LENGTHS ARE NOT EQUAL");
        }
        for (int i = 0; i < queryResults.size(); ++i) {
            JsonObjectBuilder jsonObject = Json.createObjectBuilder();
            Object[] obj = queryResults.get(i);
            for (int j = 0; j < obj.length; ++j) {
                Class c = obj[j].getClass();
                if (c == String.class) {
                    String str = (String)obj[j];
                    jsonObject.add(fieldNames[j], str);
                }
                else if (c == BigInteger.class) {
                    BigInteger num = (BigInteger)obj[j];
                    jsonObject.add(fieldNames[j], num);
                }
                else if (c == Integer.class) {
                    Integer num = (Integer)obj[j];
                    jsonObject.add(fieldNames[j], num);
                }
                else if (c == Long.class) {
                    Long num = (Long)obj[j];
                    jsonObject.add(fieldNames[j], num);
                }
                else if (c == Timestamp.class) {
                    String t = ((Timestamp)obj[j]).toString();
                    jsonObject.add(fieldNames[j], t);
                }
                else if (c == Double.class) { 
                    Double num = (Double)obj[j];
                    jsonObject.add(fieldNames[j], num);
                }
                else if (c == Boolean.class) {
                    Boolean val = (Boolean)obj[j];
                    jsonObject.add(fieldNames[j], val);
                }
                else {
                    System.out.println("ERROR UNKNOWN CLASS TYPE: " + c);
                    String str = (String)obj[j].toString();
                    jsonObject.add(fieldNames[j], str);
                }
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
