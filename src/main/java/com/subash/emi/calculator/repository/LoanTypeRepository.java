package com.subash.emi.calculator.repository;

import com.subash.emi.calculator.model.LoanType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * LoanType repository
 *
 * @author subash s
 */
@Repository
public interface LoanTypeRepository extends MongoRepository<LoanType, String> {
}
