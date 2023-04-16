package io.skai.reservation.repository;

import io.skai.reservation.model.PassengerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerHibernateRepository extends JpaRepository<PassengerModel, Long> {
}