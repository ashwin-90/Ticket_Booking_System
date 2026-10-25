package com.example.inventoryservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.inventoryservice.Entity.Event;

@Repository
public interface EventRepository extends JpaRepository< Event , Long> {

}
