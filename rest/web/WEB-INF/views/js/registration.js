var RestPostAddNewUser = function (login, email, name, lastName, password) {

    JSONObject = {
        "login": login,
        "email": email,
        "firstName": name,
        "lastName": lastName,
        "password": password
    };

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/registration/add",
        contentType: "application/json;utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        async: false,
        success: function (result) {
            if (result > 0) {
                $('#response').html("User registrated with id = " + result)
            } else {
                $('#response').html("Wrong data for registration: " + GetReason(result))
            }
        },
        error: function (jqXHR, testStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR))
        }
    });

    GenericCaptcha();
};

var GetReason = function(cod){
    switch (cod){
        case -1 : return "exists user with specific login";
        case -2 : return "exists user with specific e-mail";
        case -3 : return "not valid e-mail";
        case -4 : return "no info about fist name";
        case -5 : return "no info about last name";
        default : "reason unknown";
    }
};

var ValidPassword = function (login, email, name, lastName, password, password2) {
    if (password != password2) {
        alert("passwords not equals. Fix and try again");
        return false;
    }
    if (password.length < 5) {
        alert("password's length must be more 5. Fix and try again");
        return false;
    }
    return true;
};

var Registration = function (login, email, name, lastName, password, password2) {
    if (!CheckCaptcha($('#captchaResult').val())) {
        $('#response').html("Captcha wrong!");
        GenericCaptcha();
        return;
    }
    if (ValidPassword(login, email, name, lastName, password, password2)) {
        RestPostAddNewUser(login, email, name, lastName, password);
    }
};

var GenericCaptcha = function () {
    document.getElementById("captcha").value = Math.floor(Math.random() * 100000);
};

CheckCaptcha = function (usersValue) {
    return usersValue == document.getElementById("captcha").value;
};
