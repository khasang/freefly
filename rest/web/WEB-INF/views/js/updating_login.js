var id;

var AuthorizedLogin = function() {
    var service = "http://localhost:8080/user/rest/security/authentication/current";

    $.ajax({
        type: 'GET',
        url: service,
        dataType: 'json',
        async: false,
        success: function (result) {
            $('#login').html(result.login),
            id = result.id
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
        }
    });
}

var UpdateLogin = function(login) {

    JSONObject = {
        "id": id,
        "login": login
    }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/rest/check/unique/login/" + login,
        async: false,
        success: function(result){
            if (result){

                $.ajax({
                    type: "PUT",
                    url: "http://localhost:8080/user/rest/update/login",
                    data: JSON.stringify(JSONObject),
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success : function (result) {
                        $('#response').html("Login was changed")
                        alert("Login was changed to '"+ login +"'. You must log in using new login.")
                        location.href = "http://localhost:8080/logout"
                    },
                    error: function (jqXHR, testStatus, errorThrown) {
                        $('#response').html(JSON.stringify(jqXHR))
                    }
                });

            } else {
                    $('#response').html("User '" + login + "' already exists. Try another login");
            }
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
        }
    })
}