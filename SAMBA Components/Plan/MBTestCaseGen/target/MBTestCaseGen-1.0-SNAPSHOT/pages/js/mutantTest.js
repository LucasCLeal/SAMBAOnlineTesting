/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("button").click(function () {
        
        config = $("#dirlist").val().toString() + "/" + $("#testRep").val().toString();
        
        $.post("http://localhost:8080/MBTestCaseGen/resources/multipleMT",config,function(data) {
            $("#txtArea").text(data);
        });
    });

});


$(document).ready(function () {
    //solicitar lista de modelos ao webservice
    $.getJSON("http://localhost:8080/MBTestCaseGen/resources/getfolderlist", function (result) {

        var mdlist = $("#dirlist");
        $.each(result.model, function (i, item) {
            mdlist.append($('<option>', {value: item, text: item}));
        });

    });
});

$("textarea").keyup(function(e) {
    while($(this).outerHeight() < this.scrollHeight + parseFloat($(this).css("borderTopWidth")) + parseFloat($(this).css("borderBottomWidth"))) {
        $(this).height($(this).height()+1);
    };
});
