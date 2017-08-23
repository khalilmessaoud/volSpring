package vol.metier.dao.impl;
import java.util.HashMap;
import java.util.Map;
import vol.metier.model.Login;
import javax.servlet.http.HttpServletRequest;

public class ConnexionClient {

	    private static final String CHAMP_LOGIN  = "login";
	    private static final String CHAMP_PASS   = "motdepasse";

	    private String              resultat;
	    private Map<String, String> erreurs      = new HashMap<String, String>();

	    public String getResultat() {
	        return resultat;
	    }

	    public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public Login connecterUtilisateur( HttpServletRequest request ) {
	        /* Récupération des champs du formulaire */
	        String login = getValeurChamp( request, CHAMP_LOGIN );
	        String motDePasse = getValeurChamp( request, CHAMP_PASS );

	        Login Login= new Login();

	        /* Validation du champ login. */
	        try {
	            validationLogin( Login );
	        } catch ( Exception e ) {
	            setErreur( CHAMP_LOGIN, e.getMessage() );
	        }
	        
	        Login.setLogin(login);

	        /* Validation du champ mot de passe. */
	        try {
	            validationMotDePasse( motDePasse );
	        } catch ( Exception e ) {
	            setErreur( CHAMP_PASS, e.getMessage() );
	        }
	        Login.setMotDePasse(motDePasse);

	        /* Initialisation du résultat global de la validation. */
	        if ( erreurs.isEmpty() ) {
	            resultat = "Succès de la connexion.";
	        } else {
	            resultat = "Échec de la connexion.";
	        }

	        return Login;
	    }

	    /**
	     * Valide login saisie.
	     */
	    private void validationLogin( Login login) throws Exception {
	        if ( login != null  )  {
	            throw new Exception( "Merci de saisir une login valide." );
	        }
	    }

	    /**
	     * Valide le mot de passe saisi.
	     */
	    private void validationMotDePasse( String motDePasse ) throws Exception {
	        if ( motDePasse != null ) {
	            if ( motDePasse.length() < 3 ) {
	                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
	            }
	        } else {
	            throw new Exception( "Merci de saisir votre mot de passe." );
	        }
	    }

	    /*
	     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	     */
	    private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }

	    /*
	     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	     * sinon.
	     */
	    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }
	}