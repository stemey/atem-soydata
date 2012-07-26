package org.atemsource.atem.impl.soydata.attribute;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.atemsource.atem.api.EntityTypeRepository;
import org.atemsource.atem.api.attribute.CollectionSortType;
import org.atemsource.atem.api.attribute.MapAttribute;
import org.atemsource.atem.api.type.Type;
import org.atemsource.atem.impl.common.attribute.AbstractAttribute;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractMapNodeAttribute<V> extends AbstractAttribute<V, SoyMapData> implements
	MapAttribute<String, V, SoyMapData>
{

	@Autowired
	protected EntityTypeRepository entityTypeRepository;

	public AbstractMapNodeAttribute()
	{
		super();
	}

	public void clear(Object entity)
	{
		SoyMapData data = (SoyMapData) entity;
		for (String key : data.getKeys())
		{
			data.remove(key);
		}
	}

	public boolean containsValue(Object entity, V element)
	{
		throw new UnsupportedOperationException("not implemented yet");
	}

	public Class<SoyMapData> getAssociationType()
	{
		return SoyMapData.class;
	}

	public CollectionSortType getCollectionSortType()
	{
		return CollectionSortType.NONE;
	}

	public V getElement(Object entity, String keye)
	{

		return (V) SoyUtils.convertToJava(getValue(entity).get(keye));
	}

	public SoyMapData getEmptyMap()
	{
		return new SoyMapData();
	}

	public Iterator<Entry<?, ?>> getIterator(Object entity)
	{
		Map<String, V> elements = new HashMap<String, V>();
		Iterator<Map.Entry<String, SoyData>> fields = getValue(entity).asMap().entrySet().iterator();
		for (; fields.hasNext();)
		{
			Entry<String, SoyData> next = fields.next();
			elements.put(next.getKey(), (V) next.getValue());
		}

		Map untypedMap = elements;
		return untypedMap.entrySet().iterator();
	}

	public Set<String> getKeySet(Object entity)
	{
		SoyMapData node = (SoyMapData) entity;
		Set<String> fields = new HashSet<String>();
		for (Iterator<String> i = node.getKeys().iterator(); i.hasNext();)
		{
			fields.add(i.next());
		}
		return fields;
	}

	public Type<String> getKeyType()
	{
		return entityTypeRepository.getType(String.class);
	}

	public Type<String> getKeyType(String key)
	{
		return entityTypeRepository.getType(String.class);
	}

	public Class<SoyMapData> getReturnType()
	{
		return getAssociationType();
	}

	public int getSize(Object entity)
	{
		return getValue(entity).getKeys().size();
	}

	public Type<V> getTargetType(V value)
	{
		return entityTypeRepository.getType(value);
	}

	public SoyMapData getValue(Object entity)
	{
		SoyMapData node = (SoyMapData) entity;
		if (node == null)
		{
			throw new NullPointerException("entity is null");
		}
		else
		{
			return (SoyMapData) node.get(getCode());
		}
	}

	@Override
	public boolean isWriteable()
	{
		return true;
	}

	public void putElement(Object entity, String key, V value)
	{
		getValue(entity).put(key, SoyUtils.convertToSoy(value));
	}

	public void removeKey(Object entity, String key)
	{
		getValue(entity).remove(key);
	}

	public void removeValue(Object entity, V value)
	{
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public void setValue(Object entity, SoyMapData value)
	{
		SoyMapData node = (SoyMapData) entity;
		if (node == null)
		{
			throw new NullPointerException("entity is null");
		}
		else
		{
			node.put(getCode(), value);
		}
	}

}