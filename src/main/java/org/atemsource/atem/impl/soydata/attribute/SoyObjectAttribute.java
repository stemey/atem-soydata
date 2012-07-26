/*******************************************************************************
 * Stefan Meyer, 2012 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/
package org.atemsource.atem.impl.soydata.attribute;

import com.google.template.soy.data.SoyMapData;

import org.atemsource.atem.impl.common.attribute.AbstractSingleAssociationAttribute;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class SoyObjectAttribute extends AbstractSingleAssociationAttribute<SoyMapData>
{
	@Override
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

	@Override
	public void setValue(Object entity, SoyMapData value)
	{
		SoyMapData node = (SoyMapData) entity;
		if (node == null)
		{
			throw new NullPointerException("entity is null");
		}
		node.put(getCode(), value);
	}
}
