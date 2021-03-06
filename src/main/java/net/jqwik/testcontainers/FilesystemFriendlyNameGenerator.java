package net.jqwik.testcontainers;

import java.io.*;
import java.net.*;

import net.jqwik.api.lifecycle.*;

import static java.nio.charset.StandardCharsets.*;
import static org.junit.platform.commons.util.StringUtils.*;

class FilesystemFriendlyNameGenerator {
	private static final String UNKNOWN_NAME = "unknown";

	static String filesystemFriendlyNameOf(LifecycleContext context) {
		String contextId = context.label();
		try {
			return (isBlank(contextId))
					? UNKNOWN_NAME
					: URLEncoder.encode(contextId, UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			return UNKNOWN_NAME;
		}
	}
}
