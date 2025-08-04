package com.ktdsuniversity.proj.dadada.chat.dao;

import org.springframework.stereotype.Repository;
import com.ktdsuniversity.proj.dadada.chat.vo.ParticipantRankVO;
import com.ktdsuniversity.proj.dadada.chat.vo.ParticipantVO;

@Repository
public interface ParticipantDAO {
    void insertParticipant(ParticipantVO participant);

    /**
     * 참가자 순위 업데이트
     * @param rankVo 순위 정보 VO
     */
    void updateParticipantRank(ParticipantRankVO rankVo);
}
