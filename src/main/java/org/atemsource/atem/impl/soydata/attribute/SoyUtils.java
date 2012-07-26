package org.atemsource.atem.impl.soydata.attribute;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.BooleanData;
import com.google.template.soy.data.restricted.FloatData;
import com.google.template.soy.data.restricted.IntegerData;
import com.google.template.soy.data.restricted.NullData;
import com.google.template.soy.data.restricted.StringData;
import com.google.template.soy.data.restricted.UndefinedData;


public class SoyUtils
{

	public static Object convertToJava(SoyData soyData)
	{
		if (soyData instanceof BooleanData)
		{
			return soyData.booleanValue();
		}
		else if (soyData instanceof IntegerData)
		{
			return soyData.integerValue();
		}
		else if (soyData instanceof FloatData)
		{
			return soyData.floatValue();
		}
		else if (soyData instanceof StringData)
		{
			return soyData.stringValue();
		}
		else if (soyData instanceof NullData)
		{
			return null;
		}
		else if (soyData instanceof UndefinedData)
		{
			return null;
		}
		else
		{
			return soyData;
		}

	}

	public static SoyData convertToSoy(Object value)
	{
		if (value == null)
		{
			return NullData.INSTANCE;
		}
		if (value instanceof SoyData)
		{
			return (SoyData) value;
		}
		else if (value instanceof Boolean)
		{
			return BooleanData.forValue((Boolean) value);
		}
		else if (value instanceof Integer)
		{
			return IntegerData.forValue((Integer) value);
		}
		else if (value instanceof Double)
		{
			return FloatData.forValue((Double) value);
		}
		else if (value instanceof String)
		{
			return StringData.forValue((String) value);
		}
		else
		{
			throw new UnsupportedOperationException("not supported yet " + value);
		}
	}

}
