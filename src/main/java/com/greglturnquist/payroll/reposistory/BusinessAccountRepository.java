package com.greglturnquist.payroll;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BusinessAccountRepository extends BaseAccountRepository<BusinessAccount> {

}
