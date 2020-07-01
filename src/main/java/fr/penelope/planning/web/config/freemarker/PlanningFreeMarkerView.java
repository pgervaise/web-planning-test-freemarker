package fr.penelope.planning.web.config.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;

/**
 * 
 * @author Philippe Gervaise
 *
 */
public class PlanningFreeMarkerView extends FreeMarkerView {

	public PlanningFreeMarkerView() {
	}
	
	@Override
	protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		SimpleHash fmModel = super.buildTemplateModel(model, request, response);
		BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.getVersion()).build();
		fmModel.put("statics", wrapper.getStaticModels());
		
		return fmModel;
	}
}
