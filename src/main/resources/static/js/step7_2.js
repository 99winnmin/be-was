document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('askQuestionBtn').addEventListener('click', function() {
        // 쿠키에서 sid 값을 읽어옵니다.
        const sid = getCookie('sid');

        // sid 값이 있으면 질문하기 페이지로, 없으면 로그인 페이지로 이동합니다.
        if (sid) {
            window.location.href = './qna/form.html'; // 질문하기 페이지의 URL로 변경하세요.
        } else {
            window.location.href = 'user/login.html'; // 로그인 페이지의 URL로 변경하세요.
        }
    });

    // 쿠키에서 특정 이름의 값을 가져오는 함수입니다.
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
    }
});
