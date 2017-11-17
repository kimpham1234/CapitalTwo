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

    private ArrayList<Item> items;

    /* low to high inclusive */
    private static int randomInt(int low, int high) {
        return ((new Random()).nextInt(high - low + 1) + low);
    }

    @Autowired
    public DatabaseLoader(CreditCardRepository creditRepo,
                          DebitCardRepository debitRepo,
                          CustomerAccountRepository customerRepo) {
                          CustomerAccountRepository customerRepo,
                          ItemRepository itemRepo,
                          TransactionRepository transactionRepo,
                          TransactionItemRepository transactionItemRepo) {
        this.userRepo = userRepo;
        this.creditCardRepo = creditRepo;
        this.debitCardRepo = debitRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.transactionRepo = transactionRepo;
        this.transactionItemRepo = transactionItemRepo;

        this.items = new ArrayList<Item>();
    }

    public void generateItems() {
        this.items.add(new Item("sock", "put on ur feet"));
        this.items.add(new Item("shoe", "description here"));
        this.items.add(new Item("clothing", "description here"));
        this.items.add(new Item("fruit", "description here"));
        this.items.add(new Item("veggies", "description here"));
        this.items.add(new Item("tv", "description here"));
        this.items.add(new Item("computer", "description here"));
        this.items.add(new Item("pencils", "description here"));
        for (Item i : items) {
            itemRepo.save(i);
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
        /*
        this.userRepo.save(new User("name1", "email1@email.com"));
        this.userRepo.save(new User("name2", "email2@email.com"));
        this.userRepo.save(new User("name3", "email3@email.com"));
        this.userRepo.save(new User("name4", "email4@email.com"));*/

		/*
		this.customerRepo.save(new CustomerAccount("SJ", "123456", "4087138244", "email1@email.com", "Smith Johnson",
							 Ethnicity.BLACK, Gender.MALE, new Date(1984, 10, 23), 50000));
        this.customerRepo.save(new CustomerAccount("WB", "123456", "4086136224", "email2@email.com", "William Brown",
                             Ethnicity.WHITE, Gender.MALE, new Date(1985, 6, 12), 45000));
        this.customerRepo.save(new CustomerAccount("MJ", "123456", "4087134294", "email3@email.com", "Mary Jones",
                             Ethnicity,WHITE, Gender.FEMALE, new Date(1990, 11, 14), 57000));
        this.customerRepo.save(new CustomerAccount("AW", "123456", "4088133264", "email4@email.com", "Anna Wong",
                             Ethnicity.ASIAN, Gender.FEMALE, new Date(1980, 1, 23), 59000));
        */
        this.customerRepo.save(
            new CustomerAccount("secritboy", "hunter2", "123-456-7890", "therealdonald@gmail.com",
                                "Donald", "Dumbass", "Drumpf",
                                Ethnicity.WHITE, Gender.MALE, new Date(1776, 7, 4),
                                200000)
        );
        
        generateAccounts();
        generateItems();

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
