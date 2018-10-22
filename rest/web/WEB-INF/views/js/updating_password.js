var AuthorizedLogin = function() {
    var service = "http://localhost:8080/user/rest/security/authentication/current";

    $.ajax({
        type: 'GET',
        url: service,
        dataType: 'json',
        async: false,
        success: function (result) {
            $('#login').html(result.login)
            document.getElementById("login").value = result.login
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
        }
    });
}

var UpdatePassword = function (login, password, password2) {

    if (!ValidPassword(password, password2)){
       return;
    }

    JSONObject = {
        "login": login,
        "password": password
    };

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/rest/users/change/password",
        contentType: "application/json;utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        async: false,
        success: function (result) {
            if (result) {
                $('#response').html("for " + login + " password changed");
            } else {
                $('#response').html("something is wrong... try check existence specific user");
            }
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
        }
    });
}

var UpdatePasswordByUser = function(login, oldPassword, newPassword, elseNewPassword) {
    if (!ValidPassword(newPassword, elseNewPassword)){
        return;
    }

    JSONObject = {
        "login": login,
        "password": oldPassword
    };

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/rest/check/password",
        contentType: "application/json;utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        async: false,
        success: function (result) {
            if (!result) {
                $('#response').html("password is not correct");
            } else {

                $.ajax({
                    type: "PUT",
                    url: "http://localhost:8080/user/rest/update/password",
                    contentType: "application/json;utf-8",
                    data: newPassword,
                    dataType: "json",
                    async: false,
                    success: function (result) {
                            $('#response').html("for " + result.login + " password changed");
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
