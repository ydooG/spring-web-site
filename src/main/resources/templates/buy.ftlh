<#import "macros/macros.ftlh" as macros>

<@macros.skeleton title="Покупка">
    <h1>Покупка</h1>
    <hr>
    <div class="row">
        <div class="col">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Название</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Количество</th>
                </tr>
                </thead>
                <tbody>
                    <#list products as key, value>
                        <tr>
                            <td>${key.name}</td>
                            <td>${key.price}</td>
                            <td>${value}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col">
            Итоговая цена: ${sum}
        </div>
    </div>
</@macros.skeleton>