package com.framework.utils;

import org.testng.annotations.DataProvider;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "urlProvider")
    public static Object[][] getUrlData() {
        List<String> urls = DatabaseUtils.getUrlsFromDatabase();
        Object[][] data = new Object[urls.size()][1];

        for (int i = 0; i < urls.size(); i++) {
            data[i][0] = urls.get(i);
        }

        return data;
    }
}
