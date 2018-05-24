package pnp.ringmachine.engine.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableJpaRepositories( basePackages="pnp.ringmachine.engine.repository" )
class PersistenceConfig { }
