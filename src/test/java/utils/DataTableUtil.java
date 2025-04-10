package utils;

import static utils.PropertyUtil.ENV_PROPERTY;

import context.ScenarioContext;

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
                .filter(row -> row.get("environment").equals(ENV_PROPERTY))
                .toList();
    }

    public static void getRowsForCurrentEnvAndStore(DataTable dataTable) {
        ScenarioContext.setDataRows(getRowsForCurrentEnv(dataTable));
    }
}
