<#import "macros/macros.ftlh" as macros>

<@macros.skeleton title="Корзина">
    <h1>Корзина</h1>
    <div class="row">
        <div class="col">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Название</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Количество</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <#if products??>
                    <#list products as key, value>
                        <tr>
                            <th scope="row">${key.name}</th>
                            <td>${key.price} ₽</td>
                            <td>
                                <@macros.csrf_form action="/cart/amount?id=${key.id}&action=plus">
                                    <button class="btn btn-sm" type="submit">
                                        <i class="fas fa-plus text-primary"></i>
                                    </button>
                                </@macros.csrf_form>
                                <span id="amount">${value}</span>
                                <@macros.csrf_form action="/cart/amount?id=${key.id}&action=minus">
                                    <button class="btn btn-sm" type="submit">
                                        <i class="fas fa-minus text-primary"></i>
                                    </button>
                                </@macros.csrf_form>
                            </td>

                            <td>
                                <@macros.csrf_form action="/cart/remove?id=${key.id}">
                                    <button type="submit" class="btn btn-outline-danger">Убрать</button>
                                </@macros.csrf_form>
                            </td>
                        </tr>
                    </#list>
                </#if>

                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <p>Итого: ${sum} ₽</p>
    </div>
    <div class="row">
        <div class="col4">
            <a href="/buy" class="btn btn-success">Купить</a>
        </div>
    </div>
    <script>
        function handlePlus() {
            let amount = document.getElementById("amount");
            let number = Number.parseInt(amount.innerText);
            amount.innerText = Number(number + 1).toString();

            let sumObject = document.getElementById("sum");
            let sum = sumObject.innerText
        }

        function handleMinus() {
            let amount = document.getElementById("amount");
            let number = Number.parseInt(amount.innerText);
            if (number > 1) {
                amount.innerText = Number(number - 1).toString();
            }
        }

        function calculateSum() {

        }
    </script>
</@macros.skeleton>