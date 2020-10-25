package com.subash.emi.calculator.repository;

import com.subash.emi.calculator.model.CalculatorSpecs;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CalculatorSpecs repository
 *
 * @author subash s
 */
public interface CalculatorSpecsRepository extends MongoRepository<CalculatorSpecs, String> {
}
