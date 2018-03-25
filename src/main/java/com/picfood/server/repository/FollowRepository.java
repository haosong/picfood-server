package com.picfood.server.repository;

import com.picfood.server.entity.Follow;
import com.picfood.server.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shawn on 2018/3/21.
 */
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    List<Follow> findAllByFollowee(String id);

    List<Follow> findAllByFollower(String id);

    List<Follow> findAllByFollowerAndFollowee(String followerId, String followeeId);

}
