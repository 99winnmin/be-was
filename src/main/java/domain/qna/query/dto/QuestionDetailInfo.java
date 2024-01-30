package domain.qna.query.dto;

public class QuestionDetailInfo {
    private String questionDetailInfoId;
    private String questionId;
    private String title;
    private String contents;
    private String createdAt;
    private String writerId;
    private String writerName;

    public QuestionDetailInfo(String questionDetailInfoId, String questionId, String title,
        String contents, String createdAt, String writerId, String writerName) {
        this.questionDetailInfoId = questionDetailInfoId;
        this.questionId = questionId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.writerId = writerId;
        this.writerName = writerName;
    }

    public String getQuestionDetailInfoId() {
        return questionDetailInfoId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getWriterName() {
        return writerName;
    }


}
