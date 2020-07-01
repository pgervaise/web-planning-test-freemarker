package fr.penelope.util;

/**
 * Un environnement
 * @author Philippe Gervaise
 *
 */
public enum Environment {
	LOCAL("Local"), // inutilisé
	DEV("Développement"),
	TEST("Test"),
	RECETTE("Recette"),
	PREPROD("Pré-production"),
	PROD("Production");

	private String fullName;

	private Environment(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 
	 * @return
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isLocal() {
		return this.equals(LOCAL);
	}

	/**
	 * S'agit t'-il d'un environnement de développement ?
	 * @return
	 */
	public boolean isDev() {
		return this.equals(DEV);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isTest() {
		return this.equals(TEST);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRecette() {
		return this.equals(RECETTE);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPreProduction() {
		return this.equals(PREPROD);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isProduction() {
		return this.equals(PROD);
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
