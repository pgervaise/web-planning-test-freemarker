package fr.penelope.planning.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import fr.penelope.util.Environment;
import fr.penelope.util.FwkUtils;

/**
 * Pour démarrer l'application via un serveur web
 * @author Philippe Gervaise
 *
 */
public class WebPlanningApplicationWebXml extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(WebPlanningApplicationWebXml.class);

    public WebPlanningApplicationWebXml() {
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	
    	return builder.profiles(addDefaultProfile())
    		.bannerMode(Banner.Mode.OFF)
    		.sources(WebPlanningApplication.class);
    }

    /**
     * Profile par défaut
     * @return
     */
    private String addDefaultProfile() {
    	FwkUtils.setEnvironmentWithSpringProfile();

        String profile = System.getProperty("spring.profiles.active");

        if (profile != null) {
        	log.info("Démarrage avec les profils Spring : {}", profile);
        }

        logger.info("Environnement: " + FwkUtils.getEnvironment().getFullName() + " (" + FwkUtils.getEnvironment().toString().toLowerCase() + ")");

        Environment env = FwkUtils.getEnvironment();
        return env.toString().toLowerCase();
    }
}