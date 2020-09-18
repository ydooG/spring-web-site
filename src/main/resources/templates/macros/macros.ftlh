<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<#macro head title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>${title}</title>
</#macro>

<#macro header>
    <div class="header-wrapper">
        <div class="header_pos d-flex flex-column flex-md-row fixed-top align-items-center p-2 px-md-3 mb-2 bg-white border-bottom shadow-sm">
            <a class="my-0 mr-md-auto navbar-brand text-dark" href="/">Главная</a>
            <nav class="my-2 my-md-0 mr-md-3">
                <a href="/cart" class="btn btn-outline-success mr-2">
                    <i class="fas fa-shopping-cart"></i>
                    Корзина
                </a>
                <@security.authorize access="isAuthenticated()">
                    <@security.authorize access="hasAuthority('ADMIN')">
                        <a href="/addProduct" class="btn btn-outline-primary mr-2">
                            Добавить товар
                        </a>
                    </@security.authorize>

                    <a href="/profile" class="btn btn-outline-primary mr-2">
                        <i class="fas fa-user"></i>
                        Профиль
                    </a>
                </@security.authorize>

                <@security.authorize access="!isAuthenticated()">
                    <a href="/signIn" class="btn btn-outline-primary mr-2">Вход</a>
                    <a href="/signUp" class="btn btn-outline-success">Регистрация</a>
                </@security.authorize>
            </nav>
        </div>
    </div>
</#macro>

<#macro skeleton title>
    <!doctype html>
    <html lang="ru">
    <head>
        <@head title/>
    </head>
    <body>
        <header>
            <@header/>
        </header>
        <div class="page-wrapper">
            <div class="container">
                <#nested>
            </div>
        </div>
    </body>
    </html>
</#macro>

<#macro csrf_form action>
    <form action="${action}" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <#nested>
    </form>
</#macro>