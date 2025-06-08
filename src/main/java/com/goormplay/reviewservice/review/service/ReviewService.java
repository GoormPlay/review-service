package com.goormplay.reviewservice.review.service;

import com.goormplay.reviewservice.review.dto.CreateReviewRequest;
import com.goormplay.reviewservice.review.dto.ReviewResponse;
import com.goormplay.reviewservice.review.dto.UpdateReviewRequest;
import com.goormplay.reviewservice.review.entity.Review;
import com.goormplay.reviewservice.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse> getReviews(String videoId) {
        return reviewRepository.findByVideoId(videoId).stream()
                .map(ReviewResponse::of)
                .collect(Collectors.toList());
    }

    public void createReview(String videoId, CreateReviewRequest request) {

        reviewRepository.save(Review.builder().id(UUID.randomUUID().toString())
                .videoId(videoId)
                        .username(request.getUsername())
                .userId(request.getUserId())
                .comment(request.getComment())
                .rating(request.getRating())
                .build());
    }

    public void updateReview(UpdateReviewRequest request) {
        Review review = reviewRepository.findByIdAndUserId(request.getId(), request.getUserId())
                .orElseThrow();
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        reviewRepository.save(review);
    }

    public void deleteReview(String reviewId, String userId) {
        reviewRepository.deleteByIdAndUserId(reviewId, userId);
    }
}