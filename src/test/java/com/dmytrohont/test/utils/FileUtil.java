package com.dmytrohont.test.utils;

import static com.dmytrohont.test.utils.PropertyUtil.ENVIRONMENT;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static JsonNode loadJsonData(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String path = String.format("src/test/resources/com/dmytrohont/test/data/%s/%s", ENVIRONMENT, filename);
        return mapper.readTree(new File(path));
    }
}
