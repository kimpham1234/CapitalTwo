/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.sql.Date;


/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository repository;
	private final CustomerAccountRepository customerRepo;
    private final CreditCardRepository creditCardRepo;
    private final DebitCardRepository debitCardRepo;

	@Autowired
	public DatabaseLoader(EmployeeRepository repository,
						  CustomerAccountRepository customerRepo,
                          CreditCardRepository creditRepo,
                          DebitCardRepository debitRepo) {
		this.repository = repository;
		this.customerRepo = customerRepo;
        this.creditCardRepo = creditRepo;
        this.debitCardRepo = debitRepo;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
		
		this.customerRepo.save(new CustomerAccount("SJ", "123456", "4087138244", "email1@email.com", "Smith Johnson",
                             "Afican American", "Male", new Date(1984, 10, 23), 50000));
        this.customerRepo.save(new CustomerAccount("WB", "123456", "4086136224", "email2@email.com", "William Brown",
                             "Caucasian", "Male", new Date(1985, 6, 12), 45000));
        this.customerRepo.save(new CustomerAccount("MJ", "123456", "4087134294", "email3@email.com", "Mary Jones",
                             "Caucasian", "Female", new Date(1990, 11, 14), 57000));
        this.customerRepo.save(new CustomerAccount("AW", "123456", "4088133264", "email4@email.com", "Anna Wong",
                             "Asian", "Female", new Date(1980, 1, 23), 59000));

        // public CreditCard(Date expirationDate, String loginId, double monthlyLimit) 
        this.creditCardRepo.save(new CreditCard(new Date(2020, 10, 19), "SJ", 1000));
        this.creditCardRepo.save(new CreditCard(new Date(2020, 10, 19), "WB", 2000));
        this.creditCardRepo.save(new CreditCard(new Date(2020, 10, 19), "MJ", 1000));
        this.creditCardRepo.save(new CreditCard(new Date(2020, 10, 19), "AW", 3000));

        //public DebitCard(Date expirationDate, String loginId, double balance) {
        this.debitCardRepo.save(new DebitCard(new Date(2020, 10, 19), "SJ", 10000));
        this.debitCardRepo.save(new DebitCard(new Date(2020, 10, 19), "WB", 20000));
        this.debitCardRepo.save(new DebitCard(new Date(2020, 10, 19), "MJ", 10000));
        this.debitCardRepo.save(new DebitCard(new Date(2020, 10, 19), "AW", 30000));

	}
}
// end::code[]