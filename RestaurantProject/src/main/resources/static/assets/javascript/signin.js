$(document).ready(function(){
    $("#btn-signin").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/login/signin",
            data: {
                username: username,
                password: password
            }
        })
        .done(function( msg ) {
            if(msg.data){
                window.location.href='../Homepage/homepage.html';
            }else{
                alert("Fail!");
            }
        });
    })
})