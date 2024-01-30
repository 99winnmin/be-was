package domain.qna.command.application;

public class QuestionCreateRequest {
    private String title;
    private String contents;

    public QuestionCreateRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
