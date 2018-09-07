package **packagename**// provide the package name under which the class file is created

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import package.SmtpRequest;

@RestController
public class SmtpRequestController
{

    /**
     * This method handles Smtp Request.
     * It makes Smtp request from current client with given params.
     * 
     * @param smtpreq Object having json passed as argument to this REST API
     * 
     * @return response JSON having (full response and md5sum as keys.)
     * @throws Exception.
     * 
     */
    @RequestMapping(value = "/smtpreq", method = RequestMethod.POST)
    public Map<String, String> SmtpReq(@RequestBody final SmtpRequest smtpreq)
        throws Exception
    {
        Map<String, String> response = new HashMap<>();

        smtpreq.makeSmtpReq();

        response.put("response_message", smtpreq.getResponseMessage());
        response.put("response_code", Integer.toString(smtpreq.getResponseCode()));

        return response;
    }

}
