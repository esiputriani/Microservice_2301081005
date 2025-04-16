package com.teknologiinformasi.restapi.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.teknologiinformasi.restapi.payment.model.Payment;
import com.teknologiinformasi.restapi.payment.service.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    // GET semua payment
    @GetMapping
    public List<Payment> getAllPayments() {
        log.info("GET /api/payments accessed");
        return paymentService.getAll();
    }

    // GET payment berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        log.info("GET /api/payments/{} accessed", id);
        return paymentService.getById(id)
                .map(payment -> ResponseEntity.ok(payment))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST membuat payment baru
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        log.info("POST /api/payments accessed");
        return paymentService.createPayment(payment);
    }

    // PUT update payment yang ada
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        log.info("PUT /api/payments/{} accessed", id);
        try {
            Payment updatedPayment = paymentService.updatePayment(id, paymentDetails);
            return ResponseEntity.ok(updatedPayment);
        } catch (RuntimeException e) {
            log.error("Payment with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE payment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        log.info("DELETE /api/payments/{} accessed", id);
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok("Payment deleted successfully");
        } catch (RuntimeException e) {
            log.error("Failed to delete payment with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
