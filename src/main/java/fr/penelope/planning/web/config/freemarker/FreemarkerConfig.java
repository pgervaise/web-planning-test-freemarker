package fr.penelope.planning.web.config.freemarker;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import fr.penelope.planning.web.service.FreemarkerTemplateService;

/**
 * Classe pour le chargement du fichier security.tld avec Freemarker (TagLib)
 * Permet de le support de spring security dans les templates freemarker
 * 
 * @author Philippe Gervaise
 *
 */
@Configuration
public class FreemarkerConfig {

	@Autowired
	protected FreeMarkerProperties properties;
	
	@Autowired
	protected FreemarkerTemplateService freemarkerTemplateService;

	public FreemarkerConfig() {
	}
	
	@Bean(name="freeMarkerViewResolver")
	public FreeMarkerViewResolver getFreeMarkerViewResolver() {
		FreeMarkerViewResolver freeMarkerViewResolver = new PlanningFreeMarkerViewResolver();
		
		this.properties.applyToMvcViewResolver(freeMarkerViewResolver);

		Map<String, Object> attributesMap = new HashMap<>();
		attributesMap.put("freemarkerTemplateService", freemarkerTemplateService);
		freeMarkerViewResolver.setAttributesMap(attributesMap);

		return freeMarkerViewResolver;
	}
}
