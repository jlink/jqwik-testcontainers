package net.jqwik.testcontainers.inheritance;

import net.jqwik.api.Example;
import net.jqwik.testcontainers.Container;

import static org.assertj.core.api.Assertions.assertThat;

class InheritedTests extends AbstractTestBase {

    @Container
    private RedisContainer myRedis = new RedisContainer();

    @Example
    void step1() {
        assertThat(redisPerClass.getJedis().incr("key").longValue()).isEqualTo(1);
        assertThat(redisPerTest.getJedis().incr("key").longValue()).isEqualTo(1);
        assertThat(myRedis.getJedis().incr("key").longValue()).isEqualTo(1);
    }

    @Example
    void step2() {
        assertThat(redisPerClass.getJedis().incr("key").longValue()).isEqualTo(2);
        assertThat(redisPerTest.getJedis().incr("key").longValue()).isEqualTo(1);
        assertThat(myRedis.getJedis().incr("key").longValue()).isEqualTo(1);
    }
}
