
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>Title</title>
        <meta charset="UTF-8">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/ajax.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
    </head>
</head>
<body>
<div id="content">
    <div id = "menu" class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Поиск билета</h3>
        </div>
        <div class="panel-body">
            <div class="section_main">
                <div class="section">
                    <label>Откуда</label>
                </div>
                <div class="section">
                    <input class="select_test">
                </div>
            </div>
            <div class="section_main">
                <div class="section">
                    <label>Куда</label>
                </div>
                <div class="section">
                    <input class="select_test">
                </div>
            </div>
            <div class="section_main">
                <div class="section">
                    <label>Дата вылета</label>
                </div>
                <div class="section">
                    <input type="date" class="select_test">
                </div>
            </div>
            <div class="section_main">
                <div class="section">
                    <label>Количество человек</label>
                </div>
                <div class="section">
                    <select class="select_test">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
            </div>
            <div class="section_main">
                <div class="section">
                    <label></label>
                </div>
                <div class="section">
                    <button>Поиск</button>
                </div>
            </div>
        </div>
        <div class="panel-heading"> Результаты поиска </div>
        <table class="table">
            <thead>
            <tr>
                <th>Дата</th>
                <th>Откуда</th>
                <th>Куда</th>
                <th>Цена билета</th>
                <th>Время перелета</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>

