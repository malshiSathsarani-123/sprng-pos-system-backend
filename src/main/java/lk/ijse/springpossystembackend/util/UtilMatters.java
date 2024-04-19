package lk.ijse.springpossystembackend.util;

import java.util.Base64;
import java.util.UUID;

public class UtilMatters {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String covertBase64(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

}
