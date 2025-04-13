package com.dmytrohont.test.utils;

import static com.dmytrohont.test.utils.PropertyUtil.ENVIRONMENT;

import com.dmytrohont.test.context.ScenarioContext;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;

public class DataTableUtil {

    private DataTableUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Map<String, String>> getRowsForCurrentEnv(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        return rows.stream()
                .filter(row -> row.get("environment").equals(ENVIRONMENT))
                .toList();
    }

    public static void getRowsForCurrentEnvAndStore(DataTable dataTable) {
        ScenarioContext.setDataRows(getRowsForCurrentEnv(dataTable));
    }

    public static boolean getBoolean(String boolString) {
        return Boolean.getBoolean(boolString);
    }

}
