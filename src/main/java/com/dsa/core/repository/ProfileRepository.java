package com.dsa.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsa.core.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findByUserId(Long userId);
}
