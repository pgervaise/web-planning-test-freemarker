package fr.penelope.planning.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import freemarker.ext.servlet.HttpRequestHashModel;

/**
 * Un service mis à disposition des templates Freemarker
 * @author Philippe Gervaise
 *
 */
@Service
public class FreemarkerTemplateService {

	public final static Logger logger = LoggerFactory.getLogger(FreemarkerTemplateService.class);

	public FreemarkerTemplateService() {
	}

	/**
	 * Echappement du HTML y compris les accents
	 * @param html
	 * @param httpRequestHashModel
	 * @return
	 */
	public String escapeHtml(String html, HttpRequestHashModel httpRequestHashModel) {
		return html;
	}
}
