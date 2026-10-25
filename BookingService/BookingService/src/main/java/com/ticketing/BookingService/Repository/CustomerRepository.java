package com.ticketing.BookingService.Repository;

import com.ticketing.BookingService.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Long> {
}
