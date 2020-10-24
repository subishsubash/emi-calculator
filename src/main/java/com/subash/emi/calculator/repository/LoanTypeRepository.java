package com.subash.emi.calculator.repository;

import com.subash.emi.calculator.model.LoanType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypeRepository extends MongoRepository<LoanType, String> {
}
