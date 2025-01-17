package io.kestra.runner.h2;

import io.kestra.core.runners.ExecutionQueued;
import io.kestra.jdbc.runner.AbstractJdbcExecutionQueuedStorage;
import io.kestra.repository.h2.H2Repository;
import io.micronaut.context.ApplicationContext;
import jakarta.inject.Singleton;

@Singleton
@H2QueueEnabled
public class H2ExecutionQueuedStorage extends AbstractJdbcExecutionQueuedStorage {
    public H2ExecutionQueuedStorage(ApplicationContext applicationContext) {
        super(new H2Repository<>(ExecutionQueued.class, applicationContext));
    }
}
