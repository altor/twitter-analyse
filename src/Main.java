import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

	public static void main(String[] args) throws TwitterException {

		// lancement de L'API Twitter (conexion)
		TwitterAPI twitterAPI = new TwitterAPI();

		// Création du model qui va contenir les Tweets
		TweetsTableController tableTweets = new TweetsTableController();

		// Création de la fenêtre d'interface
		Window window = new Window(twitterAPI, tableTweets);
	}
}
