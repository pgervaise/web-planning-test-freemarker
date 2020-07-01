package fr.penelope.planning.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import fr.penelope.util.FwkUtils;

/**
 * 
 * @author Philippe Gervaise
 * @see fr.penelope.planning.config
 */
@SpringBootApplication(
	exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class },
	scanBasePackages = {
		"fr.penelope.planning.web",
		"fr.penelope.planning.dao",
		"fr.penelope.planning.service"
	})
@EnableScheduling
@EnableCaching
public class WebPlanningApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebPlanningApplication.class);

    // @Autowired
    // private Environment env;

    /**
     * Pour lancer Spring Boot sans serveur
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
    	SpringApplication app = new SpringApplication(WebPlanningApplication.class);
    	app.setBannerMode(Banner.Mode.OFF);

    	SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
    	addDefaultProfile(app, source);

    	Environment env = app.run(args).getEnvironment();

        logger.info("Access URLs:\n----------------------------------------------------------\n\t" +
	        "Local: \t\thttp://127.0.0.1:{}\n\t" +
	        "External: \thttp://{}:{}\n----------------------------------------------------------",
	        env.getProperty("server.port"),
	        InetAddress.getLocalHost().getHostAddress(),
	        env.getProperty("server.port"));
    }

    /**
     * Set a default profile if it has not been set
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
    	FwkUtils.setEnvironmentWithSpringProfile(source.getProperty("spring.profiles.active"));
    	app.setAdditionalProfiles(FwkUtils.getEnvironment().toString().toLowerCase());

        logger.info("Environnement: " + FwkUtils.getEnvironment().getFullName() + " (" + FwkUtils.getEnvironment().toString().toLowerCase() + ")");
   }
}