package fr.penelope.planning.web.config.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 
 * @author Philippe Gervaise
 *
 */
public class PlanningFreeMarkerViewResolver extends FreeMarkerViewResolver {

	@Override
	protected Class<?> getViewClass() {
		return PlanningFreeMarkerView.class;
	}
}
