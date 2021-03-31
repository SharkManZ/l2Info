package ru.shark.home.l2info.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.shark.home.l2info.dao.repository.JpaBaseRepository;

@Configuration
@EnableJpaRepositories(basePackages = "ru.shark.home.l2info.dao.repository", repositoryBaseClass = JpaBaseRepository.class)
public class RepositoryConfig {
}
