document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        const formData = {
            userId: document.getElementById('userId').value,
            password: document.getElementById('password').value
        };

        // fetch API를 사용하여 서버에 비동기 요청
        fetch('/user/login', {
            method: 'POST',
            body: JSON.stringify(formData)
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
            window.location.href = '/index.html';
            return;
        })
//        .then(data => {
//            // 로그인 성공 후의 처리 (예: 사용자 알림, 페이지 리다이렉션 등)
//            console.log('Success:', data);
//            window.location.href = '/index.html'; // 성공 시 리다이렉션할 페이지 주소
//        })
        .catch((error) => {
            console.error('Error:', error);
            alert('로그인 실패: 아이디나 비밀번호를 확인해주세요.');
        });
    });
});
