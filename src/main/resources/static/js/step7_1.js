function fetchQnaList() {
    fetch('/qna/list', {
        method: 'GET',
        credentials: 'include'
    })
    .then(response => response.json()) // response.json()을 여기서 처리합니다.
    .then(data => {
        console.log('Success: ', data);
        const qnaTable = document.getElementById('qnaTable').getElementsByTagName('tbody')[0];
        qnaTable.innerHTML = ''; // 기존 테이블 내용을 지우고 새로운 내용으로 대체합니다.

        // 데이터가 비어 있을 경우 메시지 출력
        if (data.length === 0) {
            const row = qnaTable.insertRow();
            const cell = row.insertCell(0);
            cell.innerHTML = '데이터가 없어요';
            cell.colSpan = 4; // 테이블 열 개수에 맞게 colspan 조정
            cell.style.textAlign = 'center'; // 텍스트 가운데 정렬
            return; // 데이터가 없으면 여기서 함수 종료
        }

        // 데이터가 있는 경우 각 항목을 테이블에 추가
        data.forEach(item => {
            const row = qnaTable.insertRow();
            const cellQuestionId = row.insertCell(0);
            const cellTitle = row.insertCell(1);
            const cellCreatedAt = row.insertCell(2);
            const cellWriterId = row.insertCell(3);

            const a = document.createElement('a');
                a.textContent = item.questionId;
                a.href = '#'; // 실제 이동할 주소 대신 '#' 사용, 실제 동작은 JavaScript에서 처리
                a.addEventListener('click', function(e) {
                    e.preventDefault();

                    // 클릭 이벤트 내부에서 쿠키 검사 로직 추가
                    const sid = getCookie('sid');
                    if (!sid) {
                        window.location.href = 'user/login.html'; // 로그인 페이지 경로에 맞게 조정
                        return;
                    }

                    console.log('질문 ID 클릭:', item.questionId);
                    localStorage.setItem('currentQuestionId', item.questionId);
                    window.location.href = 'qna/detail.html';
                });
            cellQuestionId.appendChild(a);
//            cellQuestionId.innerHTML = item.questionId;
            cellTitle.innerHTML = item.title;
            cellCreatedAt.innerHTML = item.createdAt;
            cellWriterId.innerHTML = item.writerId;
        });
    })
    .catch(error => console.error('Error fetching data: ', error));
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

document.addEventListener('DOMContentLoaded', fetchQnaList);
