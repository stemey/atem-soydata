/*******************************************************************************
 * Stefan Meyer, 2012 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 ******************************************************************************/
package org.atemsource.atem.impl.soydata;

import com.google.template.soy.data.SoyMapData;

import org.atemsource.atem.api.BeanLocator;
import org.atemsource.atem.api.EntityTypeRepository;
import org.atemsource.atem.api.infrastructure.exception.TechnicalException;
import org.atemsource.atem.impl.common.AbstractEntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class SoyEntityTypeImpl extends AbstractEntityType<SoyMapData>
{

	@Autowired
	private BeanLocator beanLocator;

	@Autowired
	private EntityTypeRepository entityTypeRepository;

	public SoyEntityTypeImpl()
	{
		super();
		setEntityClass(SoyMapData.class);
	}

	@Override
	public SoyMapData createEntity() throws TechnicalException
	{
		return new SoyMapData();
	}

	public Class<SoyMapData> getJavaType()
	{
		return SoyMapData.class;
	}

	public boolean isAssignableFrom(Object entity)
	{
		if (entity == null)
		{
			return false;
		}
		else if (entity instanceof SoyMapData)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isPersistable()
	{
		return true;
	}
}
