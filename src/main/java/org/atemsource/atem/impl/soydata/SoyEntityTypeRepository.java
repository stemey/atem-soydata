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
import org.atemsource.atem.api.type.EntityType;
import org.atemsource.atem.api.type.EntityTypeBuilder;
import org.atemsource.atem.impl.common.AbstractEntityType;
import org.atemsource.atem.impl.common.AbstractEntityTypeBuilder.EntityTypeBuilderCallback;
import org.atemsource.atem.impl.common.AbstractMetaDataRepository;
import org.atemsource.atem.spi.DynamicEntityTypeSubrepository;
import org.atemsource.atem.spi.EntityTypeCreationContext;
import org.springframework.beans.factory.annotation.Autowired;


public class SoyEntityTypeRepository extends AbstractMetaDataRepository<SoyMapData> implements
	DynamicEntityTypeSubrepository<SoyMapData>, EntityTypeBuilderCallback
{
	private SoyEntityTypeImpl arrayNodeType;

	@Autowired
	private BeanLocator beanLocator;

	private SoyEntityTypeImpl objectNodeType;

	public void afterFirstInitialization(EntityTypeRepository entityTypeRepositoryImpl)
	{
		// TODO Auto-generated method stub

	}

	public void afterInitialization()
	{
	}

	public EntityTypeBuilder createBuilder(String code)
	{
		SoyEntityTypeBuilder builder = beanLocator.getInstance(SoyEntityTypeBuilder.class);
		SoyEntityTypeImpl entityType = createEntityType(code);
		builder.setEntityType(entityType);
		builder.setEntityClass(SoyMapData.class);
		builder.setRepositoryCallback(this);
		return builder;
	}

	public SoyEntityTypeImpl createEntityType(String code)
	{
		final SoyEntityTypeImpl dynamicEntityTypeImpl = beanLocator.getInstance(SoyEntityTypeImpl.class);
		dynamicEntityTypeImpl.setCode(code);

		if (getEntityType(code) != null)
		{
			throw new IllegalArgumentException("dynamic type with name " + code + " already exists.");
		}
		AbstractEntityType x = dynamicEntityTypeImpl;
		this.nameToEntityTypes.put(code, x);
		return dynamicEntityTypeImpl;
	}

	public EntityType<SoyMapData> getEntityType(Object entity)
	{
		return null;
	}

	public void initialize(EntityTypeCreationContext entityTypeCreationContext)
	{
		this.entityTypeCreationContext = entityTypeCreationContext;
	}

	public void onFinished(AbstractEntityType<?> entityType)
	{
		attacheServicesToEntityType(entityType);
		((AbstractEntityType) entityType).initializeIncomingAssociations(entityTypeCreationContext);
		entityTypeCreationContext.lazilyInitialized(entityType);
	}

}
