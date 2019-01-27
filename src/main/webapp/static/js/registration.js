let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
console.log(token);
console.log(header);
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});

$(document).ready(function () {

    $("#password").on("keyup", function () {
        console.log("Handler for .keyup() called for password key");

        let password = $(this).val();
        let payload = {password: password};

        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "/validation",
            data: JSON.stringify(payload),
        }).done(function (data) {
            $("#password-msg").html(data);
        }).fail(function (error) {
            alert('something went wrong')
        })
    });

});