document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('submitQuestion').addEventListener('click', function() {
        const title = document.getElementById('title').value;
        const contents = document.getElementById('contents').value;

            // 파일 경로를 포함하여 /qna/save API로 최종 데이터 전송
        fetch('/qna/save', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
                // 필요한 경우 추가 헤더를 여기에 추가하세요.
            },
            body: JSON.stringify({
                title: title,
                contents: contents
            })
        })
        .then(response => {
            // 최종 요청 성공 후 처리, 예를 들어 질문 목록 페이지로 리다이렉션
            window.location.href = '/index.html'; // 성공 시 리다이렉션할 페이지 주소
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            // 에러 처리 로직
        });
    });
});
