import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

        public static void main(String[] args) throws TwitterException {
        		TwitterAPI twitterAPI = new TwitterAPI();
                Window window = new Window(twitterAPI);
        }
}
