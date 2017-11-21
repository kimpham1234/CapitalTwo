package capitaltwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final int DAY_MIN = 1;
    private static final int DAY_MAX = 28; // don't care about getting all dates
    private static final int MONTH_MIN = 1;
    private static final int MONTH_MAX = 12;
    private static final int CARD_EXPIRE_MIN = 2018;
    private static final int CARD_EXPIRE_MAX = 2025;

    private final UserRepository userRepo;
    private final CreditCardRepository creditCardRepo;
    private final DebitCardRepository debitCardRepo;
    private final CustomerAccountRepository customerRepo;
    private final ItemRepository itemRepo;
    private final TransactionRepository transactionRepo;
    private final TransactionItemRepository transactionItemRepo;
    private final BusinessRepository businessRepo;
    private final BusinessAccountRepository businessAccountRepo;
    private final CategoryRepository categoryRepo;

    private ArrayList<Item> items;
    private ArrayList<Business> businesses;
    private ArrayList<Category> categories;

    /* low to high inclusive */
    private static int randomInt(int low, int high) {
        return ((new Random()).nextInt(high - low + 1) + low);
    }

    @Autowired
    public DatabaseLoader(UserRepository userRepo,
                          CreditCardRepository creditRepo,
                          DebitCardRepository debitRepo,
                          CustomerAccountRepository customerRepo,
                          ItemRepository itemRepo,
                          TransactionRepository transactionRepo,
                          TransactionItemRepository transactionItemRepo,
                          BusinessRepository businessRepo,
                          BusinessAccountRepository businessAccountRepo,
                          CategoryRepository categoryRepo) {
        this.userRepo = userRepo;
        this.creditCardRepo = creditRepo;
        this.debitCardRepo = debitRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.transactionRepo = transactionRepo;
        this.transactionItemRepo = transactionItemRepo;
        this.businessRepo = businessRepo;
        this.businessAccountRepo = businessAccountRepo;
        this.categoryRepo = categoryRepo;

        this.items = new ArrayList<Item>();
        this.businesses = new ArrayList<Business>();
        this.categories = new ArrayList<Category>();
    }

    public static String[] CATEGORY = {
        "Food",
        "Electronics",
        "Clothing",
        "Utility",
        "Service"
    };
    public static String[] CATEGORY_DESCRIPTIONS = {
        "edible items",
        "techy thingies",
        "soft things",
        "useful things",
        "not goods"
    };

    public void generateCategories() {
        for (int i = 0; i < this.CATEGORY.length; ++i) {
            this.categories.add(
                new Category(CATEGORY[i], CATEGORY_DESCRIPTIONS[i])
            );
            this.categoryRepo.save(categories.get(i));
        }
    }

    public static String[] ITEMS = {
        "sock",
        "shoe",
        "clothing",
        "fruit",
        "veggies",
        "tv",
        "computer",
        "pencils"
    };

    public void generateItems() {
        for (String i : ITEMS) {
            Item it = new Item(i, "description here",
                this.categories.get(randomInt(0, this.categories.size()-1))
            );
            this.items.add(it);
            itemRepo.save(it);
        }
    }

    /* TODO return array pairs of state, city */
    public static String[] states = {
        "CA", "TX", "WA", "MA", "NY", "FL", "OR"
    };
    public String generateState() {
        return this.states[randomInt(0, this.states.length - 1)];
    }

    public static String[] cities = {
        "city1", "city2", "city3", "city4", "city5"
    };
    public String generateCity() {
        return this.cities[randomInt(0, this.cities.length - 1)];
    }

    public void generateTransactionItems(Card card) {
        HashSet<TransactionItem> ti = new HashSet<TransactionItem>();
        HashSet<Item> itemsSeen = new HashSet<Item>();

        Transaction transaction = new Transaction(
            new Date(),
            generateState(),
            generateCity(),
            card,
            this.businesses.get(randomInt(0, businesses.size()-1)),
            null
        );
        this.transactionRepo.save(transaction);
        //System.out.println(transaction);

        int sz = items.size();
        int r = randomInt(0, sz - 1);
        for (int i = 0; i < sz; ++i) {
            Item currItem = items.get(randomInt(0, sz-1));
            if (itemsSeen.add(currItem)) { // no duplicates
                TransactionItem tItem = new TransactionItem(
                    transaction,
                    currItem,
                    randomInt(1, 5),
                    (double)randomInt(5, 100)
                );
                transactionItemRepo.save(tItem);
                ti.add(tItem);
            }
        }
        transaction.setTransactionItems(ti);
        this.transactionRepo.save(transaction);
    }

    public static String[] BUSINESS_NAME = {
        "google",
        "facebook",
        "microsoft",
        "amazon",
        "apple",
        "grocery outlet bargain market"
    };
    public void generateBusinesses() {
        for (int i = 0; i < BUSINESS_NAME.length; ++i) {
            this.businesses.add(new Business(
                this.BUSINESS_NAME[i], (double)randomInt(1, 5)
            ));
            this.businessRepo.save(businesses.get(i));
        }
    }

    public void generateBusinessAccounts(){
        for(Business b : businesses){
            Set<BusinessAccount> accountSet = new HashSet<BusinessAccount>();
            b.setAccounts(accountSet);
            BusinessAccount newAccount = new BusinessAccount(b.getName(), "123456", "408-415-7292",
                            b.getName().split(" ")[0]+"@gmail.com", randomInt(2020, 2025), randomInt(1,12),
                            randomInt(1,30), "Partner", true, b);
            b.addAccount(newAccount);
            businessAccountRepo.save(newAccount);
        }
    }

    public void generateAccounts() {
        ArrayList<CustomerAccount> customers = new ArrayList<CustomerAccount>();
        customers.add(new CustomerAccount(
            "secritboy",
            "hunter2",
            "123-456-7890",
            "therealdonald@gmail.com",
            "Donald",
            "Dumbass",
            "Drumpf",
            Ethnicity.WHITE,
            Gender.MALE,
            1776, 7, 4,
            200000)
        );

        customers.add(new CustomerAccount(
            "someaccount",
            "123456",
            "4087138244",
            "email1@email.com",
            "Smith",
            null,
            "Johnson",
            Ethnicity.BLACK,
            Gender.MALE,
            1984, 10, 23,
            50000)
        );
        customers.add( new CustomerAccount(
            "WBaccount",
            "123456",
            "4086136224",
            "email2@email.com",
            "William",
            "Harold",
            "Brown",
            Ethnicity.WHITE,
            Gender.MALE,
            1985, 6, 12,
            45000)
        );
        customers.add(new CustomerAccount("MJaccount",
            "123456",
            "4087134294",
            "email3@email.com",
            "Mary",
            "J",
            "Jones",
            Ethnicity.WHITE,
            Gender.FEMALE,
            1990, 11, 14,
            57000)
        );
        customers.add(new CustomerAccount(
            "AWaccount",
            "123456",
            "4088133264",
            "email4@email.com",
            "Anna",
            null,
            "Wong",
            Ethnicity.ASIAN,
            Gender.FEMALE,
            1980, 1, 23,
            59000)
        );
        for (CustomerAccount customer : customers) {
            this.customerRepo.save(customer);
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        
        generateCategories();
        generateAccounts();
        generateItems();
        generateBusinesses();
        generateBusinessAccounts();

        CustomerAccount acc = this.customerRepo.findAll().get(0);

        Set<Card> ccards = new HashSet<Card>();
        ccards.add(new CreditCard(acc, 2020, 10, 19, 1000));
        ccards.add(new CreditCard(acc, 2020, 10, 19, 2000));
        ccards.add(new CreditCard(acc, 2020, 10, 19, 1000));
        ccards.add(new CreditCard(acc, 2020, 10, 19, 3000));
        for (Card c : ccards) {
            CreditCard cc = (CreditCard)c;
            this.creditCardRepo.save(cc);
        }

        acc.setCards(ccards);
        this.customerRepo.save(acc);

        //Item i1 = new Item("sponge", "spongey thing to clean stuff");
        //Item i2 = new Item("soap", "bubbly thing that makes squeakies");

        generateTransactionItems(ccards.iterator().next());

        

        //Transaction tr = new Transaction

        //ccards.get(0)

        /*
        for (CreditCard c : ccards) {
            this.creditCardRepo.save(c);
        }
        ArrayList<DebitCard> dcards = new ArrayList<DebitCard>();
        dcards.add(new DebitCard(new Date(2020, 10, 19), 10000));
        dcards.add(new DebitCard(new Date(2020, 10, 19), 20000));
        dcards.add(new DebitCard(new Date(2020, 10, 19), 10000));
        dcards.add(new DebitCard(new Date(2020, 10, 19), 30000));
        for (DebitCard c : dcards) {
            this.debitCardRepo.save(c);
        }
        */

        /*
        CustomerAccount acct = new CustomerAccount(
            "firstname",
            "nomiddle",
            "lastname",
            Ethnicity.WHITE,
            Gender.FEMALE,
            new Date(2000, 11, 11),
            10000
        );
        CreditCard c1 = new CreditCard(new Date(2017, 10, 7), 4000);
        DebitCard d1 = new DebitCard(new Date(2017, 10, 9), 8000);
        c1.setAccount(acct);
        d1.setAccount(acct);
        this.customerRepo.save(acct);
        this.creditCardRepo.save(c1);
        this.debitCardRepo.save(d1);
        */
    }
}
