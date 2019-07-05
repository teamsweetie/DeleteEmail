import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class MailConfig {

	String host;
    String username;
    String password;
    String mailStoreType = "pop3";

    public MailConfig() {
    }

    public MailConfig(String host, String username, String password) {
 	   this.host = host;
 	   this.username = username;
 	   this.password = password;
    }

   public MailConfig(String[] args) {

	   String host;
	   String username;
	   String password;

	   //***Definition Stage***
	   // create Options object
	   Options options = new Options();

	   options.addOption("h", "host", true, "hostname of pop3 server");
	   options.addOption("u", "username", true, "username of pop3 account");
	   options.addOption("p", "password", true, "password pop3 account");

	   //***Parsing Stage***
	   //Create a parser
	   CommandLineParser parser = new DefaultParser();


	   //***Interrogation Stage***
	   CommandLine cmd;
	   try {
		   //parse the options passed as command line arguments
		   cmd = parser.parse( options, args);
		   if(cmd.hasOption("host") && cmd.hasOption("username") && cmd.hasOption("password")) { 
			   host = cmd.getOptionValue("host");
			   username = cmd.getOptionValue("username");
			   password = cmd.getOptionValue("password");
/*			   System.out.println("hostname: " + cmd.getOptionValue("host"));
			   System.out.println("username: " + cmd.getOptionValue("username"));
			   System.out.println("password: " + cmd.getOptionValue("password"));
*/			   this.host = host;
			   this.username = username;
			   this.password = password;
		   } 

	   } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }

   }
   
   public String toString() {
	   return getClass().getSimpleName() + "[hostname:" + host +", username:" + username +", password:" + password + ", mailStoreTypemailStoreType:" + mailStoreType + "]";	   
   }
}
