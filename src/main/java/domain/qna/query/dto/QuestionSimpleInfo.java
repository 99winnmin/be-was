package domain.qna.query.dto;

public class QuestionSimpleInfo {
    private String questionSimpleInfoId;
    private String questionId;
    private String title;
    private String createdAt;
    private String writerId;

    public QuestionSimpleInfo(String questionSimpleInfoId, String questionId, String title,
        String createdAt, String writerId) {
        this.questionSimpleInfoId = questionSimpleInfoId;
        this.questionId = questionId;
        this.title = title;
        this.createdAt = createdAt;
        this.writerId = writerId;
    }

    public String getQuestionSimpleInfoId() {
        return questionSimpleInfoId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getWriterId() {
        return writerId;
    }
}
