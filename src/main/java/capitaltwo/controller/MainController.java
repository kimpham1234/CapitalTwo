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

/*
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.Json;
import java.math.BigInteger;
*/


@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepo;
    @Autowired
    private CreditCardRepository creditRepo;
    @Autowired
    private DebitCardRepository debitRepo;

    @Autowired
    private CustomerAccountRepository customerRepo;

    @Autowired
    private EntityManager em;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepo.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepo.findAll();
	}

    @GetMapping(path="/credit")
    public @ResponseBody Iterable<CustomerAccount> getAllCreditCards() {
        //return customerRepo.getCustomers();
        Query q = em.createNativeQuery(
            "SELECT * FROM customer_account, transaction"
        );
        return q.getResultList();
        //return null;
    }

    private String queryResults(String query, String[] columns) {
        return QueryUtils.queryResults(em, query, columns);
    }

    @RequestMapping(value="/testjson", 
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
