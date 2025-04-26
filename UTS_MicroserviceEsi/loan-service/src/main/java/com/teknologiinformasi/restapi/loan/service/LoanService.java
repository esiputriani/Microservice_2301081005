package com.teknologiinformasi.restapi.loan.service;

import com.teknologiinformasi.restapi.loan.model.Loan;
import com.teknologiinformasi.restapi.loan.respository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan loanDetails) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan tidak ditemukan dengan ID " + id));

        loan.setIdbuku(loanDetails.getIdbuku());
        loan.setNama(loanDetails.getNama());
        loan.setQuantity(loanDetails.getQuantity());
        loan.setStatus(loanDetails.getStatus());

        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan tidak ditemukan dengan ID " + id));
        loanRepository.delete(loan);
    }
}
