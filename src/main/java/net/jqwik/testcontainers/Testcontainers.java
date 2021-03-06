package net.jqwik.testcontainers;

import java.lang.annotation.*;

import net.jqwik.api.lifecycle.*;

/**
 * {@code @Testcontainers} is a Jqwik extension to activate automatic
 * startup and stop of containers used in a test case.
 *
 * <p>The test containers extension finds all fields that are annotated with
 * {@link Container} and calls their container lifecycle methods. Containers
 * declared as static fields will be shared between properties. They will be
 * started only once before any property is tested and stopped after the
 * last try has executed. Containers declared as instance fields will
 * be started and stopped for each property or try.</p>
 *
 * <p>The annotation {@code @Testcontainers} can be used on a superclass in
 * the test hierarchy as well. All subclasses will automatically inherit
 * support for the extension.</p>
 *
 * <p><strong>Note:</strong> This extension has only been tested with sequential
 * test execution. Using it with parallel test execution is unsupported and
 * may have unintended side effects.</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 * &#64;Testcontainers
 * class MyTestcontainersTests {
 *
 *     // will be shared between test methods
 *     &#64;Container
 *     private static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer();
 *
 *     // will be started before and stopped after each test method
 *     &#64;Container
 *     private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
 *             .withDatabaseName("foo")
 *             .withUsername("foo")
 *             .withPassword("secret");
 *
 *     &#64;Example
 *     void test() {
 *         assertTrue(MY_SQL_CONTAINER.isRunning());
 *         assertTrue(postgresqlContainer.isRunning());
 *     }
 * }
 * </pre>
 *
 * @see Container
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AddLifecycleHook(value = TestcontainersExtension.class, propagateTo = PropagationMode.ALL_DESCENDANTS)
@Inherited
public @interface Testcontainers {

	/**
	 * Whether tests should be disabled (rather than failing) when Docker is not available.
	 */
	boolean disabledWithoutDocker() default false;
}
