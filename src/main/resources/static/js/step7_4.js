document.addEventListener('DOMContentLoaded', function() {
    // localStorage에서 질문 ID 가져오기
    const questionId = localStorage.getItem('currentQuestionId');

    // 질문 ID가 없을 경우 처리, 예: 에러 메시지 표시 또는 이전 페이지로 리다이렉트
    if (!questionId) {
        console.error('Question ID not found.');
        // 여기에 에러 처리 로직 추가, 예: window.location.href = 'index.html';
        return;
    }

    // API 호출을 통해 질문 상세 정보 가져오기
    fetch(`/qna/detail?questionId=${questionId}`, {
        method: 'GET',
        credentials: 'include' // 쿠키 등의 인증 정보를 포함시키기 위함
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success: ', data);
        // 페이지에 데이터 채우기
        document.getElementById('questionTitle').textContent = data.title;
        document.getElementById('writerName').textContent = data.writerName;
        document.getElementById('createdAt').textContent = data.createdAt;
        document.getElementById('questionContents').textContent = data.contents;
    })
    .catch(error => {
        console.error('Error fetching question details:', error);
        // 에러 처리 로직, 예: 사용자에게 에러 메시지 표시
    });
});
