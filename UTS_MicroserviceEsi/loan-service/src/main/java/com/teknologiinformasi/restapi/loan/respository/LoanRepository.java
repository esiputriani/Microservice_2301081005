package com.teknologiinformasi.restapi.loan.respository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.loan.model.Loan;
 

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}