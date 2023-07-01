package com.fodala.service;

public interface DataLoader {
    void loadData(String resourceFile);

    void saveFileToTable(String filePath, String tableName);

    void exportFile(String tableName, String targetPath, String column, Integer id);
}
