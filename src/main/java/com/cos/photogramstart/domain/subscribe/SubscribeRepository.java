package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

        @Modifying //INSERT,DELETE,UPDATE를 네이티브 쿼리로 작성하려면 필요
        @Query(value="INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,:now())",nativeQuery=true)
        int mSubscribe(int fromUserId,int toUserId);

        @Modifying //INSERT,DELETE,UPDATE를 네이티브 쿼리로 작성하려면 필요
        @Query(value="DELETE FROM subscribe WHERE fromUserid=:fromUserId AND toUserId=:toUserId",nativeQuery=true)
        int mUnSubscribe(int fromUserId,int toUserId);

        @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId=:principalId AND toUserId=:pageUserId",nativeQuery = true)
        int mSubscribState(int principalId, int pageUserId);

        @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId=:pageUserId",nativeQuery = true)
        int mSubscribeCount(int pageUserId);


}
