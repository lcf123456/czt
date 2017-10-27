/*
 * Copyright (c) 2015, All rights reserved.
 */
package com.ztel.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * String utility functions
 * @author Zeal
 * @date 2015-12-14
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * Generate UUID 
	 */
	public static String generateUUID() {
		return StringUtils.remove(UUID.randomUUID().toString(), '-');
	}
	
	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for {@code toString()} implementations.
	 * @param arr the array to display
	 * @param delim the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null || arr.length <= 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for {@code toString()} implementations.
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delimiter) {
		return collectionToDelimitedString(coll, delimiter, "", "");
	}	
	
	
	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for {@code toString()} implementations.
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @param prefix the String to start each element with
	 * @param suffix the String to end each element with
	 * @return the delimited String
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using {@code delimitedListToStringArray}
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static List<String> tokenizeToStringList(String str, String delimiters) {
		return tokenizeToStringList(str, delimiters, true, true);
	}
	
	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using {@code delimitedListToStringArray}
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's {@code trim}
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens ({@code null} if the input String
	 * was {@code null})
	 * @see java.util.StringTokenizer
	 * @see String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static List<String> tokenizeToStringList(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return new ArrayList<>(0);
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return tokens;
	}
	
	/**
	 * Camel string to delimeted string like underline '_'
	 * @param str
	 * @param seperator
	 * @return
	 */
	public static String camelToDelimitedString(String str, String seperator) {
		if (StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder((int)((double)str.length() * 1.5));
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(seperator);
                sb.append(Character.toLowerCase(c));
            }
            else{
                sb.append(c);
            }
        }
        return sb.toString();		
	}
	
	/**
	 * Convert to camel format
	 * @param str
	 * @param seperator like '_'
	 * @return
	 */
	public static String toCamelString(String str, char seperator) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		boolean toUppercase = false;
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			//Convert next char to uppercase
			if (c == seperator) {
				toUppercase = true;
				continue;
			}
			if (toUppercase) {
			    sb.append(Character.toUpperCase(c));
			    toUppercase = false;
			}
			else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static String urlEncode(String src) {
		return urlEncode(src, "UTF-8");
	}
	
	public static String urlEncode(String src, String charset) {
		try {
			return URLEncoder.encode(src, charset);
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return src;
		}
	}
	
	public static String getUriParameterString(Map<String,String> params, boolean urlEncode) {
		return getUriParameterString(params, false, urlEncode, "UTF-8");
	}
	
    public static String getUriParameterString(Map<String, String> params, boolean ignoreBlank, boolean urlEncode, String charset) {
		if (params == null || params.size() <= 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
		int count = 0;
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			Object valueObject = entry.getValue();
			String value = "";
			if (valueObject != null) {
				value = valueObject.toString();
			}
			if (ignoreBlank && StringUtils.isBlank(value)) {
				continue;
			}
			if (urlEncode) {
				value = urlEncode(value, charset);
			}
			
			if (count > 0) {
				sb.append('&');
			}
			sb.append(key).append('=').append(value);
			++count;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String str = "aaa123bbb";
		System.out.println(StringUtils.split(str, "123")[1]);
	}
	
}
