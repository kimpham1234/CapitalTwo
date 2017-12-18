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
import java.util.Calendar;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final int DAY_MIN = 1;
    private static final int DAY_MAX = 28;
    private static final int MONTH_MIN = 1;
    private static final int MONTH_MAX = 12;
    private static final int CARD_EXPIRE_MIN = 2018;
    private static final int CARD_EXPIRE_MAX = 2025;

    public static String[] MALE_FIRST_NAMES = {
        "Brian", "Terrell", "Earnest", "Bart", "Bryce", "Grover", "Keith",
        "Bernard", "Chadwick", "Russel", "Millard", "Reuben", "Van", "Jeffrey",
        "Toby", "Edmundo", "Joesph", "Willian", "Jonah", "Scott", "Sergio",
        "Murray", "Darnell", "Rueben", "Lazaro", "William", "Mitch", "Heriberto",
        "Gregg", "Lynn", "Demetrius", "Nathanael", "Neil", "Tyron", "Jefferey",
        "Homer", "Lawrence", "Lesley", "Philip", "Harland", "Jared", "Ambrose",
        "Wes", "Garrett", "Kendall", "Saul", "Wilford", "Adrian", "Elton", "Anthony",
        "Ron", "Herman", "Rudolf", "Maximo", "Bryant", "Kenton", "Alex", "Coy",
        "Arlen", "Michael", "Eugenio", "Adalberto", "Jaime", "Lynwood", "Dante",
        "Herschel", "Pat", "Kareem", "Norman", "Jae", "Johnathon", "Fredric",
        "Cristopher", "Brendon", "Kelvin", "Riley", "Rene", "Monroe", "Angel",
        "Buford", "Domingo", "Johnie", "Jeffery", "Kelly", "James", "Dion", "Nick",
        "Stanton", "Cameron", "Brett", "Linwood", "Kevin", "Kory", "Ricardo",
        "Vicente", "Fritz", "Jesus", "Jacinto", "Edison", "Eddy"
    };

    public static String[] FEMALE_FIRST_NAMES = {
        "Alta", "Celinda", "Lannie", "Felicitas", "Georgann", "Verdie", "Sana",
        "Keli", "Avelina", "Sanjuanita", "Carri", "Johnette", "Nidia", "Christine",
        "Magdalen", "Jama", "Vivien", "Karol", "Vanna", "Tu", "Maryann", "Marlo",
        "Toi", "Clemmie", "Jacquelin", "Celina", "Vena", "Trinh", "Donnette",
        "Xiao", "Robbi", "Jacquelyne", "Leanora", "Jolene", "Pamula", "Catharine",
        "Ona", "Sanda", "Yee", "Jayna", "Rochell", "Danille", "Fanny", "Bernice",
        "Rosann", "Shelli", "Vicki", "Joycelyn", "Calandra", "Meta", "Lucinda",
        "Elenora", "Mitzi", "Lucia", "Reta", "Tomoko", "Bernarda", "Elvira",
        "Jo", "Rosanne", "Marya", "Tresa", "Aida", "Lurline", "Merlyn", "Nakisha",
        "Renetta", "Stefani", "Piedad", "Camelia", "Belinda", "Darlene", "Janessa",
        "Vernia", "Racheal", "Bebe", "Cheryl", "Providencia", "Cherryl", "Charlsie",
        "Alysha", "Shae", "Margit", "Vanetta", "Natisha", "Deborah", "Jonnie",
        "Gaynell", "Laurice", "Saturnina", "Mirella", "Noelle", "Belle", "Richelle",
        "Simona", "Ingrid", "Eloisa", "Marilee", "Constance", "Imogene"
    };

    public static String[] LAST_NAMES = {
        "Chung", "Chen", "Melton", "Hill", "Puckett", "Song", "Hamilton", "Bender",
        "Wagner", "McLaughlin", "McNamara", "Raynor", "Moon", "Woodard", "Desai",
        "Wallace", "Lawrence", "Griffin", "Dougherty", "Powers", "May", "Steele",
        "Teague", "Vick", "Gallagher", "Solomon", "Walsh", "Monroe", "Connolly",
        "Hawkins", "Middleton", "Goldstein", "Watts", "Johnston", "Weeks", "Wilkerson",
        "Barton", "Walton", "Hall", "Ross", "Chung", "Bender", "Woods",
        "Mangum", "Joseph", "Rosenthal", "Bowden", "Barton", "Underwood", "Jones",
        "Baker", "Merritt", "Cross", "Cooper", "Holmes", "Sharpe", "Morgan",
        "Hoyle", "Allen", "Rich", "Rich", "Grant", "Proctor", "Diaz",
        "Graham", "Watkins", "Hinton", "Marsh", "Hewitt", "Branch", "Walton",
        "O'Brien", "Case", "Watts", "Christensen", "Parks", "Hardin", "Lucas",
        "Eason", "Davidson", "Whitehead", "Rose", "Sparks", "Moore", "Pearson",
        "Rodgers", "Graves", "Scarborough", "Sutton", "Sinclair", "Bowman", "Olsen",
        "Love", "McLean", "Christian", "Lamb", "James", "Chandler", "Stout",
        "Cowan", "Golden", "Bowling", "Beasley", "Clapp", "Abrams", "Tilley",
        "Morse", "Boykin", "Sumner", "Cassidy", "Davidson", "Heath", "Blanchard",
        "McAllister", "McKenzie", "Byrne", "Schroeder", "Griffin", "Gross", "Perkins",
        "Robertson", "Palmer", "Brady", "Rowe", "Zhang", "Hodge", "Li", "Bowling",
        "Justice", "Glass", "Willis", "Hester", "Floyd", "Graves", "Fischer",
        "Norman", "Chan", "Hunt", "Byrd", "Lane", "Kaplan", "Heller",
        "May", "Jennings", "Hanna", "Locklear", "Holloway", "Jones", "Glover",
        "Vick", "O'Donnell", "Goldman", "McKenna", "Starr", "Stone"
    };

    public static String[] USERNAMES = {
        "unhappy_colleague", "mortallysupremeinflux", "constant_maintenance",
        "reproachfully_teeny-tiny_chivalry", "spicy_stole", "utter_adherence",
        "deserted_syntax", "Jam-packedReceptor", "readydexterity564",
        "unawareboasting5", "box_of_arms", "OpenNeighbour",
        "PM_ME_YOUR_SYNDROMES", "NeatlyHabitual", "NeatlyGarrulous",
        "OftenUntried", "SpeedilyFormal", "lackingsesame68",
        "adhesivemover_5975", "bossy_effectiveness", "shaggybattery",
        "needy_bureaucracy", "noteworthyhands0", "continually_rich_propensity",
        "LuxuriantManifesto", "negativeemperor45", "alleged_header",
        "NumberlessDelegation", "PM_ME_YOUR_PERSONAS", "UnequaledDingo",
        "selfishly_gray_crocodile", "pushyeyewitness_5371", "excitedlypoorvolcano",
        "TroubledIntercession", "notable_outage", "apt_lookout",
        "purring_wiring", "misguidedpiles_16", "reassuringly_ethical_self-confidence",
        "rarely_entertaining_delicacy", "DiscreetPrivacy", "forgetfulrecipe82",
        "apathetic_progression", "NoisyAssociation", "dynamic_narration",
        "ThreateningBanjo", "therapeutic_honesty", "loud_follower",
        "PM_ME_YOUR_ARCHITECTURES", "RevolvingTicker", "PresentCoverage",
        "RudelyMixed", "UpliftinglyFixed", "envelope_of_conditioners",
        "LinearFrequency", "WrongBookmark", "brisklyquackaccuracy",
        "RighteouslyMiniature", "SoftGoogle", "unequaled_domination",
        "bewitchedbicycle08", "PleasantDrank", "young_and_foolish",
        "supportivebroccoli_83", "veneratedterry", "abiding_joseph",
        "ThankfullyHonorable", "Sure-footedVoltage", "TruculentOutfield",
        "politicaladvertising_44", "SoftlyRebel", "colossalanthropology138",
        "automaticextinction_34", "courageousfoyer8841", "cup_of_tracks",
        "thevillainousultimatum", "CriticalStorey", "theshyplurality",
        "NicelySlushy", "slow_mentality", "TheBulkyApplause",
        "theblack-and-whiteemancipation", "lostpottery", "AboundingLiner",
        "ImpishBuffalo", "exasperated_envoy", "TheCravenReceptor",
        "HandsomeOpportunity", "FunnyRunoff", "cut_through_the_red_tape",
        "FretfulBanjo", "belligerentscenery334", "unruly_rendition",
        "FarawayRanger", "selfish_espionage", "theapprehensivescouring",
        "gleefulhockey", "AwkwardFunction", "recklesslyreminiscentabsence",
        "pleasing_passenger"
    };

    public static String[] EMAIL_DOMAINS = {
        "@gmail.com",
        "@yahoo.com",
        "@hotmail.com"
    };

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
        "Transportation",
        "Miscellaneous",
        "Pets",
        "Outdoor",
        "Kids",
        "Medical"
    };

    public static String[] DESCRIPTIONS = {
        "Mainly kitchen objects: Refrigerators, stoves, dishwashers, microwaves, coffee makers, food processor, garbage cans, barbecues and trash compactors.",
        "Beds, chairs, armchairs, sofas, bar stools, benches and deck chairs.",
        "Plants, paintings, sculptures, mirrors, curtains, rugs, ornaments, etc.",
        "TVs, computers, stereos, clocks, burglar alarm, fire alarm, video games, phones, pinball machines, etc.",
        "Hobbies and skill items, party items, etc.",
        "Musical instruments, bookshelves, exercise equipment, play equipment, workbenches, other skill-building items, etc.",
        "Lamps, wall sconces, hanging lights.",
        "Toilets, sinks, showers, bathtubs and hot tubs.",
        "Bookshelves and dressers.",
        "Counters, kitchen tables, desks, side tables, coffee tables, shelves and displays (not bookshelves), cabinets, bars etc.",
        "Cars, bicycles, motorcycles, scooters, parking spaces, garage doors, and bike racks.",
        "Skill-building items (mirrors, weight machines, bookshelves),dressers, play equipment, party items, children\'s items, cars, pet items, garbage cans, etc.",
        "Pet Bowls, pet houses, chewable dog toys, scratching posts, horse training obstacles, horse stalls, haystack, etc.",
        "Barbecues, sporting goods, patio, balcony, backyard items, outdoor lighting.",
        "Toys, diapers, clothing, baby items, health",
        "Medicine, drugs, medical machines"
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
    private ArrayList<Card> cards;

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
        
        int transCount = randomInt(3, 25);
        for(int j = 0 ; j < transCount; j++){
            HashSet<Item> itemsSeen = new HashSet<Item>();
            int busIdx = randomInt(0, businesses.size()-1);
            Business business = this.businesses.get(busIdx);
            String[] city = generateCity();
            // 0 to 2000 days ago from today
            Date today = new Date();
            Date day = new Date(today.getTime() - randomInt(0, 2000)* 24 * 3600 * 1000l);
            Calendar todayCal = Calendar.getInstance();
            todayCal.setTime(today);
            Calendar dayCal = Calendar.getInstance();
            dayCal.setTime(day);

            boolean thisMonth = todayCal.get(Calendar.YEAR) == dayCal.get(Calendar.YEAR) &&
                todayCal.get(Calendar.MONTH) == dayCal.get(Calendar.MONTH);
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
            int r = randomInt(0, (sz - 1)/4);
            for (int i = 0; i < r; ++i) {
                Item currItem = items.get(randomInt(0, sz-1));
                double cost = (double)randomInt(5, 5000);
                int quantity = randomInt(1,5);
                if (thisMonth && !card.isChargeable(cost*quantity)) {
                    break;
                }
                // no duplicates in this transaction
                if (itemsSeen.add(currItem)) { 
                    TransactionItem tItem = new TransactionItem(
                        transaction,
                        currItem,
                        quantity,
                        cost
                    );
                    if (thisMonth && card.getClass() == CreditCard.class) {
                        card.charge(cost*quantity);
                    }
                    transactionItemRepo.save(tItem);
                    ti.add(tItem);
                }
            }
            transaction.setTransactionItems(ti);
            this.transactionRepo.save(transaction);
            business.addTransaction(transaction);
            this.businesses.set(busIdx, this.businessRepo.save(business));
            if (card.getClass() == DebitCard.class) {
                this.debitCardRepo.save((DebitCard)card);
            } else {
                this.creditCardRepo.save((CreditCard)card);
            }
        }
    }

    public void generateBusinesses() {
        System.out.println("Generating businesses");
        for (int i = 0; i < BUSINESS_NAME.length; ++i) {
            Business b = new Business(
                this.BUSINESS_NAME[i], (double)randomInt(1, 5),
                new HashSet<Transaction>(), new HashSet<BusinessAccount>()
            );
            this.businesses.add(this.businessRepo.save(b));
        }
    }

    public void generateBusinessAccounts(){
        System.out.println("Generating business accounts");
        for(Business b : businesses){
            Set<BusinessAccount> accountSet = new HashSet<BusinessAccount>();
            b.setAccounts(accountSet);
            BusinessAccount newAccount = new BusinessAccount(
                b.getName(), "123456", generatePhoneNumber(),
                b.getName().split(" ")[0]+"@gmail.com",
                randomInt(2020, 2025), randomInt(1,12), randomInt(1,30),
                "Partner", true, b);
            b.addAccount(newAccount);
            businessAccountRepo.save(newAccount);
        }
    }

    public void generateAccounts() {
        System.out.println("Generating accounts");
        generateFixedAccounts();

        //for (int i = 0; i < USERNAMES.length; ++i) {
        for (int i = 0; i < USERNAMES.length/5; ++i) {
            int gender = randomInt(0,1);
            CustomerAccount cust = new CustomerAccount(
                USERNAMES[i],
                "123456",
                generatePhoneNumber(),
                USERNAMES[i] + EMAIL_DOMAINS[randomInt(0,EMAIL_DOMAINS.length-1)],
                gender != 0 ?
                    FEMALE_FIRST_NAMES[randomInt(0, FEMALE_FIRST_NAMES.length-1)] :
                    MALE_FIRST_NAMES[randomInt(0, MALE_FIRST_NAMES.length-1)],
                LAST_NAMES[randomInt(0, LAST_NAMES.length-1)],
                LAST_NAMES[randomInt(0, LAST_NAMES.length-1)],
                Ethnicity.values()[randomInt(0, Ethnicity.values().length-1)],
                Gender.values()[gender],
                randomInt(1940, 2000), randomInt(1,12), randomInt(1,28),
                randomInt(30000,1000000)
            );
            this.customerRepo.save(cust);
        }
    }

    public void generateFixedAccounts() {
        ArrayList<CustomerAccount> customers = new ArrayList<CustomerAccount>();
        customers.add(new CustomerAccount(
            "myHeightIsHigherThanMyIQ",
            "123456",
            "1234567890",
            "therealdonald@gmail.com",
            "Donald",
            "John",
            "Trump",
            Ethnicity.WHITE,
            Gender.MALE,
            1946, 6, 14,
            400000)
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
        customers.add(new CustomerAccount(
            "mrturtles",
            "123456",
            "4157285109",
            "turtlesaregreat@gmail.com",
            "mister",
            "Theodore",
            "turtles",
            Ethnicity.OTHER,
            Gender.FEMALE,
            1992, 1, 23,
            59000)
        );
        for (CustomerAccount customer : customers) {
            this.customerRepo.save(customer);
        }
    }

    public void generateCards(CustomerAccount customer) {
            
            Set<Card> customerCards = new HashSet<Card>();

            int c_num = randomInt(1, 5);
            for (int i = 0; i < c_num; ++i) {
                CreditCard c = new CreditCard(
                    customer,
                    randomInt(2018, 2025),
                    randomInt(1,12),
                    randomInt(1,28),
                    randomInt(1000, 100000)
                );
                customerCards.add(this.creditCardRepo.save(c));
            }
            c_num = randomInt(1, 5);
            for (int i = 0; i < c_num; ++i) {
                DebitCard d = new DebitCard(
                    customer,
                    randomInt(2018, 2025),
                    randomInt(1,12),
                    randomInt(1,28),
                    randomInt(1000, 100000)
                );
                customerCards.add(this.debitCardRepo.save(d));
            }
            customer.setCards(customerCards);
            this.customerRepo.save(customer);

            for (Card c : customerCards) {
                generateTransactionItems(c);
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
            generateCards(acc);
        }
    }
}
