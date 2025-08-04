<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>결제 완료</title>
    <script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>

    <c:url var="shopCreateUrl" value="/shoplaylist/api/create"/>
    <c:url var="shopListUrl" value="/shoplaylist/list"/>
    <c:url var="mypageOrderList" value="/mypage/order-list"/>

    <style>
        .payment-modal {
            position: fixed;
            top: 0; left: 0;
            width: 100%; height: 100%;
            background: rgba(0, 0, 0, 0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1000;
        }

        .payment-modal-content {
            background: #fff;
            width: 400px;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
        }

        .payment-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        .payment-table th,
        .payment-table td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        .btn-confirm {
            background-color: #F44336;
            color: white;
            font-weight: bold;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 20px;
        }
    </style>

    <script>
        $(function () {
            let status = "${info.status}";
            if (status === "paid") {
                status = "결제 완료";
            }
            $("#paymentStatus").text(status);
            $("#paymentAmount").text("${info.paidAmount}");

            $("#paymentModal").fadeIn();

            $("#modalConfirm").click(function () {
                $("#paymentModal").fadeOut();

                const params = new URLSearchParams(location.search);
                if (params.get("askPackage") === "true") {
                    const raw = sessionStorage.getItem("packageItems") || "[]";
                    let items = [];
                    try { items = JSON.parse(raw); } catch (e) { console.error(e); }

                    if (items.length && confirm("이 상품을 패키지화 하시겠습니까?")) {
                        const name = prompt("패키지명을 입력하세요");
                        if (!name) {
                            location.href = "${mypageOrderList}";
                            return;
                        }

                        $.ajax({
                            url: "${shopCreateUrl}",
                            method: "POST",
                            contentType: "application/json",
                            data: JSON.stringify({
                                packageName: name,
                                productIds: items.map(i => i.prdId)
                            }),
                            success: function () {
                                alert("패키지 생성 완료");
                                location.href = "${shopListUrl}";
                            },
                            error: function () {
                                alert("패키지 생성 실패");
                                location.href = "${mypageOrderList}";
                            }
                        });
                    } else {
                        location.href = "${mypageOrderList}";
                    }
                } else {
                    location.href = "${mypageOrderList}";
                }
            });
        });
    </script>
</head>
<body>

<div id="paymentModal" class="payment-modal" style="display:none;">
    <div class="payment-modal-content">
        <h2>결제 정보</h2>
        <table class="payment-table">
            <tr>
                <th>상태</th>
                <td><span id="paymentStatus">결제 완료</span></td>
            </tr>
            <tr>
                <th>금액</th>
                <td><span id="paymentAmount"></span>원</td>
            </tr>
        </table>
        <button type="button" id="modalConfirm" class="btn-confirm">확인</button>
    </div>
</div>

</body>
</html>
