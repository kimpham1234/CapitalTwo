package capitaltwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.ArrayList;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CreditCardRepository creditCardRepo;
    private final DebitCardRepository debitCardRepo;
    private final CustomerAccountRepository customerRepo;

    @Autowired
    public DatabaseLoader(CreditCardRepository creditRepo,
                          DebitCardRepository debitRepo,
                          CustomerAccountRepository customerRepo) {
        this.creditCardRepo = creditRepo;
        this.debitCardRepo = debitRepo;
        this.customerRepo = customerRepo;
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

        ArrayList<CreditCard> ccards = new ArrayList<CreditCard>();
        ccards.add(new CreditCard(new Date(2020, 10, 19), 1000));
        ccards.add(new CreditCard(new Date(2020, 10, 19), 2000));
        ccards.add(new CreditCard(new Date(2020, 10, 19), 1000));
        ccards.add(new CreditCard(new Date(2020, 10, 19), 3000));
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
