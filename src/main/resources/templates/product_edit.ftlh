<#import "macros/macros.ftlh" as macros>
<#import "/spring.ftl" as spring />

<@macros.skeleton title="Редактирование">
    <h1>Редактирование</h1>
    <hr>
    <@spring.bind path="form"/>
    <div class="row">
        <div class="col"></div>
        <div class="col-6">
            <@macros.csrf_form action="">
                <div class="form-group">
                    <label for="name">Название</label>
                    <@spring.formInput path="form.name" fieldType="text" attributes="class='form-control' id='name'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="price">Цена в рублях</label>
                    <@spring.formInput path="form.price" fieldType="number" attributes="class='form-control' id='price'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description">Описание товара</label>
                    <@spring.formTextarea path="form.description" attributes="rows='5' class='form-control' id='description'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="category">Категория товара</label>
                    <@spring.formSingleSelect path="form.category" options=categories attributes="class='form-control' id='category'"/>
                    <div class="text-danger">
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary" >Сохранить</button>
            </@macros.csrf_form>
        </div>
        <div class="col"></div>
    </div>
</@macros.skeleton>