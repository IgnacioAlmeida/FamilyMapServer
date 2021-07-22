package Serializer;

import com.google.gson.Gson;

import java.io.IOException;

public class JsonSerializer {
    /**
     * A generic Gson deserialization method that will return a runtime specified data type.
     * @param value
     * @param returnType
     * @param <T>
     * @return The converted Json
     */
    public static <T> T deserialize (String value, Class<T> returnType){
        return (new Gson()).fromJson(value, returnType);
    }

    /**
     * It converts an object to Json.
     * @param object
     * @return The converted object
     * @throws IOException
     */
    public static String serialize(Object object) throws IOException {
        return (new Gson()).toJson(object);
    }
}
