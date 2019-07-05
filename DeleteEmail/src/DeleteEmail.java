import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class DeleteEmail {

  
	public static void delete(MailConfig mail)
   {
	   
	   
      try
      {
         // get the session object
         Properties properties = new Properties();
         properties.put("mail.store.protocol", mail.mailStoreType);
         properties.put("mail.pop3s.host", mail.host);
         properties.put("mail.pop3s.port", "995");
         properties.put("mail.pop3.starttls.enable", "true");
         Session emailSession = Session.getDefaultInstance(properties);
         // emailSession.setDebug(true);

         // create the POP3 store object and connect with the pop server
         Store store = emailSession.getStore("pop3s");

         store.connect(mail.host, mail.username, mail.password);

         // create the folder object and open it
         Folder emailFolder = store.getFolder("INBOX");
         emailFolder.open(Folder.READ_WRITE);

//         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.getMessages();
         System.out.println("messages.length---" + messages.length);
/*         for (int i = 0; i < messages.length; i++) {
//         for (int i = 0; i < 3; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            System.out.println("Email Number " + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);

            String subject = message.getSubject();
//            System.out.print("Do you want to delete this message [y/n] ? ");
//            String ans = reader.readLine();
 //           if ("Y".equals(ans) || "y".equals(ans)) {
               // set the DELETE flag to true
               message.setFlag(Flags.Flag.DELETED, true);
               System.out.println("Marked DELETE for message: " + subject);
//            } else if ("n".equals(ans)) {
//             break;
 //           }
         }*/
         // expunges the folder to remove messages which are marked deleted
/*         The messages marked DELETED are not actually deleted, until we call the expunge() method on the Folder object, or close the folder with expunge set to true. For example:

        	 boolean expunge = true;
        	 folder.close(expunge);
        	       or:
        	 folder.expunge();
        	 folder.close(false);
*/        	  
//         emailFolder.expunge();  SAYS EXPUNGE NOT SUPPORTED
         emailFolder.close(true);
         store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      }
 //     } catch (IOException io) {
 //        io.printStackTrace();
 //     }
   }

 
   
	public static void main(String[] args) {

		MailConfig mail = new MailConfig(args);
		System.out.println(mail.toString());
		delete(mail);

	}

}