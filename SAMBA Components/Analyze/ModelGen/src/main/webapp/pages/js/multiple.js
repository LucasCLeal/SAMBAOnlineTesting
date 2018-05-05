/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    //solicitar path para diretório de origem dos arquivos BPEL a serem convertidos

    $.get("http://localhost:8080/ModelGen/resources/getOriginPath", function (result) {
        var path = $("#origin");
        path.text(result.toString());
    });
});


$(document).ready(function () {
    //solicitar path para diretório de destino dos modelos gerados

    $.get("http://localhost:8080/ModelGen/resources/getTargetPath", function (result) {
        var path = $("#target");
        path.text(result.toString());
    });
});

$(document).ready(function () {
    //Executando criação do modelo e retornando resultado do processo
    $("button").click(function () {
        $.get("http://localhost:8080/ModelGen/resources/genMultipleModels", function (result) {
            $("#output").text(result.toString());
        });
    });

});