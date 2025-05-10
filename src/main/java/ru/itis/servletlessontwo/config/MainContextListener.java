package ru.itis.servletlessontwo.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.servletlessontwo.mapper.CategoryMapper;
import ru.itis.servletlessontwo.mapper.ProductMapper;
import ru.itis.servletlessontwo.mapper.impl.CategoryMapperImpl;
import ru.itis.servletlessontwo.mapper.impl.ProductMapperImpl;
import ru.itis.servletlessontwo.repository.CategoryRepository;
import ru.itis.servletlessontwo.repository.ProductRepository;
import ru.itis.servletlessontwo.repository.impl.CategoryRepositoryImpl;
import ru.itis.servletlessontwo.repository.impl.ProductRepositoryImpl;
import ru.itis.servletlessontwo.service.ProductService;
import ru.itis.servletlessontwo.service.impl.ProductServiceImpl;
import ru.itis.servletlessontwo.utils.PropertyReader;

import javax.sql.DataSource;

@Slf4j
@WebListener
public class MainContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext(); // context - глобальное хранилище для всего веб-приложения

        DataSource dataSource = dataSource();
        context.setAttribute("dataSource", dataSource); // сохраняет объект DataSource в это хранилище под именем "dataSource"

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        context.setAttribute("jdbcTemplate", jdbcTemplate);

        ProductMapper productMapper = new ProductMapperImpl();
        context.setAttribute("productMapper", productMapper);

        CategoryMapper categoryMapper = new CategoryMapperImpl();
        context.setAttribute("categoryMapper", categoryMapper);

        CategoryRepository categoryRepository = new CategoryRepositoryImpl(jdbcTemplate, categoryMapper);
        context.setAttribute("categoryRepository", categoryRepository);

        ProductRepository productRepository = new ProductRepositoryImpl(jdbcTemplate, categoryRepository, productMapper);
        context.setAttribute("productRepository", productRepository);

        ProductService productService = new ProductServiceImpl(productRepository, productMapper);
        context.setAttribute("productService", productService);

    }

    private DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(PropertyReader.getProperty("DB_URL"));
        dataSource.setUser(PropertyReader.getProperty("DB_USER"));
        dataSource.setPassword(PropertyReader.getProperty("DB_PASSWORD"));
        return dataSource;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("-=-=-=-=-=-=-=-=- CONTEXT DESTROYED -==-=-=-=-=-=-=-=-=");
    }
}
