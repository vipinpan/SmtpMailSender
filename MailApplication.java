package **packagename** // provide the package name under which the class file is created

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class for MailApplication (Spring boot application).
 */
@SpringBootApplication
public class MailApplication
{
    

    String classDetails;

    MailApplication()
    {
        classDetails = "Mail Spring boot application.";
    }

    /**
     * Main method for Mail Application.
     *
     * @param args arguments to be passed to main method
     */
    public static void main(final String[] args)
    {
        SpringApplication.run(MailApplication.class, args);
    }

    
}
