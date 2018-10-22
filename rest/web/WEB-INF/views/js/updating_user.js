var idUser;
var login;

var GettingInfoAboutUser = function () {
    var service = 'http://localhost:8080/user/rest/security/authentication/current';

    $.ajax({
        type: 'GET',
        url: service,
        dataType: 'json',
        async: false,
        success: function (result) {
            idUser = result.id;
            login = result.login;
            $('#login').html("Update "+ login + "'s data:");
            document.getElementById("email").value = result.email;
            document.getElementById("name").value = result.firstName;
            document.getElementById("lastName").value = result.lastName;
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#infoAboutUser').html(JSON.stringify(jqXHR))
        }
    });
}

var UpdateUser = function (email, name, lastName) {
    JSONObject = {
        "id": idUser,
        "login": login,
        "email": email,
        "firstName": name,
        "lastName": lastName
    };

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/rest/check/for/update/info",
        contentType: "application/json;utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        async: false,
        success: function (result) {
            if (result != 0) {
                $('#response').html("Not correct data: " + GetReason(result));
            } else {
                //data is correct, update user
                $.ajax({
                    type: "PUT",
                    url: "http://localhost:8080/user/rest/update/info",
                    contentType: "application/json;utf-8",
                    data: JSON.stringify(JSONObject),
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        $('#response').html("")
                    },
                    error: function (jqXHR, testStatus, errorThrown) {
                        $('#response').html(JSON.stringify(jqXHR))
                    }
                });

            }
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
        }
    });
}

var OnChange = function() {
    $('#response').html("data were changing")
}

