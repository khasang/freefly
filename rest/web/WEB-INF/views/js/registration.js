var GetReason = function (cod) {
    switch (cod) {
        case 1 :
            return "exists user with specific login";
        case 2 :
            return "exists user with specific e-mail";
        case 3 :
            return "not valid e-mail";
        case 4 :
            return "no info about fist name";
        case 5 :
            return "no info about last name";
        case 6:
            return "empty login";
        default :
            "reason unknown";
    }
};

var ValidPassword = function (password, password2) {
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

var AddNewUser = function (login, email, name, lastName, password) {
    JSONObject = {
        "login": login,
        "email": email,
        "firstName": name,
        "lastName": lastName,
        "password": password
    };

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/check",
        contentType: "application/json;utf-8",
        data: JSON.stringify(JSONObject),
        dataType: "json",
        async: false,
        success: function (result) {
            if (result != 0) {
                $('#response').html("Not correct data: " + GetReason(result));
            } else {
                //data is correct, add user
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/user/add/nolocked",
                    contentType: "application/json;utf-8",
                    data: JSON.stringify(JSONObject),
                    dataType: "json",
                    async: false,
                    success: function (result) {
                        $('#response').html("User '"+ login+ "' registrated.")
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

    GenericCaptcha();
}

var Registration = function (login, email, name, lastName, password) {
    if (!CheckCaptcha($('#captchaResult').val())) {
        $('#response').html("Captcha wrong!");
        GenericCaptcha();
        return;
    }

    if (!ValidPassword($('#password').val(), $('#password2').val())) {
        return;
    }

    AddNewUser(login, email, name, lastName, password);
};

var GenericCaptcha = function () {
    document.getElementById("captcha").value = Math.floor(Math.random() * 100000);
};

CheckCaptcha = function (usersValue) {
    return usersValue == document.getElementById("captcha").value;
};
