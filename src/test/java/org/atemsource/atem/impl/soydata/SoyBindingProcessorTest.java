package org.atemsource.atem.impl.soydata;

import com.google.template.soy.data.SoyMapData;

import javax.inject.Inject;

import junit.framework.Assert;

import org.atemsource.atem.api.EntityTypeRepository;
import org.atemsource.atem.api.type.EntityType;
import org.atemsource.atem.impl.soydata.entities.EntityA;
import org.atemsource.atem.impl.soydata.entities.EntityB;
import org.atemsource.atem.utility.transform.api.DerivedType;
import org.atemsource.atem.utility.transform.api.SimpleTransformationContext;
import org.atemsource.atem.utility.transform.api.UniTransformation;
import org.atemsource.atem.utility.transform.impl.DerivationMetaAttributeRegistrar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = {"classpath:/test/atem/soydata/transform.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SoyBindingProcessorTest
{

	@Inject
	private EntityTypeRepository entityTypeRepository;

	@Test
	public void test()
	{
		EntityType<SoyMapData> soyType = entityTypeRepository.getEntityType("soy:" + EntityA.class.getName());
		DerivedType derivedType =
			(DerivedType) entityTypeRepository.getEntityType(EntityType.class)
				.getMetaAttribute(DerivationMetaAttributeRegistrar.DERIVED_FROM).getValue(soyType);
		EntityA a = new EntityA();
		a.setB(new EntityB());
		a.getB().setInteger(100);
		UniTransformation<Object, SoyMapData> transformation =
			(UniTransformation<Object, SoyMapData>) derivedType.getTransformation().getAB();
		SoyMapData soy = transformation.convert(a, new SimpleTransformationContext());
		Assert.assertEquals(100, soy.getMapData("b").getInteger("integer"));

	}
}
