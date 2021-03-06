package net.jqwik.testcontainers;

import org.testcontainers.containers.*;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.*;

import static org.assertj.core.api.Assertions.*;

import static net.jqwik.testcontainers.JqwikTestImages.*;

@Testcontainers
class TestcontainersGroupedSharedContainerTests {

	@Container
	private static final GenericContainer<?> TOP_LEVEL_CONTAINER = new GenericContainer<>(HTTPD_IMAGE.toString())
			.withExposedPorts(80);

	private static String topLevelContainerId;

	@BeforeContainer
	static void setTopLevelContainer() {
		topLevelContainerId = TOP_LEVEL_CONTAINER.getContainerId();
	}

	@Example
	void top_level_container_should_be_running() {
		assertThat(TOP_LEVEL_CONTAINER.isRunning()).isTrue();
	}

	@Group
	class GroupedExamples {

		@Example
		void top_level_containers_should_be_running() {
			assertThat(TOP_LEVEL_CONTAINER.isRunning()).isTrue();
		}

		@Example
		void ids_should_not_change() {
			assertThat(topLevelContainerId).isEqualTo(TOP_LEVEL_CONTAINER.getContainerId());
		}
	}
}
