package com.tdcs.epg.servicesImp;

import com.tdcs.epg.common.Constants;
import com.tdcs.epg.common.RentingItem;
import com.tdcs.epg.common.Utils;
import com.tdcs.epg.entities.Rent;
import com.tdcs.epg.entities.User;
import com.tdcs.epg.entities.VideoGame;
import com.tdcs.epg.repositories.RentRepository;
import com.tdcs.epg.repositories.UserRepository;
import com.tdcs.epg.repositories.VideoGameRepository;
import com.tdcs.epg.services.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImp implements RentService {

    private final RentRepository rentRepository;

    private final VideoGameRepository videoGameRepository;

    private final UserRepository userRepository;

    public RentServiceImp(RentRepository rentRepository, VideoGameRepository videoGameRepository, UserRepository userRepository) {
        this.rentRepository = rentRepository;
        this.videoGameRepository = videoGameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> rent(Long id, List<RentingItem> rentingItems) {
        final int[] result = {Constants.CODE_RENT_SUCCESS};
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            rentingItems.forEach(ri -> {
                Rent rent = new Rent();
                User u = user.get();
                Optional<VideoGame> videoGame = videoGameRepository.findById(ri.getVideoGameId());
                if (videoGame.isPresent()) {
                    VideoGame v = videoGame.get();
                    if (!v.isStatus()) {
                        if(ri.getDays() >= 1){
                            int lp = v.getType().getId() == 1 ? Constants.PREMIUM_LOYALTY_POINTS
                                    : Constants.BASIC_LOYALTY_POINTS;
                            u.setLoyaltyPoints(u.getLoyaltyPoints() + lp);
                            userRepository.save(u);
                            rent.setUser(u);
                            rent.setVideoGame(v);
                            rent.setRented(LocalDate.now().toString());
                            rent.setPromisedReturn(LocalDate.now().plusDays(ri.getDays()).toString());
                            v.setStatus(Boolean.TRUE);
                            videoGameRepository.save(v);
                            rentRepository.save(rent);
                        }else {
                            result[0] = Constants.CODE_NO_VALID_DAYS_AMOUNT;
                        }
                    } else {
                        result[0] = Constants.CODE_VIDEO_GAME_NOT_AVAILABLE;
                    }
                } else {
                    result[0] = Constants.CODE_VIDEO_GAME_NOT_FOUND;
                }
            });
        } else {
            result[0] = Constants.CODE_USER_NOT_FOUND;
        }
        return Utils.getResponse(result[0]);
    }

    @Override
    public ResponseEntity<?> calculateRentPrice(List<RentingItem> rentingItems) {
        final ResponseEntity<?>[] response = new ResponseEntity<?>[1];
        List<RentingItem> list = new ArrayList<>();
        rentingItems.forEach(ri -> {
                    Optional<VideoGame> videoGame = videoGameRepository.findById(ri.getVideoGameId());
                    if(videoGame.isPresent()){
                        VideoGame v = videoGame.get();
                        if(ri.getDays() >= 0){
                            list.add(new RentingItem(ri.getVideoGameId(), ri.getDays(),
                                    Utils.calculateRentPrice(v.getType().getId().intValue(), ri.getDays().intValue())));
                            response[0] = new ResponseEntity<>(list, HttpStatus.OK);
                        }else{
                            response[0] = new ResponseEntity<>(Constants.NO_VALID_DAYS_AMOUNT, HttpStatus.BAD_REQUEST);
                        }
                    }else{
                        response[0] = new ResponseEntity<>(Constants.VIDEO_GAME_NOT_FOUND, HttpStatus.BAD_REQUEST);
                    }
                }
        );
        return response[0];
    }

    @Override
    public ResponseEntity<?> getRentedVideoGamesByUser(Long id) {
        ResponseEntity<?> response;
        Optional<User> user = userRepository.findById(id);
        List<Rent> list;
        if(user.isPresent()){
            list = rentRepository.findRentsByUserIdAndReturnedIsNull(id);
            if(list.isEmpty()){
                response = new ResponseEntity<>(Constants.NO_RENTS_FOUND_FOR_THIS_USER, HttpStatus.BAD_REQUEST);
            }else{
                response = new ResponseEntity<>(list, HttpStatus.OK);
            }
        }else{
            response = new ResponseEntity<>(Constants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> giveBack(Long id, List<RentingItem> rentingItems) {
        final int[] result = {Constants.CODE_RETURN_SUCCESS};
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (!rentRepository.findRentsByUserIdAndReturnedIsNull(id).isEmpty()) {
                rentingItems.forEach(ri -> {
                    Optional<Rent> rentedVideoGame = rentRepository.findRentsByUserIdAndReturnedIsNull(id)
                            .stream()
                            .filter(r -> r.getVideoGame().getId().equals(ri.getVideoGameId()))
                            .findAny();
                    if (rentedVideoGame.isPresent()) {
                        Rent r = rentedVideoGame.get();
                        if(ri.getDays() >= 0){
                            r.setReturned(LocalDate.now().plusDays(ri.getDays()).toString());
                            rentRepository.save(r);
                            videoGameRepository.findById(ri.getVideoGameId())
                                    .ifPresent(v -> {
                                        v.setStatus(Boolean.FALSE);
                                        videoGameRepository.save(v);
                                    });
                        }else {
                            result[0] = Constants.CODE_NO_VALID_DAYS_AMOUNT;
                        }
                    } else {
                        result[0] = Constants.CODE_VIDEO_GAME_NOT_FOUND;
                    }
                });
            } else {
                result[0] = Constants.CODE_NO_RENTS_FOUND_FOR_THIS_USER;
            }
        } else {
            result[0] = Constants.CODE_USER_NOT_FOUND;
        }
        return Utils.getResponse(result[0]);
    }

}
