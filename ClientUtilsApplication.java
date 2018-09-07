package com.sophos.nsg.jtaf.clientutils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class for Client Utils (Spring boot application).
 */
@SpringBootApplication
public class ClientUtilsApplication
{
    public static final String LOGFILE = "/var/log/client-utils.log";

    String classDetails;

    ClientUtilsApplication()
    {
        classDetails = "Client Utils Spring boot application.";
    }

    /**
     * Main method for running Client Utils.
     *
     * @param args arguments to be passed to main method
     */
    public static void main(final String[] args)
    {
        SpringApplication.run(ClientUtilsApplication.class, args);
    }

    /**
     * Output provided message to logfile
     *
     * @param message Message to log
     * @param debug Log message to stdout
     * @throws IOException if an I/O error occurs
     */
    public static void log(String message, Boolean debug) throws IOException
    {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        BufferedWriter log = new BufferedWriter(new FileWriter(LOGFILE, true));

        log.append(time + " " + message + System.lineSeparator());
        log.close();

        if (debug)
        {
            System.out.println("[DEBUG] " + time + " " + message);
        }
    }
}
