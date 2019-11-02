package org.floworc.task.gcp.gcs;

import com.google.common.collect.ImmutableMap;
import io.micronaut.context.annotation.Value;
import io.micronaut.test.annotation.MicronautTest;
import org.floworc.core.runners.RunContext;
import org.floworc.core.runners.RunOutput;
import org.floworc.core.storages.StorageInterface;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@MicronautTest
class GcsCopyTest {
    @Inject
    private StorageInterface storageInterface;

    @Value("${floworc.tasks.gcs.bucket}")
    private String bucket;

    @Test
    void run() throws Exception {
        RunContext runContext = new RunContext(
            this.storageInterface,
            ImmutableMap.of(
                "bucket", this.bucket
            )
        );

        GcsCopy task = GcsCopy.builder()
            .from("gs://{{bucket}}/file/storage/get.yml")
            .to("gs://{{bucket}}/file/storage/get2.yml")
            .build();

        RunOutput run = task.run(runContext);

        assertThat(run.getOutputs().get("uri"), is(new URI("gs://airflow_tmp_lmfr-ddp-dcp-dev/file/storage/get2.yml")));
    }
}