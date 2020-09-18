<#import "macros/macros.ftlh" as macros>
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@macros.skeleton title="${product.name}">
    <h1>Товар</h1>
    <hr>
    <form>
        <div class="form-group row">
            <label for="category" class="col-3 col-form-label">Категория</label>
            <div class="col-5">
                <input type="text" readonly class="form-control-plaintext" id="category" value="${product.category}">
            </div>
        </div>

        <div class="form-group row">
            <label for="name" class="col-3 col-form-label">Название</label>
            <div class="col-5">
                <input type="text" readonly class="form-control-plaintext" id="name" value="${product.name}">
            </div>
        </div>

        <div class="form-group row">
            <label for="price" class="col-3 col-form-label">Цена</label>
            <div class="col-5">
                <input type="text" readonly class="form-control-plaintext" id="price" value="${product.price}">
            </div>
        </div>

        <div class="form-group row">
            <label for="last" class="col-3 col-form-label">Последнее Изменение</label>
            <div class="col-5">
                <input type="datetime-local" readonly class="form-control-plaintext" id="last" value="${product.lastModified}">
            </div>
        </div>

        <div class="form-group row">
            <label for="description" class="col-3 col-form-label">Описание товара</label>
            <div class="col-5">
                <span>${product.description}</span>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col">
            <@macros.csrf_form action="/cart/add?id=${product.id}">
                <button class="btn btn-primary" type="submit" id="add"><i class="fas fa-cart-plus"></i>
                    Добавить в корзину
                </button>
            </@macros.csrf_form>
        </div>
        <div class="col">
            <@security.authorize access="hasAuthority('ADMIN')">
                <button onclick="location.href='/product/edit/${product.id}'" class="btn btn-warning">
                    Редактировать данные
                </button>
            </@security.authorize>
        </div>
        <div class="col">
            <@security.authorize access="hasAuthority('ADMIN')">
                <@macros.csrf_form action="/product/remove/${product.id}">
                    <button onclick="location.href='/product/remove/${product.id}'" class="btn btn-danger">

                        Удалить Товар
                    </button>
                </@macros.csrf_form>
            </@security.authorize>
        </div>
    </div>
    <script>
        function manageCookie() {
            let cookieName = "cart-items";
            let items = getCookie(cookieName);
            let stringCookie = "";
            console.log("items:", items);
            let found = false;
            let arr = items.split("-");
            for (let i = 0; i < arr.length - 1; i++) {
                let id = arr[i].split(":")[0];
                let count = arr[i].split(":")[1];
                if (id === "${product.id}") {
                    found = true;
                    arr[i] = id + ":" + (Number.parseInt(count) + 1).toString()
                }
            }
            if (found) {
                for (let i = 0; i < arr.length - 1; i++) {
                    stringCookie += arr[i] + "-"
                }
            } else {
                stringCookie = items + "${product.id}:1-"
            }
            setCookie(cookieName, stringCookie, 7);
            changeButtonText()
        }

        function changeButtonText() {
            let button = document.getElementById("add");
            button.innerText = "Добавлено!";
            button.classList.remove("btn-primary");
            button.classList.add("btn-success");
        }

        function setCookie(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toUTCString();
            document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
        }

        function getCookie(cname) {
            var name = cname + "=";
            var decodedCookie = decodeURIComponent(document.cookie);
            var ca = decodedCookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') {
                    c = c.substring(1);
                }
                if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        }
    </script>
</@macros.skeleton>