/*******************************************************************************
 * Stefan Meyer, 2012 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/
package org.atemsource.atem.impl.soydata.attribute;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.atemsource.atem.api.attribute.CollectionAttribute;
import org.atemsource.atem.api.attribute.CollectionSortType;
import org.atemsource.atem.api.attribute.OrderableCollection;
import org.atemsource.atem.api.type.Type;
import org.atemsource.atem.impl.common.attribute.AbstractAttribute;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class SoyListDataAttribute extends AbstractAttribute<Object, SoyListData> implements
	CollectionAttribute<Object, SoyListData>, OrderableCollection<Object, SoyListData>
{

	public void addElement(Object entity, int index, Object value)
	{
		SoyListData array = getValue(entity);
		array.add(index, SoyUtils.convertToSoy(value));
	}

	public void addElement(Object entity, Object element)
	{
		SoyListData arrayNode = getArrayNode(entity);
		arrayNode.add(SoyUtils.convertToSoy(element));
	}

	public void clear(Object entity)
	{
		SoyListData arrayNode = getArrayNode(entity);
		for (int index = 0; index < arrayNode.length(); index++)
		{
			arrayNode.remove(index);
		}
	}

	public boolean contains(Object entity, Object element)
	{
		throw new UnsupportedOperationException("notimplemented yet");
	}

	private SoyListData getArrayNode(Object entity)
	{
		return (SoyListData) ((SoyMapData) entity).get(getCode());
	}

	public Class<SoyListData> getAssociationType()
	{
		return SoyListData.class;
	}

	public CollectionSortType getCollectionSortType()
	{
		return CollectionSortType.ORDERABLE;
	}

	public Object getElement(Object entity, int index)
	{
		return null;
	}

	public Collection<Object> getElements(Object entity)
	{
		List<Object> collection = new ArrayList<Object>();
		SoyListData arrayNode = getArrayNode(entity);
		for (int index = 0; index < arrayNode.length(); index++)
		{
			collection.add(SoyUtils.convertToJava(arrayNode.get(index)));
		}
		return collection;
	}

	public SoyListData getEmptyCollection(Object entity)
	{
		return new SoyListData();
	}

	public int getIndex(Object entity, Object value)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterator<Object> getIterator(Object entity)
	{
		return getElements(entity).iterator();
	}

	public Class<SoyListData> getReturnType()
	{
		return getAssociationType();
	}

	public Serializable getSerializableValue(Object entity)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int getSize(Object entity)
	{
		return getArrayNode(entity).length();
	}

	public Type<Object> getTargetType(Object value)
	{
		return getTargetType();
	}

	public String getTypeCode()
	{
		return "arrayNode";
	}

	public SoyListData getValue(Object entity)
	{
		return getArrayNode(entity);
	}

	@Override
	public boolean isEqual(Object entity, Object other)
	{
		SoyListData valueA = getValue(entity);
		SoyListData valueB = getValue(other);
		if (valueA == null && valueB == null)
		{
			return true;
		}
		else if (valueA != null && valueB == null)
		{
			return false;
		}
		else if (valueA == null && valueB != null)
		{
			return false;
		}
		else if (valueA.length() != valueB.length())
		{
			return false;
		}
		else
		{
			for (int index = 0; index < valueA.length(); index++)
			{
				SoyMapData a = (SoyMapData) getElement(entity, index);
				SoyMapData b = (SoyMapData) getElement(other, index);
				if (!getTargetType().isEqual(a, b))
				{
					return false;
				}

			}
		}
		return true;

	}

	@Override
	public boolean isWriteable()
	{
		return true;
	}

	public void moveElement(Object entity, int fromIndex, int toIndex)
	{
		SoyListData value = getValue(entity);
		SoyData element = value.get(fromIndex);
		value.remove(fromIndex);
		value.set(toIndex, element);

	}

	public Object removeElement(Object entity, int index)
	{
		SoyData data = getValue(entity).get(index);
		getValue(entity).remove(index);
		return data;
	}

	public void removeElement(Object entity, Object element)
	{
		throw new UnsupportedOperationException("notimplemented yet");
	}

	@Override
	public void setValue(Object entity, SoyListData value)
	{
		SoyMapData node = (SoyMapData) entity;
		node.put(getCode(), value);

	}
}
