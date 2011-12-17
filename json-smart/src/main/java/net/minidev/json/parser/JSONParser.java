package net.minidev.json.parser;

/*
 *    Copyright 2011 JSON-SMART authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.Reader;

public class JSONParser {
	/**
	 * allow simple quote as String quoting char
	 */
	public final static int ACCEPT_SIMPLE_QUOTE = 1;
	/**
	 * allow non quoted test
	 */
	public final static int ACCEPT_NON_QUOTE = 2;
	/**
	 * Parse NaN as Float.NaN
	 */
	public final static int ACCEPT_NAN = 4;
	/**
	 * Ignore control char in input text.
	 */
	public final static int IGNORE_CONTROL_CHAR = 8;
	/**
	 * Use int datatype to store number when it's possible.
	 * 
	 * @since 1.0.7
	 */
	public final static int USE_INTEGER_STORAGE = 16;
	/**
	 * Throws exception on excessive 0 leading in digits
	 * 
	 * @since 1.0.7
	 */
	public final static int ACCEPT_LEADING_ZERO = 32;
	/**
	 * Throws exception on useless comma in object and array
	 * 
	 * @since 1.0.8
	 */
	public final static int ACCEPT_USELESS_COMMA = 64;
	/**
	 * Allow Json-smart to use Double or BigDecimal to store floating point
	 * value
	 * 
	 * You may need to disable HI_PRECISION_FLOAT feature on 32bit to improve
	 * parsing performances.
	 * 
	 * @since 1.0.9
	 */
	public final static int USE_HI_PRECISION_FLOAT = 128;
	/**
	 * If enabled json-smart will throws exception if datas are present after
	 * the end of the Json data.
	 * 
	 * @since 1.0.9-2
	 */
	public final static int REJECT_TAILLING_DATA = 256;
	/**
	 * smart mode, fastest parsing mode. accept lots of non standard json syntax
	 * 
	 * @since 1.0.6
	 */
	public final static int MODE_PERMISSIVE = -1;
	/**
	 * strict RFC4627 mode.
	 * 
	 * slower than PERMISIF MODE.
	 * 
	 * @since 1.0.6
	 */
	public final static int MODE_RFC4627 = USE_INTEGER_STORAGE | USE_HI_PRECISION_FLOAT;
	/**
	 * Parse Object like json-simple
	 * 
	 * Best for an iso-bug json-simple API port.
	 * 
	 * @since 1.0.7
	 */
	public final static int MODE_JSON_SIMPLE = ACCEPT_USELESS_COMMA | USE_HI_PRECISION_FLOAT;
	/**
	 * Strictest parsing mode
	 * 
	 * @since 1.0.9-2
	 */
	public final static int MODE_STRICTEST = USE_INTEGER_STORAGE | USE_HI_PRECISION_FLOAT | REJECT_TAILLING_DATA;
	
	/**
	 * Default json-smart processing mode
	 */
	public static int DEFAULT_PERMISSIVE_MODE = (System.getProperty("JSON_SMART_SIMPLE") != null) ? MODE_JSON_SIMPLE
			: MODE_PERMISSIVE;

	/*
	 * internal fields
	 */
	private int mode;
	private JSONStreamParser pStream;
	private JSONStringParser pString;

	/**
	 * @deprecated prefer usage of new JSONParser(JSONParser.MODE_*)
	 */
	public JSONParser() {
		this.mode = DEFAULT_PERMISSIVE_MODE;
	}

	public JSONParser(int permissifMode) {
		this.mode = permissifMode;
	}

	/**
	 * use to return Primitive Type, or String, Or JsonObject or JsonArray
	 * generated by a ContainerFactory
	 */
	public Object parse(String in) throws ParseException {
		if (pString == null)
			pString = new JSONStringParser(mode);
		return pString.parse(in);
	}

	/**
	 * use to return Primitive Type, or String, Or JsonObject or JsonArray
	 * generated by a ContainerFactory
	 */
	public Object parse(String in, ContainerFactory containerFactory) throws ParseException {
		if (pString == null)
			pString = new JSONStringParser(mode);
		return pString.parse(in, containerFactory);
	}

	public Object parse(String in, ContainerFactory containerFactory, ContentHandler handler) throws ParseException {
		if (pString == null)
			pString = new JSONStringParser(mode);
		return pString.parse(in, containerFactory, handler);
	}

	/**
	 * use to return Primitive Type, or String, Or JsonObject or JsonArray
	 * generated by a ContainerFactory
	 */
	public Object parse(Reader in) throws ParseException {
		if (pStream == null)
			pStream = new JSONStreamParser(mode);
		return pStream.parse(in);
	}

	/**
	 * use to return Primitive Type, or String, Or JsonObject or JsonArray
	 * generated by a ContainerFactory
	 */
	public Object parse(Reader in, ContainerFactory containerFactory) throws ParseException {
		if (pStream == null)
			pStream = new JSONStreamParser(mode);
		return pStream.parse(in, containerFactory);
	}

	/**
	 * use to return Primitive Type, or String, Or JsonObject or JsonArray
	 * generated by a ContainerFactory
	 */
	public Object parse(Reader in, ContainerFactory containerFactory, ContentHandler handler) throws ParseException {
		if (pStream == null)
			pStream = new JSONStreamParser(mode);
		return pStream.parse(in, containerFactory);
	}
}
