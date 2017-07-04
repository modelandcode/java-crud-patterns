package com.modelncode.crudpattern.infrastructure.factory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modelncode.crudpattern.domain.User;

import java.io.IOException;

/**
 * Created by g on 2017-07-04.
 */
public class JsonFactory {

    public static String toJson(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(user);
        } catch (JsonGenerationException e) {
            //e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            //e.printStackTrace();
            return null;
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }
}
