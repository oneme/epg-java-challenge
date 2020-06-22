package com.tdcs.epg.repositories;


import com.tdcs.epg.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findRentsByUserIdAndReturnedIsNull(Long id);
}
