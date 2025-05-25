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
import ru.itis.servletlessontwo.mapper.UserMapper;
import ru.itis.servletlessontwo.mapper.impl.CategoryMapperImpl;
import ru.itis.servletlessontwo.mapper.impl.ProductMapperImpl;
import ru.itis.servletlessontwo.mapper.impl.UserMapperImpl;
import ru.itis.servletlessontwo.repository.CategoryRepository;
import ru.itis.servletlessontwo.repository.FavoritesRepository;
import ru.itis.servletlessontwo.repository.ProductRepository;
import ru.itis.servletlessontwo.repository.UserRepository;
import ru.itis.servletlessontwo.repository.impl.CategoryRepositoryImpl;
import ru.itis.servletlessontwo.repository.impl.FavoritesRepositoryImpl;
import ru.itis.servletlessontwo.repository.impl.ProductRepositoryImpl;
import ru.itis.servletlessontwo.repository.impl.UserRepositoryImpl;
import ru.itis.servletlessontwo.service.CategoryService;
import ru.itis.servletlessontwo.service.FavoritesService;
import ru.itis.servletlessontwo.service.ProductService;
import ru.itis.servletlessontwo.service.UserService;
import ru.itis.servletlessontwo.service.impl.CategoryServiceImpl;
import ru.itis.servletlessontwo.service.impl.FavoritesServiceImpl;
import ru.itis.servletlessontwo.service.impl.ProductServiceImpl;
import ru.itis.servletlessontwo.service.impl.UserServiceImpl;
import ru.itis.servletlessontwo.utils.PropertyReader;

import javax.sql.DataSource;
import java.util.List;

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

        UserMapper userMapper = new UserMapperImpl();
        context.setAttribute("userMapper", userMapper);

        CategoryRepository categoryRepository = new CategoryRepositoryImpl(jdbcTemplate, categoryMapper);
        context.setAttribute("categoryRepository", categoryRepository);

        FavoritesRepository favoritesRepository = new FavoritesRepositoryImpl(jdbcTemplate, productMapper, categoryRepository);
        context.setAttribute("favoritesRepository", favoritesRepository);

        ProductRepository productRepository = new ProductRepositoryImpl(jdbcTemplate, categoryRepository, productMapper, favoritesRepository);
        context.setAttribute("productRepository", productRepository);

        UserRepository userRepository = new UserRepositoryImpl(jdbcTemplate, userMapper);
        context.setAttribute("userRepository", userRepository);

        CategoryService categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
        context.setAttribute("categoryService", categoryService);

        ProductService productService = new ProductServiceImpl(productRepository, productMapper);
        context.setAttribute("productService", productService);

        UserService userService = new UserServiceImpl(userRepository, userMapper);
        context.setAttribute("userService", userService);

        FavoritesService favoritesService = new FavoritesServiceImpl(favoritesRepository, productMapper);
        context.setAttribute("favoritesService", favoritesService);

        List<String> PROTECTED_URIS = List.of(PropertyReader.getProperty("PROTECTED_URIS").split(","));
        context.setAttribute("PROTECTED_URIS", PROTECTED_URIS);
        List<String> PROTECTED_ADMIN_URIS = List.of(PropertyReader.getProperty("PROTECTED_ADMIN_URIS").split(","));
        context.setAttribute("PROTECTED_ADMIN_URIS", PROTECTED_ADMIN_URIS);
        List<String> NOTAUTH_URIS = List.of(PropertyReader.getProperty("NOTAUTH_URIS").split(","));
        context.setAttribute("NOTAUTH_URIS", NOTAUTH_URIS);


        String PROTECTED_REDIRECT = PropertyReader.getProperty("PROTECTED_REDIRECT");
        context.setAttribute("PROTECTED_REDIRECT", PROTECTED_REDIRECT);
        String PROTECTED_ADMIN_REDIRECT = PropertyReader.getProperty("PROTECTED_ADMIN_REDIRECT");
        context.setAttribute("PROTECTED_ADMIN_REDIRECT", PROTECTED_ADMIN_REDIRECT);
        String NOTAUTH_REDIRECT = PropertyReader.getProperty("NOTAUTH_REDIRECT");
        context.setAttribute("NOTAUTH_REDIRECT", NOTAUTH_REDIRECT);

        String AUTHORIZATION = PropertyReader.getProperty("AUTHORIZATION");
        context.setAttribute("AUTHORIZATION", AUTHORIZATION);

        String IS_ADMIN = PropertyReader.getProperty("IS_ADMIN");
        context.setAttribute("IS_ADMIN", IS_ADMIN);

        String SECRET_KEY = PropertyReader.getProperty("SECRET_KEY");
        context.setAttribute("SECRET_KEY", SECRET_KEY);

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
