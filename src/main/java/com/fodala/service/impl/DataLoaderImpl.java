package com.fodala.service.impl;

import com.fodala.service.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;

@Service
public class DataLoaderImpl implements DataLoader {

    private static final Logger logger = LoggerFactory.getLogger(DataLoaderImpl.class);

    private @Autowired
    DataSource ds;

    @Override
    public void loadData(String resourceFile) {
        try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement()) {
            String sql = loadResource(resourceFile);
            logger.trace("Executing \n{}\n", sql);
            statement.setQueryTimeout(30);  // set timeout to 30 seconds.
            logger.info("Completed loading {}.", resourceFile);
            statement.executeUpdate(sql);
            logger.info("Completed executing {}.", resourceFile);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    String loadResource(String name) {
        logger.info("Loading resource {}", name);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:" + name);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public void saveFileToTable(String filePath, String tableName) {
        try (Connection connection = ds.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO " + tableName + "(name, uploaded_path, file_size, file_type, data) VALUES (?,?,?,?,?)");
             FileInputStream inputStream = new FileInputStream(new File(filePath))
        ) {
            File file = new File(filePath);
            statement.setString(1, file.getName());
            statement.setString(2, filePath);
            statement.setInt(3, (int) file.length());
            String fileType = "";
            int i = filePath.lastIndexOf('.');
            if (i > 0) {
                fileType = filePath.substring(i + 1);
            }
            statement.setString(4, fileType);
            byte[] ba = new byte[(int) file.length()];
            int read = inputStream.read(ba);
            logger.info("Read {} bytes from file {}", read, filePath);
            statement.setBytes(5, ba);
            statement.executeUpdate();
            logger.info("File saved to database successfully.");
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void exportFile(String tableName, String targetPath, String column, Integer id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("SELECT " + column + " FROM " + tableName + " WHERE id = " + id);
             ResultSet rs = statement.executeQuery()
        ) {
            byte[] ba = rs.getBytes(1);
            try (FileOutputStream fileOutputStream = new FileOutputStream(targetPath)) {
                fileOutputStream.write(ba);
            }
        } catch (SQLException | IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
