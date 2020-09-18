<#import "macros/macros.ftlh" as macros>

<@macros.skeleton title="Профиль">
    <h1>Профиль</h1>
    <hr>
    <form>
        <div class="form-group row">
            <label for="name" class="col-3 col-form-label">Имя</label>
            <div class="col-5">
                <input type="text" readonly class="form-control-plaintext" id="name" value="${currentUser.name}">
            </div>
        </div>

        <div class="form-group row">
            <label for="surname" class="col-3 col-form-label">Фамилия</label>
            <div class="col-5">
                <input type="text" readonly class="form-control-plaintext" id="surname" value="${currentUser.surname}">
            </div>
        </div>

        <div class="form-group row">
            <label for="email" class="col-3 col-form-label">Электронная почта</label>
            <div class="col-5">
                <input type="text" readonly class="form-control-plaintext" id="email" value="${currentUser.email}">
            </div>
        </div>
    </form>

    <@macros.csrf_form action="/exit">
        <button type="submit" class="btn btn-danger">Выйти из аккаунта</button>
    </@macros.csrf_form>
</@macros.skeleton>