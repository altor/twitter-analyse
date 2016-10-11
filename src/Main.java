import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

        public static void main(String[] args) throws TwitterException {
        		TwitterAPI twitterAPI = new TwitterAPI();
                
        		TweetsTableModel tableTweets = new TweetsTableModel();
        		
        		
        		// this.setModel(new TweetsTableModel(list));
        		
        		// on cree la table de tweet
        		// on la passe a windows (elle est encore vide)
        		Window window = new Window(twitterAPI, tableTweets);
        		// la elle est pleine 
        		
                
                
        }
}
