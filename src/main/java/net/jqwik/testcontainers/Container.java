package net.jqwik.testcontainers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code @Container} annotation is used in conjunction with the {@link Testcontainers} annotation
 * to mark test containers that should be managed by the Testcontainers extension.
 *
 * @see Testcontainers
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Container {

    /**
     * Whether a container declared as instance field should be restarted between each try
     * (test runs with different parameters) rather than between each property.
     */
    boolean restartPerTry() default false;
}
