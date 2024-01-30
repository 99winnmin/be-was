package domain.qna.query.dao;

import domain.qna.query.dto.QuestionDetailInfo;
import domain.qna.query.dto.QuestionSimpleInfo;
import java.util.List;
import java.util.Optional;

public interface QuestionDao {
    void addQuestionSimpleInfo(QuestionSimpleInfo questionSimpleInfo);
    void addQuestionDetailInfo(QuestionDetailInfo questionDetailInfo);

    Optional<QuestionSimpleInfo> findQuestionSimpleInfoByQuestionId(String questionSimpleInfoId);
    Optional<QuestionDetailInfo> findQuestionDetailInfoByQuestionId(String questionDetailInfoId);

    List<QuestionSimpleInfo> findAllQuestionSimpleInfo();
}
