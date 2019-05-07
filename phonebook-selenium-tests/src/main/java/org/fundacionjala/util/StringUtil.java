package org.fundacionjala.util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

/** This utility class manage string endpoints. **/
public final class StringUtil {

    /** Default constructor. **/
    private StringUtil() {
    }

    /**
     * This method build explicit endpoint from a format url.
     *
     * @param bareUrl url format.
     * @return string specific url.
     */
    public static String getExplicitEndpoint(final String bareUrl) {
        final String baseUrl = (String) ScenarioContext
                .getContextAsString("url.api");
        if (bareUrl.contains("{")) {
            final StringBuilder result = new StringBuilder();
            result.append(baseUrl);
            for (final String part : bareUrl.split("\\{")) {
                if (part.contains("}")) {
                    final String key = getKey(part, '}');
                    final String value = getValue(key);
                    final String restPart = getRestPart(part, '}');
                    result.append(value).append(restPart);
                } else {
                    result.append(part);
                }
            }
            return result.toString();
        }
        return baseUrl.concat(bareUrl);
    }

    /**
     * This method get a substring from a line until keyLimit.
     *
     * @param line     string
     * @param keyLimit char limit of substring.
     * @return substring from begining to keylimit
     */
    public static String getKey(final String line, final char keyLimit) {
        final int index = line.indexOf(keyLimit);
        return line.substring(0, index);
    }

    /**
     * This method get the value associated with key in the ScenarioContext.
     *
     * @param key string key.
     * @return value in the context for key.
     */
    public static String getValue(final String key) {
        if (key.contains(".")) {
            final String mainKey = getKey(key, '.');
            final JsonPath value = ((Response) ScenarioContext.getInstance()
                    .getContext(mainKey)).body().jsonPath();
            final String subKey = getRestPart(key, '.');
            return value.get(subKey).toString();
        }
        return String.valueOf(ScenarioContext.getContextAsString(key));
    }

    /**
     * This method get a substring from keyLimit to end of the line string.
     *
     * @param line     string.
     * @param keyLimit char limit of substring.
     * @return substring from keylimit to end.
     */
    private static String getRestPart(final String line, final char keyLimit) {
        final int index = line.indexOf(keyLimit);
        return index == line.length() - 1 ? StringUtils.EMPTY
                : line.substring(index + 1);
    }
}
