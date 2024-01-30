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
                    // 응답 상태 코드가 404인 경우 /error/404.html로 리다이렉션
                    if (response.status === 404) {
                        window.location.href = '/error/404.html';
                        return;
                    } else if (response.status === 400) {
                        window.location.href = '/error/400.html';
                        return;
                    } else if (response.status === 500) {
                        window.location.href = '/error/500.html';
                        return;
                    }
                    // 응답이 정상적인 경우, JSON으로 파싱하여 처리
                    window.location.href = '/index.html';
                    return;
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            // 에러 처리 로직
        });
    });
});
