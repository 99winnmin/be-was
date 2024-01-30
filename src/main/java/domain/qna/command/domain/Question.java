package domain.qna.command.domain;

public class Question {
    private String questionId;
    private String title;
    private String contents;
    private String createdAt;
    private String writerId;

    public Question(String questionId, String title, String contents, String createdAt,
        String writerId) {
        this.questionId = questionId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.writerId = writerId;
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

}
