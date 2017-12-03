package capitaltwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.sql.Timestamp;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired private CreditCardRepository creditRepo;
    @Autowired private DebitCardRepository debitRepo;
    @Autowired private TransactionRepository transactionRepo;
    @Autowired private TransactionItemRepository transactionItemRepo;
    @Autowired private CustomerAccountRepository customerRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private CreditCardRepository creditCardRepo;
    @Autowired private DebitCardRepository debitCardRepo;
    @Autowired private BusinessRepository businessRepo;

    @Autowired private EntityManager em;

    /* Utility helper function to call QueryUtils */
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

    @RequestMapping(value="/findBusiness",
                    params = "email", 
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getBusinessAccount(@RequestParam("email") String email) {
        String[] cols = {"business_id", "name", "reward_rate", "expiration", "position","verified","email","phoneNo"};
        String query = String.join("\n"
            ,"SELECT"
            ,    "*"
            ,"FROM"
            ,    "business_list"
            ,"WHERE"
            ,    "email = '" + email + "'"
        );
        return queryResults(query, cols);
    }

    @RequestMapping(value="/findAllBusiness",
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getBusinesses() {
        String[] cols = {"business_id", "name", "reward_rate"};
        String query = String.join("\n"
            ,"SELECT"
            ,    "*"
            ,"FROM"
            ,    "business"
        );
        return queryResults(query, cols);
    }

    @RequestMapping(value="/getCustomerCards",
                    params="account_id",
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getCustomerCards(@RequestParam("account_id") Long account_id) {
        String[] cols = {"card_id", "card_number", "expiration"};
        String query = String.join("\n"
            ,"SELECT"
            ,    "card_id, card_number, expiration_day"
            ,"FROM"
            ,    "card"
            ,"WHERE"
            ,"account_account_id = "+account_id
        );
        return queryResults(query, cols);
    }

    @RequestMapping(value="/findCustomerId",
                    params = "email", 
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getCustomerAccountID(@RequestParam("email") String email) {
        String[] cols = {"account_id"};
        String query = String.join("\n"
            ,"SELECT"
            ,    "account_id"
            ,"FROM"
            ,    "account"
            ,"WHERE"
            ,    "email = '"+email+"'"
        );
        return queryResults(query, cols);
    }

    @RequestMapping(value="/getItemList",
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getItemList() {
        String[] cols = {"item_id", "description", "name"};
        String query = String.join("\n"
            ,"SELECT"
            ,    "*"
            ,"FROM"
            ,    "item"
        );
        return queryResults(query, cols);
    }

    @RequestMapping(value="/getCustomerTrans",
                    params = {"account_id", "start", "end", "item_id"},
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getCustomerTransaction(
        @RequestParam("account_id") Long account_id,
        @RequestParam("start") String start,
        @RequestParam("end") String end,
        @RequestParam("item_id") Long item_id) { // default is -1
        String[] cols = {
            "transaction_id","city","cost","date","state","business_id",
            "card_id", "card_number", "quantity","name","category",
            "unit_price", "item_id"
        };

        String dateJoin = QueryUtils.getDateJoinString(start, end);
        if (dateJoin != "") {
            dateJoin = " AND " + dateJoin;
        }

        String itemJoin = "";
        if (item_id != null && item_id != -1) {
            itemJoin = " AND transaction_list.item_id = " + item_id;
        }

        String query = String.join("\n"
            ,"SELECT"
            ,    "*"
            ,"FROM"
            ,    "transaction_list"
            ,"WHERE"
            ,    "card_id in"
            ,"("
                ,"SELECT"
                ,"customer_cards_card_id"
                ,"FROM"
                ,"customer_account_customer_cards"
                ,"WHERE"
                ,"customer_account_account_id = "+account_id
            ,")"
            ,itemJoin
            ,dateJoin
        );
        return queryResults(query, cols);
    }  

    @RequestMapping(value="/getCustomerCategorizedTrans",
                    params = {"start", "end"},
                    method=RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public String getCustomerCategorizedTransaction(
        @RequestParam("start") String start,
        @RequestParam("end") String end) {
        String[] cols = {
            "category", "amount"
        };

        String dateJoin = QueryUtils.getDateJoinString(start, end);
        if (dateJoin != "") {
            dateJoin = " WHERE " + dateJoin;
        }
       
        System.out.println("date join " + dateJoin);

        String query = String.join("\n"
            ,"SELECT"
            ,    "category, sum(quantity)"
            ,"FROM"
            ,    "transaction_list"
            ,dateJoin
            ,"GROUP BY category"
        );
        //return em.createNativeQuery(query).getResultList().toString();
        return queryResults(query, cols);
    }  

    @RequestMapping(value="/getBusinessTrans",
                    params={"business_id", "start", "end"},
                    method=RequestMethod.GET,
                    produces="application/json")
    @ResponseBody
    public String getBusinessTransaction(
        @RequestParam("business_id") Long business_id,
        @RequestParam("start") String start,
        @RequestParam("end") String end) {
        String[] cols = {
            "transaction_id","city","cost","date","state","business_id",
            "card_id","quantity","name","category", "item_id"
        };

        String dateJoin = QueryUtils.getDateJoinString(start, end);
        if (dateJoin != "") {
            dateJoin = " AND " + dateJoin;
        }
        String query = String.join("\n"
            ,"SELECT"
            ,    "*"
            ,"FROM"
            ,    "transaction_list"
            ,"WHERE"
            ,    "business_id = " + business_id
            ,    dateJoin
            ,"ORDER BY"
            ,"date, transaction_id"
        );
        return queryResults(query, cols);
    }

    // group: year=0, month=1
    @RequestMapping(value="/getBusinessTransTotalByGroup",
                    params={"business_id", "start", "end", "group"},
                    method=RequestMethod.GET,
                    produces="application/json")
    @ResponseBody
    public String getBusinessTransactionTotalByGroup(
        @RequestParam("business_id") Long business_id,
        @RequestParam("start") String start,
        @RequestParam("end") String end,
        @RequestParam("group") Integer group) {
        String[] cols;
        if (group == 0) {
            cols = new String[]{ "total", "year" };
        }
        else {
            cols = new String[]{ "total", "month", "year" };
        }
        String dateJoin = QueryUtils.getDateJoinString(start, end);
        if (dateJoin != "") {
            dateJoin = "AND " + dateJoin;
        }

        String groupBy = group == 0 ?
            "transaction.year" : "transaction.month, transaction.year";
        
        String query = String.join("\n"
			,"SELECT                                "
			,"	SUM(transaction.cost),              "
            , groupBy
			,"FROM                                  "
			,"	transaction                         "
            ,"WHERE"
            ,"business_business_id = "+business_id
            , dateJoin
			,"GROUP BY                              "
			, groupBy
        );
        return queryResults(query, cols);
    }

    @RequestMapping(value="/getBusinessDemographics",
                    params={"business_id", "min_age", "max_age", "ethnicity", "gender"},
                    method=RequestMethod.GET,
                    produces="application/json")
    @ResponseBody
    public String getBusinessDemographics(
        @RequestParam("business_id") Long business_id,
        @RequestParam("min_age") Integer min_age,
        @RequestParam("max_age") Integer max_age,
        @RequestParam("ethnicity") Integer ethnicity,
        @RequestParam("gender") Integer gender) {
    
        ArrayList<String> joins = new ArrayList<String>();

        if (min_age != -1 || max_age != -1) {
            String ageJoin = "";
            String ageQuery = "YEAR(date) - birth_year";
            if (min_age != -1) {
                ageJoin += ageQuery + " > " + min_age;
                if (max_age != -1) {
                    ageJoin += " AND ";
                }
            }
            if (max_age != -1) {
                ageJoin += ageQuery + " < " + max_age;
            }
            joins.add(ageJoin);
        }

        if (ethnicity != -1) {
            joins.add("ethnicity = " + ethnicity);
        }

        if (gender != -1) {
            joins.add("gender = " + gender);
        }

        String joinQuery = "";
        for (String s : joins) {
            joinQuery += " AND " + s;
        }

        String[] cols = {
            "birth_day", "birth_month", "birth_year", "ethnicity",
            "name", "gender", "income", "reward_points", "transaction_id",
            "cost", "date", "city", "state", "business_id"
        };
        String query = String.join("\n"
            ,"SELECT"
            ,"*"
            ,"FROM"
            ,"customer_demo_list"
            ,"WHERE"
            ,"business_id = " + business_id
            ,joinQuery
        );
        System.out.println(query);
        return queryResults(query, cols);
    }

    @RequestMapping(value="/getBusinessStatsByGroup",
                    params={"business_id", "group", "age_interval"},
                    method=RequestMethod.GET,
                    produces="application/json")
    @ResponseBody
    public String getBusinessDemographicsTotalsByGroup(
        @RequestParam("business_id") Long business_id,
        @RequestParam("group") Integer group,
        @RequestParam("age_interval") Integer age_interval) {
        /*
        group:
            0 - ethnicity
            1 - gender
            2 - age

        age_interval:
            -1 for none,
            any number for any age
            
        */
        String groupQuery;
        switch(group) {
            case 0: {
                groupQuery = "ethnicity";
                break;
            }
            case 1: {
                groupQuery = "gender";
                break;
            }
            case 2: {
                groupQuery = "FLOOR((YEAR(date) - birth_year)/" + age_interval + ")";
                break;
            }
            default: {
                groupQuery = "INVALID";
            }
        }
    
        String[] cols = {
            "count", "sum", "group_value"
        };
        String query = String.join("\n"
            ,"SELECT"
            ,"COUNT(*), SUM(cost), " + groupQuery + " AS groupVal"
            ,"FROM"
            ,"customer_demo_list"
            ,"WHERE"
            ,"business_id = " + business_id
            ,"GROUP BY groupVal"
        );
        System.out.println(query);
        return queryResults(query, cols);
    }

    @RequestMapping(value = "/createTransaction",
                    params={"date", "state", "city", "card_id",
                            "business_id", "items[]"},
                    method = RequestMethod.GET)
    public @ResponseBody String addTransaction (
        @RequestParam("date") String date,
        @RequestParam("state") String state,
        @RequestParam("city") String city,
        @RequestParam("card_id") Long card_id,
        @RequestParam("business_id") Long business_id,
        @RequestParam("items[]") String[] items) {

		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        for(int i = 0; i < items.length; i++){
            System.out.println("hhhhhhhhh " + items[i]);
        }

        Card currCard = creditCardRepo.findById(card_id);
        if (currCard == null) {
            currCard = debitCardRepo.findById(card_id);
            if (currCard == null) {
                System.out.println("failed at card");
                return "Failure";
            }
        }
        Business currBusiness = businessRepo.findById(business_id);
        if (currBusiness == null) {
            System.out.println("failed at business");
            return "Failure";
        }

        Date d = null;
        try {
            d = df.parse(date);
        }
        catch (Exception e) {
            System.out.println("INVALID DATE FORMAT");
            e.printStackTrace();
            System.out.println("failed at date format");
            return "Failure";
        }

        Transaction trans = new Transaction(
            d,
            state,
            city,
            currCard,
            currBusiness,
            null
        );
        System.out.println("Creating transaction...");
        System.out.println(trans);
        this.transactionRepo.save(trans);

        HashSet<TransactionItem> ti = new HashSet<TransactionItem>();
        for (String s : items) {
            String[] strs = s.split(",");
            Item currItem = itemRepo.findById(Long.parseLong(strs[0]));
            if (currItem == null) {
                System.out.println("FAILED TO FIND ITEM");
                return "FAILED";
            }
            TransactionItem tItem = new TransactionItem(
                trans,
                currItem,
                Integer.parseInt(strs[1]),
                Integer.parseInt(strs[2])
            );
            ti.add(tItem);
            this.transactionItemRepo.save(tItem);
        }
        trans.setTransactionItems(ti);
        this.transactionRepo.save(trans);
        System.out.println("Transaction id " + trans.getId());
        return "Success";
    }

    @Transactional
    @RequestMapping(value = "/deleteTransaction",
                    params={"transaction_id"},
                    method = RequestMethod.GET)
    public @ResponseBody String addTransaction (
        @RequestParam("transaction_id") Long transaction_id) {
        String deleteBusiness = "DELETE FROM business_transactions " +
            "WHERE transactions_transaction_id = " + transaction_id;
        String deleteTransactionItem = "DELETE FROM transaction_item " +
            "WHERE transaction_transaction_id = " + transaction_id;
        String deleteTransaction = "DELETE FROM transaction " +
            "WHERE transaction_id = " + transaction_id;
        em.createNativeQuery(deleteBusiness).executeUpdate();
        em.createNativeQuery(deleteTransactionItem).executeUpdate();
        em.createNativeQuery(deleteTransaction).executeUpdate();
        return "Success";
    }


    /*
    Update fields
        birth_day : integer
        birth_month : integer
        birth_year : integer
        ethnicity : integer
        gender : integer
        first_name : string
        middle_name : string
        last_name : string
        email : string (this has to change in firebase too)
        password : string (this has to change in firebase too)
    */
    @Transactional // allows update or delete
    @RequestMapping(value="/updateCustomerAccount",
                    params={"account_id", "fields[]", "values[]"},
                    method=RequestMethod.GET)
    public @ResponseBody String updateCustomerAccountField (
        @RequestParam("account_id") Long account_id,
        @RequestParam("fields[]") String[] fields,
        @RequestParam("values[]") String[] values) {

        String fieldUpdates = "";
        for (int i = 0; i < fields.length; ++i) {
            fieldUpdates = fieldUpdates + fields[i] + " = " + values[i];
            if (i != fields.length - 1) {
                fieldUpdates = fieldUpdates + ", ";
            }
        }

        System.out.println(fieldUpdates);

        String query = String.join("\n"
            ,"UPDATE"
            ,"account, customer_account"
            ,"SET"
            ,fieldUpdates
            ,"WHERE account.account_id = " + account_id
            ,"AND account.account_id = customer_account.account_id"
        );
        return em.createNativeQuery(query).executeUpdate() > 0 ? "Success" : "Fail";
    }


}
