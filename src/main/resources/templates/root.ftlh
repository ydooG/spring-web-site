<#import "macros/macros.ftlh" as macros>

<@macros.skeleton title="All Market">
    <h1>ТОП 5 недавно измененных товаров</h1>
    <hr>

    <#list products as product>
        <div class="product border rounded border-success mb-3">
            <div class="row">
                <div class="col">
                    <h4><a href="/product/${product.id}">${product.name}</a> - ${product.price} ₽</h4>
                    <span>${product.description}</span>
                </div>
            </div>
        </div>
    </#list>
    <a href="/products" class="btn btn-primary">Смотреть все товары</a>
</@macros.skeleton>