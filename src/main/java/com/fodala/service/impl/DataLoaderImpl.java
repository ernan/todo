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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
