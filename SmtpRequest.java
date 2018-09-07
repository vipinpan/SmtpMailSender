import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

/**
 * 
 * This class is used to
 * - serialize / deserialize JSON request params.
 * - serialize / deserialize JSON response object.
 * - Make SMTP request from client using given params.
 *
 */
public class SmtpRequest
{

    String _username;
    String _host;
    String _password;
    String _recipientMailId;
    String _mailSubject;
    String _mailBody;

    int _responseCode;
    String _responseMessage;

    String _attachFiles;

    /**
     * default constructor.
     */
    public SmtpRequest()
    {
        // do nothing
    }

    /**
     * Constructor for SmtpRequest.
     * 
     * @param username make Smtp request.
     * @param host make request to given host.
     * @param password to be used for login.
     * @param recipientMailId to send mail to.
     * @param mailSubject subject of mail.
     * @param mailBody , body of mail.
     * @param attachFiles , attachments to be sent.
     */
    public SmtpRequest(final String username,
                       final String host,
                       final String password,
                       final String recipientMailId,
                       final String mailSubject,
                       final String mailBody,
                       final String attachFiles)
    {
        this._username = username;
        this._host = host;
        this._password = password;
        this._recipientMailId = recipientMailId;
        this._mailSubject = mailSubject;
        this._mailBody = mailBody;
        this._attachFiles = attachFiles;
    }

    /**
     * Getter for username
     * 
     * @return _username
     */
    public String getUsername()
    {
        return _username;
    }

    /**
     * Setter for username
     * 
     * @param username set username
     */
    public void setusername(final String username)
    {
        this._username = username;
    }

    /**
     * Getter for password
     * 
     * @return _password
     */
    public String getPassword()
    {
        return _password;
    }

    /**
     * Setter for password
     * 
     * @param password set password
     */
    public void setpassword(final String password)
    {
        this._password = password;
    }

    /**
     * Getter for host to make http request to.
     * 
     * @return host
     */
    public String getHost()
    {
        return _host;
    }

    /**
     * Setter for host to make http request to.
     * 
     * @param host set host
     */
    public void setHost(final String host)
    {
        this._host = host;
    }

    /**
     * Getter for recipient mail id.
     * 
     * @return _recipientMailId.
     */
    public String getRecipientMailId()
    {
        return _recipientMailId;
    }

    /**
     * Setter for recipient mail id.
     * 
     * @param recipientMailId set recipientMailId.
     */
    public void setRecipientMailId(final String recipientMailId)
    {
        this._recipientMailId = recipientMailId;
    }

    /**
     * Getter for mailSubject.
     * 
     * @return _mailSubject.
     */
    public String getMailSubject()
    {
        return _mailSubject;
    }

    /**
     * Setter for mailSubject.
     * 
     * @param mailSubject set mailSubject.
     */
    public void setMailSubject(final String mailSubject)
    {
        this._mailSubject = mailSubject;
    }

    /**
     * Getter for mail Body.
     * 
     * @return _mailBody.
     */
    public String getMailBody()
    {
        return _mailBody;
    }

    /**
     * Setter for Mail Body.
     * 
     * @param mailBody.
     */
    public void setMailBody(final String mailBody)
    {
        this._mailBody = mailBody;
    }

    /**
     * Getter for responseCode.
     * 
     * @return _responseCode from SMTP request.
     */
    public int getResponseCode()
    {
        return this._responseCode;
    }

    /**
     * Getter for responseMessage.
     * 
     * @return responMessage from Smtp request.
     */
    public String getResponseMessage()
    {
        return this._responseMessage;
    }

    /**
     * Getter for attach Files.
     * 
     * @return _attachFiles .
     */
    public String getAttachFiles()
    {
        return _attachFiles;
    }

    /**
     * Setter for attach Files.
     * 
     * @param attachFiles set attachFiles.
     */
    public void setAttachFiles(final String attachFiles)
    {
        this._attachFiles = attachFiles;
    }

    /**
     * Make Smtp request from client using given data.
     * 
     * @throws Exception in case of errors.
     */
    public void makeSmtpReq()
        throws Exception
    {

        Properties props = new Properties();
        props.put("mail.smtp.host", _host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(_username, _password);
            }
        });

        // Compose the Mail.
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(_username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(_recipientMailId));
            message.setSubject(_mailSubject);
            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(_mailBody, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // adds attachments
            if (_attachFiles != null)
            {

                MimeBodyPart attachPart = new MimeBodyPart();
                try
                {
                    attachPart.attachFile(_attachFiles);
                    System.out.println("Attached attachment file.");
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                    _responseMessage = ex.getMessage();
                }
                multipart.addBodyPart(attachPart);
                System.out.println("Attached attachment to multipart.");
            }

            // sets the multi-part as e-mail's content
            message.setContent(multipart);

            // sends the e-mail
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect();
            t.sendMessage(message, message.getAllRecipients());
            _responseMessage = t.getLastServerResponse();
            _responseCode = t.getLastReturnCode();

            System.out.println("Yeah..Mail sent successfully to recipient mail Id/Ids.");

        }
        catch (MessagingException e)
        {
            e.printStackTrace();
            _responseMessage = e.getMessage();

        }
    }
}
