<#import "macros/macros.ftlh" as macros>
<#import "/spring.ftl" as spring>

<@macros.skeleton title="Регистрация">
    <h1>Registration</h1>
<#--    ToDo: showErrors-->
    <hr>
    <div class="row">
        <div class="col"></div>
        <div class="col-6">
            <@spring.bind path="form"/>
            <@macros.csrf_form action="/signUp">

                <div class="form-group">
                    <label for="name">Имя</label>
                    <@spring.formInput path="form.name" fieldType="text" attributes="class='form-control' id='name'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="surname">Фамилия</label>
                    <@spring.formInput path="form.surname" fieldType="text" attributes="class='form-control' id='surname'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email">Электронная почта</label>
                    <@spring.formInput path="form.email" fieldType="email" attributes="class='form-control' id='email'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="pass">Пароль</label>
                    <@spring.formInput path="form.password" fieldType="password" attributes="class='form-control' id='pass'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </@macros.csrf_form>
        </div>
        <div class="col"></div>
    </div>
</@macros.skeleton>