package com.goormplay.reviewservice.review.repository;

import com.goormplay.reviewservice.review.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByVideoId(String videoId);
    void deleteByIdAndUserId(String id, String userId);

    Optional<Review> findByIdAndUserId(String id, String userId);
}
