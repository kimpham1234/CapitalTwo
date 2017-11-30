package capitaltwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@Controller    
@RequestMapping(path="/query")
public class QueryController {

    @Autowired
    private EntityManager em;

    private String queryResults(String query, String[] columns) {
        return QueryUtils.queryResults(em, query, columns);
    }

    @RequestMapping(value="/getAccounts", 
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getCustomerAccounts() {

        String[] cols = {"id", "email", "login", "name"};
        String query = String.join("\n"
            ,"SELECT"
            ,    "account.account_id as id,"
            ,    "account.email as email,"
            ,    "account.login_id as login,"
            ,    "customer_account.first_name as name"
            ,"FROM"
            ,    "account, customer_account"
            ,"WHERE"
            ,    "account.account_id = customer_account.account_id"
        );
        return queryResults(query, cols);
    }

}
