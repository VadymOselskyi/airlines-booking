package io.skai.reservation.pl;

import com.kenshoo.pl.entity.PLContext;
import com.kenshoo.pl.entity.PersistenceLayer;
import lombok.RequiredArgsConstructor;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.ThreadLocalTransactionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final ConnectionProvider connectionProvider;

    @Bean
    public PLContext defineJooq() {
        DSLContext jooq = DSL.using(new DefaultConfiguration()
                .set(SQLDialect.MYSQL)
                .set(new ThreadLocalTransactionProvider(connectionProvider)));
        return new PLContext.Builder(jooq).build();
    }

    @Bean
    public PersistenceLayer<AirportEntity> persistenceLayer() {
        return new PersistenceLayer<>(defineJooq());
    }
}