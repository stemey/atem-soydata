package org.atemsource.atem.impl.soydata;

import com.google.template.soy.data.SoyMapData;

import javax.annotation.Resource;
import javax.inject.Inject;

import junit.framework.Assert;

import org.atemsource.atem.api.EntityTypeRepository;
import org.atemsource.atem.api.type.EntityType;
import org.atemsource.atem.api.type.EntityTypeBuilder;
import org.atemsource.atem.spi.DynamicEntityTypeSubrepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = {"classpath:/test/atem/soydata/entitytype.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SoyEntityTypeBuilderTest
{

	@Inject
	private EntityTypeRepository entityTypeRepository;

	@Resource(name = "atem-soydata-repository")
	private DynamicEntityTypeSubrepository<SoyMapData> soydataRepository;

	private EntityType<SoyMapData> getType1()
	{
		return entityTypeRepository.getEntityType("test1");
	}

	private EntityType<SoyMapData> getType2()
	{
		return entityTypeRepository.getEntityType("test2");
	}

	@Before
	public void setup()
	{
		boolean initialized = entityTypeRepository.getEntityType("test1") != null;
		if (!initialized)
		{
			EntityTypeBuilder builder1 = soydataRepository.createBuilder("test1");
			builder1.addSingleAttribute("integer", entityTypeRepository.getType(Integer.class));
			builder1.createEntityType();

			EntityTypeBuilder builder2 = soydataRepository.createBuilder("test2");
			builder2.addSingleAssociationAttribute("association", getType1());
			builder2.createEntityType();
		}
	}

	@Test
	public void testAssociation()
	{
		SoyMapData ass = new SoyMapData();
		SoyMapData soyMapData = new SoyMapData();
		getType2().getAttribute("association").setValue(soyMapData, ass);
		Assert.assertEquals(soyMapData.getMapData("association"), ass);
	}

	@Test
	public void testInteger()
	{
		SoyMapData soyMapData = new SoyMapData();
		getType1().getAttribute("integer").setValue(soyMapData, 100);
		Assert.assertEquals(100, soyMapData.getInteger("integer"));
	}
}
