<#import "macros/macros.ftlh" as macros>

<@macros.skeleton title="Вход">
    <h1>Вход</h1>
    <hr>
    <div class="row">
        <div class="col"></div>
        <div class="col-6">
            <@macros.csrf_form action="/signIn">
                <div class="form-group">
                    <label for="email">Электронная почта</label>
                    <input type="email" name="email" class="form-control" id="email">
                </div>

                <div class="form-group">
                    <label for="pass">Пароль</label>
                    <input type="password" name="password" class="form-control" id="pass">
                </div>
                <#if error??>
                    <div class="text-danger">
                        Неверные данные
                    </div>
                </#if>
                <button type="submit" class="btn btn-primary">Войти</button>
            </@macros.csrf_form>
        </div>
        <div class="col"></div>
    </div>
</@macros.skeleton>