package capitaltwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final int DAY_MIN = 1;
    private static final int DAY_MAX = 28;
    private static final int MONTH_MIN = 1;
    private static final int MONTH_MAX = 12;
    private static final int CARD_EXPIRE_MIN = 2018;
    private static final int CARD_EXPIRE_MAX = 2025;

    public static String[] ITEMS = {
        "Appliances",
        "Comfort",
        "Decorations",
        "Electronics",
        "Entertainment",
        "Hobbies",
        "Lighting",
        "Plumbing",
        "Storage",
        "Surfaces",
        "Vehicles",
        "Miscellaneous",
        "Pets"
    };

    public static String[] DESCRIPTIONS = {
        "Mainly kitchen objects: Refrigerators, stoves, dishwashers, microwaves, coffee makers, food processor, garbage cans, barbecues and trash compactors.",
        "Beds, chairs, armchairs, sofas, bar stools, benches and deck chairs.",
        "Plants, paintings, sculptures, mirrors, curtains, rugs, ornaments, etc.",
        "TVs, computers, stereos, clocks, burglar alarm, fire alarm, video games, phones, pinball machines, etc.",
        "Sporting goods, hobbies and skill items, party items, etc.",
        "Musical instruments, bookshelves, exercise equipment, play equipment, workbenches, other skill-building items, etc.",
        "Lamps, wall sconces, hanging lights, and outdoor lights.",
        "Toilets, sinks, showers, bathtubs and hot tubs.",
        "Bookshelves and dressers.",
        "Counters, kitchen tables, desks, side tables, coffee tables, shelves and displays (not bookshelves), cabinets, bars etc.",
        "Cars, bicycles, motorcycles, scooters, parking spaces, garage doors, and bike racks.",
        "Skill-building items (mirrors, weight machines, bookshelves),dressers, play equipment, party items, children\'s items, cars, pet items, garbage cans, etc.",
        "Pet Bowls, pet houses, chewable dog toys, scratching posts, horse training obstacles, horse stalls, haystack, etc."
    };

    public static String[] STATES = {
        "Alaska", "Virginia", "Kansas", "Tennessee", "New Mexico", "Wisconsin",
        "California", "Alabama", "North Carolina", "Guam", "Idaho", "Palau",
        "Louisiana", "Mississippi", "North Carolina", "Tennessee",
        "Massachusetts", "Illinois", "California", "Nevada", "Massachusetts",
        "South Carolina", "Alabama", "South Carolina", "Maryland", "Louisiana",
        "Maine", "Delaware", "Hawaii", "Illinois", "Idaho", "Iowa", "Alaska",
        "West Virginia", "Texas", "Michigan", "Oregon", "North Carolina",
        "North Dakota", "Nebraska", "Missouri", "Alaska", "Virginia",
        "California", "North Dakota", "South Dakota", "Colorado", "New Mexico",
        "American Samoa", "California", "Connecticut", "Missouri", "Missouri",
        "Hawaii", "South Carolina", "Ohio", "Florida", "Kentucky", "North Dakota",
        "Northern Mariana Islands", "Oregon", "Arizona", "Arkansas", "Wisconsin",
        "Federated States Of Micronesia"
    };

    public static String[] CITIES= {
        "FAIRBANKS", "MANASSAS", "ERIE", "KINGSPORT", "SANTA CRUZ", "LANCASTER",
        "BLACKHAWK-CAMINO TASSAJARA", "BRUNDIDGE", "WHITE PLAINS", "GUAM",
        "HAZELTON", "Palau", "LAROSE", "MERIGOLD", "LINCOLNTON", "TRIMBLE",
        "SOUTH DENNIS", "MCCOOK", "OCEANSIDE", "WEST WENDOVER", "LYNN", "OLAR",
        "MCMULLEN", "PATRICK", "KENT NARROWS", "DESTREHAN", "WILTON",
        "FENWICK ISLAND", "KOLOA", "DANVERS", "KETCHUM", "SERGEANT BLUFF",
        "WALES", "WOMELSDORF (COALTON)", "AVERY", "ROCKWOOD",
        "HARBECK-FRUITDALE", "BROGDEN", "LARIMORE", "LIBERTY", "CURRYVILLE",
        "COOPER LANDING", "BROOKNEAL", "THERMALITO", "MAKOTI", "KAYLOR",
        "CROWLEY", "UNIVERSITY PARK", "American Samoa", "EDGEWOOD",
        "PUTNAM DISTRICT", "LADDONIA", "CARUTHERSVILLE", "LIHUE", "JEFFERSON",
        "BURLINGTON", "FISHER ISLAND", "MACKVILLE", "COLEHARBOR",
        "NORTHERN MARIANA ISLANDS", "PILOT ROCK", "WILLIAMS", "HAZEN", "WHITING",
        "FSM"
    };

    public static String[] BUSINESS_NAME = {
        "Google",
        "Facebook",
        "Microsoft",
        "Amazon",
        "Apple",
        "Walmart",
        "Safeway",
        "Best Buy",
        "Grocery Outlet Bargain Market",
        "Target",
        "Costco",
        "Starbucks",
        "Sam's Club",
        "Lucky's",
        "Walgreen's",
        "Macy's",
        "Nordstrom",
        "Home Depot",
        "Orchard Hardware Supplies"
    };


    /* Repositories */
    private final CreditCardRepository creditCardRepo;
    private final DebitCardRepository debitCardRepo;
    private final CustomerAccountRepository customerRepo;
    private final ItemRepository itemRepo;
    private final TransactionRepository transactionRepo;
    private final TransactionItemRepository transactionItemRepo;
    private final BusinessRepository businessRepo;
    private final BusinessAccountRepository businessAccountRepo;

    /* Entity sets for later use */
    private ArrayList<Item> items;
    private ArrayList<Business> businesses;

    @Autowired
    public DatabaseLoader(CreditCardRepository creditRepo,
                          DebitCardRepository debitRepo,
                          CustomerAccountRepository customerRepo,
                          ItemRepository itemRepo,
                          TransactionRepository transactionRepo,
                          TransactionItemRepository transactionItemRepo,
                          BusinessRepository businessRepo,
                          BusinessAccountRepository businessAccountRepo) {
        this.creditCardRepo = creditRepo;
        this.debitCardRepo = debitRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.transactionRepo = transactionRepo;
        this.transactionItemRepo = transactionItemRepo;
        this.businessRepo = businessRepo;
        this.businessAccountRepo = businessAccountRepo;

        this.items = new ArrayList<Item>();
        this.businesses = new ArrayList<Business>();
    }

    /* low to high inclusive */
    public static int randomInt(int low, int high) {
        return ((new Random()).nextInt(high - low + 1) + low);
    }

    public void generateItems() {
        for (int i = 0; i < ITEMS.length; ++i) {
            Item it = new Item(ITEMS[i], DESCRIPTIONS[i]);
            this.items.add(itemRepo.save(it));
        }
    }

    public String[] generateCity() {
        int idx = randomInt(0, this.CITIES.length - 1);
        String[] city = {this.CITIES[idx], this.STATES[idx]};
        return city;
    }

    public String generatePhoneNumber() {
        String p = "";
        for (int i = 0; i < 10; ++i) {
            p += Integer.toString(randomInt(0,9));
        }
        return p;
    }

    public void generateTransactionItems(Card card) {
        HashSet<TransactionItem> ti = new HashSet<TransactionItem>();
        HashSet<Item> itemsSeen = new HashSet<Item>();

        for(int j = 0 ; j < 5; j++){
            int busIdx = randomInt(0, businesses.size()-1);
            Business business = this.businesses.get(busIdx);
            String[] city = generateCity();
            Date day = new Date((new Date()).getTime() - randomInt(0,1000)* 24 * 3600 * 1000l);
            Transaction transaction = new Transaction(
                day,
                city[1],
                city[0],
                card,
                business,
                null
            );
            this.transactionRepo.save(transaction);

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
            business.addTransaction(transaction);
            this.businesses.set(busIdx, this.businessRepo.save(business));
        }
    }

    public void generateBusinesses() {
        for (int i = 0; i < BUSINESS_NAME.length; ++i) {
            Business b = new Business(
                this.BUSINESS_NAME[i], (double)randomInt(1, 5),
                new HashSet<Transaction>(), new HashSet<BusinessAccount>()
            );
            this.businesses.add(this.businessRepo.save(b));
        }
    }

    public void generateBusinessAccounts(){
        for(Business b : businesses){
            Set<BusinessAccount> accountSet = new HashSet<BusinessAccount>();
            b.setAccounts(accountSet);
            BusinessAccount newAccount = new BusinessAccount(b.getName(), "123456", generatePhoneNumber(),
                            b.getName().split(" ")[0]+"@gmail.com", randomInt(2020, 2025), randomInt(1,12),
                            randomInt(1,30), "Partner", true, b);
            b.addAccount(newAccount);
            businessAccountRepo.save(newAccount);
        }
    }

    public void generateAccounts() {
        ArrayList<CustomerAccount> customers = new ArrayList<CustomerAccount>();
        customers.add(new CustomerAccount(
            "username1",
            "hunter2",
            "123-456-7890",
            "therealdonald@gmail.com",
            "Donald",
            "D",
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
        customers.add(new CustomerAccount(
            "MJaccount",
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
        
        generateAccounts();
        generateItems();
        generateBusinesses();
        generateBusinessAccounts();

        List<CustomerAccount> accounts = this.customerRepo.findAll();
        for(CustomerAccount acc : accounts) {
            Set<Card> ccards = new HashSet<Card>();
            ccards.add(new CreditCard(acc, 2020, randomInt(1,12), randomInt(1,30), 1000));
            ccards.add(new CreditCard(acc, 2020, randomInt(1,12), randomInt(1,30), 2000));
            ccards.add(new CreditCard(acc, 2020, randomInt(1,12), randomInt(1,30), 1000));
            ccards.add(new CreditCard(acc, 2020, randomInt(1,12), randomInt(1,30), 3000));
            for (Card c : ccards) {
                CreditCard cc = (CreditCard)c;
                this.creditCardRepo.save(cc);
            } 
            acc.setCards(ccards);
            this.customerRepo.save(acc);
            generateTransactionItems(ccards.iterator().next());
        }
    }
}
