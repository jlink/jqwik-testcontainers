package net.jqwik.testcontainers;

import java.util.*;

import org.testcontainers.lifecycle.*;

public class TestLifecycleAwareContainerMock implements Startable, TestLifecycleAware {

	static final String BEFORE_TEST = "beforeTest";
	static final String AFTER_TEST = "afterTest";

	private final List<String> lifecycleMethodCalls = new ArrayList<>();
	private final List<String> lifecycleFilesystemFriendlyNames = new ArrayList<>();

	private Throwable capturedThrowable;

	@Override
	public void beforeTest(TestDescription description) {
		lifecycleMethodCalls.add(BEFORE_TEST);
		lifecycleFilesystemFriendlyNames.add(description.getFilesystemFriendlyName());
	}

	@Override
	public void afterTest(TestDescription description, Optional<Throwable> throwable) {
		lifecycleMethodCalls.add(AFTER_TEST);
		throwable.ifPresent(capturedThrowable -> this.capturedThrowable = capturedThrowable);
	}

	List<String> getLifecycleMethodCalls() {
		return lifecycleMethodCalls;
	}

	Throwable getCapturedThrowable() {
		return capturedThrowable;
	}

	public List<String> getLifecycleFilesystemFriendlyNames() {
		return lifecycleFilesystemFriendlyNames;
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}
}
