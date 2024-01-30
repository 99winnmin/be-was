document.addEventListener('DOMContentLoaded', function() {
    var sid = getCookie('sid');
    if (sid) {
        // 쿠키 'sid'가 존재하면 서버에 사용자 이름 요청
        fetch('/user/my-info', {
            method: 'GET', // 또는 'POST', 서버 구현에 따라 달라질 수 있음
            credentials: 'include' // 쿠키를 포함시키기 위해 필요
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
                return response.json();
        })
        .then(data => {
            // 서버로부터 받은 사용자 이름으로 화면 업데이트
            document.getElementById('userNameDisplay').querySelector('a').textContent = data.userName;
            document.getElementById('userNameDisplay').style.display = 'block';
            document.getElementById('logoutButton').style.display = 'block';
            document.getElementById('profileButton').style.display = 'block';
        })
        .catch(error => console.error('Error fetching user name:', error));
    } else {
        // 쿠키 'sid'가 존재하지 않으면 로그인 버튼 표시
        document.getElementById('loginButton').style.display = 'block';
        document.getElementById('signupButton').style.display = 'block';
    }
});

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}
