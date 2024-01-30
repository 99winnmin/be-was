document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('signupForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        // 폼 데이터 수집
        const formData = {
            userId: document.getElementById('userId').value,
            password: document.getElementById('password').value,
            name: document.getElementById('name').value,
            email: document.getElementById('email').value
        };

        // fetch API를 사용하여 서버에 비동기 요청
        fetch('/user/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // JSON 형태로 데이터를 전송하도록 설정
            },
            body: JSON.stringify(formData) // 폼 데이터를 JSON 문자열로 변환
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
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/index.html';
            return;
        })
//        .then(data => {
//            // 성공적으로 처리된 후의 동작 (예: 사용자 알림, 페이지 리다이렉션 등)
//            console.log('Success:', data);
//            alert('회원가입이 완료되었습니다.');
//            window.location.href = '/index.html'; // 필요한 경우 리다이렉션
//        })
        .catch((error) => {
            console.error('Error:', error);
            alert('오류가 발생했습니다. 다시 시도해주세요.');
        });
    });
});
