<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 11/27/2020
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product detail</title>
    <link href="${pageContext.request.contextPath}/Style/PDstyle.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Oswald:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
<div class="PDmain">
    <div class="PDBox" id="PDFoodImageBox">
        <img class="PDFoodImgae" src="${pageContext.request.contextPath}/Images/COM-SUON-NUONG.jpeg">
    </div>
    <div class="PDBox" id="PDFoodMainMenu">
        <div class="PD-box-title"><strong>Combo sườn trứng đặc biệt</strong></div>
        <div class="PD-main-menu-option">
            <div class="PD-main-menu-option-title"><strong>Món ăn chính</strong></div>
            <div class="PD-main-menu-option-multi-option">
                <input type="radio" checked name="MonAnChinh" value="Cơm Sườn Non"
                       class="PD-main-menu-option-check" id="PD-main-menu-option-line-1-1">
                <label for="PD-main-menu-option-line-1-1">sườn nướng + Trứng óp la</label>
            </div>
        </div>
        <div class="PD-main-menu-option">
            <div class="PD-main-menu-option-title"><strong>Món canh chính</strong></div>
            <div class="PD-main-menu-option-multi-option">
                <input type="radio" checked="" name="MonCanhChinh" value="Canh rong biển"
                       class="PD-main-menu-option-check" id="PD-main-menu-option-line-2-1">
                <label for="PD-main-menu-option-line-2-1">Canh rong biển</label>
            </div>
        </div>
        <div class="PD-main-menu-option">
            <div class="PD-main-menu-option-title"><strong>Nước đi kèm</strong></div>
            <div class="PD-main-menu-option-multi-option">
                <input type="radio" checked="" name="NuocDiKem" value="Coca-cola"
                       class="PD-main-menu-option-check" id="PD-main-menu-option-line-3-1">
                <label for="PD-main-menu-option-line-3-1">Coca-cola</label>
            </div>
            <div class="PD-main-menu-option-multi-option">
                <input type="radio" checked="" name="NuocDiKem" value="Coca-cola"
                       class="PD-main-menu-option-check" id="PD-main-menu-option-line-3-2">
                <label for="PD-main-menu-option-line-3-2">Trà bí đao hạt chia</label>
            </div>
            <div class="PD-main-menu-option-multi-option">
                <input type="radio" checked="" name="NuocDiKem" value="Coca-cola"
                       class="PD-main-menu-option-check" id="PD-main-menu-option-line-3-3">
                <label for="PD-main-menu-option-line-3-3">Sprite</label>
            </div>
        </div>
        <div class="PD-main-menu-option">
            <div class="PD-main-menu-option-title"><strong>Khăn Lạnh đi kèm</strong></div>
        </div>
        <div class="PD-main-menu-option">
            <div class="PD-main-menu-option-title"><strong>Ghi chú cho nhân viên</strong></div>
            <input class="PD-main-menu-option-text" type="text" maxlength="3000"
                   name="GhiChuChoNhanVien" size="30" placeholder="Ghi chú cho nhân viên">
        </div>
        <div class="PD-main-menu-option-quantity">
            <button class="PD-main-menu-option-quantity-dec-button">-</button>
            <div class="PD-main-menu-option-quantity-number">1</div>
            <button class="PD-main-menu-option-quantity-inc-button">+</button>
        </div>
        <div class="PD-main-menu-form">
            <form  name="PDMainMenuForm" action="#">
                <input class="btn btn-danger PD-main-menu-submit-button" type="submit" value="ĐẶT HÀNG" name="DatHang" id="Submit-button-1">
            </form>
            <div class="PD-main-menu-total-price">99,000</div>
        </div>

    </div>

    <div class="PDBox" id="PDFoodOption">
        <div class="PD-box-title"><strong>Càng ăn càng ngon</strong></div>
        <div class="PD-extra-option">
            <div class="PD-extra-option-title"><strong>Món ăn kèm 02</strong></div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox" name="MonAnKem02"
                       class="PD-extra-option-check" id="PD-extra-option-line-1-2">
                <label for="PD-extra-option-line-1-2">Chả trứng</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <span class="PD-extra-option-quantity">1</span>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox"  name="MonAnKem02"
                       class="PD-extra-option-check" id="PD-extra-option-line-1-3">
                <label for="PD-extra-option-line-1-3">Trứng óp la</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox"  name="MonAnKem02"
                       class="PD-extra-option-check" id="PD-extra-option-line-1-4">
                <label for="PD-extra-option-line-1-4">Lạp xưởng</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button"> -</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox" name="MonAnKem02"
                       class="PD-extra-option-check" id="PD-extra-option-line-1-5">
                <label for="PD-extra-option-line-1-5">Nem nướng</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
        </div>
        <div class="PD-extra-option">
            <div class="PD-extra-option-title"><strong>Các món canh theo phần</strong></div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox" name="MonCanhPhan"
                       class="PD-extra-option-check" id="PD-extra-option-line-2-1">
                <label for="PD-extra-option-line-2-1">Canh khổ qua</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox" name="MonCanhPhan"
                       class="PD-extra-option-check" id="PD-extra-option-line-2-2">
                <label for="PD-extra-option-line-2-2">Canh chua cá</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox" name="MonCanhPhan"
                       class="PD-extra-option-check" id="PD-extra-option-line-2-3">
                <label for="PD-extra-option-line-2-3">Canh hầm rau củ</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
        </div>
        <div class="PD-extra-option">
            <div class="PD-extra-option-title"><strong>Giải khát</strong></div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox"  name="GiaiKhat"
                       class="PD-extra-option-check" id="PD-extra-option-line-3-2">
                <label for="PD-extra-option-line-3-2">Sữa gạo rang</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox"  name="GiaiKhat"
                       class="PD-extra-option-check" id="PD-extra-option-line-3-3">
                <label for="PD-extra-option-line-3-3">Nước suối dasani</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
            <div class="PD-extra-option-AnOption">
                <input type="checkbox"  name="GiaiKhat"
                       class="PD-extra-option-check" id="PD-extra-option-line-3-4">
                <label for="PD-extra-option-line-3-4">Trà đá</label>
                <div class="PD-extra-option-quantity">
                    <button class="PD-extra-option-quantity-dec-button">-</button>
                    <div class="PD-extra-option-quantity">1</div>
                    <button class="PD-extra-option-quantity-inc-button">+</button>
                </div>
                <span class="PD-extra-option-price">10,000</span>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
