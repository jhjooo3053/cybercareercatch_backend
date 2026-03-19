document.addEventListener("DOMContentLoaded", function () {
    const calendarEl = document.getElementById("calendar");

    const calMonthNum = document.getElementById("calMonthNum");
    const calMonthText = document.getElementById("calMonthText");
    const calYearText = document.getElementById("calYearText");

    const prevMonthBtn = document.getElementById("prevMonth");
    const nextMonthBtn = document.getElementById("nextMonth");

    const expoTitle = document.getElementById("expoTitle");
    const expoLocation = document.getElementById("expoLocation");
    const expoBody = document.getElementById("expoBody");

    const monthTextList = [
        "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
        "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
    ];

    function updateCustomHeader(date) {
        const year = date.getFullYear();
        const month = date.getMonth();

        calMonthNum.textContent = month + 1;
        calMonthText.textContent = monthTextList[month];
        calYearText.textContent = year;
    }

    function addOneDay(dateString) {
        const date = new Date(dateString);
        date.setDate(date.getDate() + 1);

        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
    }

    function renderEmptyDetail(message) {
        expoTitle.textContent = "박람회 정보를 찾을 수 없습니다.";
        expoLocation.textContent = "";
        expoBody.innerHTML = `<div class="expo-company-empty">${message}</div>`;
    }

    function renderExpoDetail(data) {
        expoTitle.textContent = data.expoName || "박람회 정보를 찾을 수 없습니다.";
        expoLocation.textContent = data.location || "";

        if (!data.companies || data.companies.length === 0) {
            expoBody.innerHTML = `<div class="expo-company-empty">참가 기업 정보가 없습니다.</div>`;
            return;
        }

        let html = "";

        data.companies.forEach(company => {
            html += `
                <div class="expo-company-row">
                    <div class="expo-company-name">${company.companyName}</div>
                    <div class="expo-company-address">${company.companyAddress}</div>
                </div>
            `;
        });

        expoBody.innerHTML = html;
    }

    function loadExpoDetail(expoNumber) {
        fetch(`${contextPath}/expo/detail.ex?expoNumber=${expoNumber}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("상세 조회 실패");
                }
                return response.json();
            })
            .then(data => {
                renderExpoDetail(data);
            })
            .catch(error => {
                console.error(error);
                renderEmptyDetail("상세 정보를 불러오지 못했습니다.");
            });
    }

    function loadExpoCalendarList() {
        fetch(`${contextPath}/expo/list.ex`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("일정 목록 조회 실패");
                }
                return response.json();
            })
            .then(expoList => {
                const calendar = new FullCalendar.Calendar(calendarEl, {
                    initialView: "dayGridMonth",
                    locale: "ko",
                    height: "auto",
                    fixedWeekCount: false,
                    showNonCurrentDates: true,
                    headerToolbar: false,

                    events: expoList.map(expo => ({
                        id: String(expo.expoNumber),
                        title: expo.expoName,
                        start: expo.startDate,
                        end: addOneDay(expo.endDate),
                        display: "block"
                    })),

                    datesSet: function (info) {
                        updateCustomHeader(info.view.currentStart);
                    },

                    eventClick: function (info) {
                        loadExpoDetail(info.event.id);
                    }
                });

                calendar.render();

                prevMonthBtn.addEventListener("click", function () {
                    calendar.prev();
                });

                nextMonthBtn.addEventListener("click", function () {
                    calendar.next();
                });
            })
            .catch(error => {
                console.error(error);
                renderEmptyDetail("박람회 일정을 불러오지 못했습니다.");
            });
    }

    loadExpoCalendarList();
});