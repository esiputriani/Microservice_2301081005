package com.teknologiinformasi.restapi.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.loan.model.Loan;
import com.teknologiinformasi.restapi.loan.service.LoanService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private static final Logger log = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    // GET semua loan
    @GetMapping
    public List<Loan>getAllLoans() {
        log.info("GET /api/loans accessed");
        return loanService.getAll();
    }

    // GET loan berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        log.info("GET /api/loans/{} accessed", id);
        return loanService.getById(id)
                .map(loan -> ResponseEntity.ok(loan))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST membuat loan baru
    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        log.info("POST /api/loans accessed");
        return loanService.createLoan(loan);
    }

    // PUT update loan yang ada
    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        log.info("PUT /api/loans/{} accessed", id);
        try {
            Loan updatedLoan = loanService.updateLoan(id, loanDetails);
            return ResponseEntity.ok(updatedLoan);
        } catch (RuntimeException e) {
            log.error("loan with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE loan
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id) {
        log.info("DELETE /api/loans/{} accessed", id);
        try {
            loanService.deleteLoan(id);
            return ResponseEntity.ok("loan deleted successfully");
        } catch (RuntimeException e) {
            log.error("Failed to delete loan with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}