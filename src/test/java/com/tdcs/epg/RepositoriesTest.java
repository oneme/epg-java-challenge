package com.tdcs.epg;


import com.tdcs.epg.entities.Rent;
import com.tdcs.epg.entities.User;
import com.tdcs.epg.entities.VideoGame;
import com.tdcs.epg.repositories.RentRepository;
import com.tdcs.epg.repositories.UserRepository;
import com.tdcs.epg.repositories.VideoGameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoriesTest {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private VideoGameRepository videoGameRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenInitializedByDbUnit_thenRentsAreEmpty() {
        List<Rent> rents = rentRepository.findAll();
        assertThat(rents.size()).isEqualTo(0);
    }

    @Test
    void whenInitializedByDbUnit_thenFindRentsByUserIdIsZero() {
        List<Rent> rents = rentRepository.findRentsByUserIdAndReturnedIsNull(1L);
        assertThat(rents.size()).isEqualTo(0);
    }

    @Test
    void whenInitializedByDbUnit_thenUsersHasEntries() {
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    void whenInitializedByDbUnit_thenVideoGamesHasEntries() {
        List<VideoGame> videoGames = videoGameRepository.findAll();
        assertThat(videoGames.size()).isGreaterThan(0);
    }

    @Test
    void whenInitializedByDbUnit_thenVideoGamesHasAvailableEntries() {
        List<VideoGame> videoGames = videoGameRepository.findVideoGamesByStatusIsFalse();
        assertThat(videoGames.size()).isGreaterThan(0);
    }
}
