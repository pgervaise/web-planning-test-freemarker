package fr.penelope.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.penelope.config.Constants;


/**
 * 
 * @author Philippe Gervaise
 *
 */
public class FwkUtils {

	private static final Logger logger = LoggerFactory.getLogger(FwkUtils.class);

	public static Environment environment = null;

	/**
	 * Détermine s'il s'agit de l'environnement de DEV
	 * @return
	 */
	public static boolean isDev() {
		return getEnvironment().isDev();
	}

	/**
	 * Détermine s'il s'agitde l'environnement de TEST
	 * @return
	 */
	public static boolean isTest() {
		return getEnvironment().isTest();
	}

	/**
	 * Détermine s'il s'agit de l'environnement de PRODUCTION
	 * @return
	 */
	public static boolean isProd() {
		return getEnvironment().isProduction();
	}

	/**
	 * Détermine s'il s'agit de l'environnement de PRE PRODUCTION
	 * @return
	 */
	public static boolean isPreProd() {
		return getEnvironment().isPreProduction();
	}

	/**
	 * Détermine s'il s'agit de l'environnement de RECETTE
	 * @return
	 */
	public static boolean isRecette() {
		return getEnvironment().isRecette();
	}

	/**
	 * Détermine si le point d'entrée des applications est unique ou
	 * multiplié par le nombre de sites
	 * @return
	 */
	public static boolean hasUniqueEntryPoint() {
		if (isProd() || isPreProd())
			return false;

		return true;
	}

	/**
	 * 
	 * @param key
	 */
	public static void getProperty(String key) {
		
	}

	/**
	 * Vérification de l'existence d'un répertoire
	 * @param dir
	 * @param canCreate
	 * @return TRUE si le répertoire est exploitable 
	 */
	public static boolean checkDir(File dir, boolean canCreate) {
		if (dir.exists()) {
			if (!dir.isDirectory())
				return false;
			return true;
		}

		return canCreate ? dir.mkdirs() : false;
	}

	/**
	 * Retourne l'environnement en cours
	 * @return
	 */
	public static Environment getEnvironment() {
		if (environment == null) {
			String env = System.getProperty(Constants.ENVIRONMENT_VAR_NAME);

			if (env == null)
				return Environment.DEV;
	
			env = env.toUpperCase();
	
			environment = Environment.valueOf(env);

			logger.debug("Environnement : " + environment);
		}

		return environment;
	}

	/**
	 * Positionne l'environnement selon les profils Spring (via
	 * la propriété "spring.profiles.active"
	 * @return Environnement positionné par la propriété globale 
	 */
	public static Environment setEnvironmentWithSpringProfile() {
		return setEnvironmentWithSpringProfile(System.getProperty("spring.profiles.active"));
	}

	/**
	 * Positionne l'environnement selon les profils Spring
	 * @param profile Profils spring séparés par des ","
	 * @return Environnement positionné par la propriété globale 
	 */
	public static Environment setEnvironmentWithSpringProfile(String profiles) {
		String envProperty = System.getProperty(Constants.ENVIRONMENT_VAR_NAME);

		if (profiles == null) {
			// Pas de profile spring, on se base sur la variable d'environnement classique
			return getEnvironment();
		}

        String[] profileArray = profiles.split(",", -1);
    	Environment env = null;
        for (String profile : profileArray) {
        	profile = profile == null ? "" : profile.trim().toUpperCase();

        	try {
        		env = Environment.valueOf(profile);

            	if (env != null)
                	System.setProperty(Constants.ENVIRONMENT_VAR_NAME, env.toString().toLowerCase());
        	} catch (IllegalArgumentException e) {
        		// environnement non existant, rien à faire
        	}
        }
        
        if (env == null) {
			// Pas de profile spring correspondant à un environnement, on se base sur la variable
        	// d'environnement classique qui donnera un env de dev
			return getEnvironment();
        }

        // Force la prochaine détection de l'environnement avec getEnvironment()
        environment = null;

        return env;
	}

	/**
	 * Retourne l'environnement en cours en tant que chaine (minuscule)
	 * (note: écrit pour Spring 3.1.0)
	 * @return
	 */
	public static String getEnvironmentAsString() {
		return getEnvironment().toString();
	}
}