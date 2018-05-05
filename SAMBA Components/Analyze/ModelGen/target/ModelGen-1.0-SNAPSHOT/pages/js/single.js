/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    //solicitar lista de Arquivos BPEL ao webservice

    $.getJSON("http://localhost:8080/ModelGen/resources/getBPELList", function (result) {

        var bplist = $("#BPELList");
        $.each(result.file, function (i, item) {
            bplist.append($('<option>', {value: item, text: item}));
        });

    });
});


$(document).ready(function () {
    //Executando criação do modelo e retornando resultado do processo
    $("button").click(function () {
        $.post("http://localhost:8080/ModelGen/resources/genSingleModel",$("#BPELList").val(), function (data) {
            $("#txtArea").text(data);
        });
    });

});

$(document).ready(function () {
    //solicitar path para diretório de origem dos arquivos BPEL a serem convertidos


    $.get("http://localhost:8080/ModelGen/resources/getOriginPath", function (result) {
        $("#test1").text(result.toString());
    }); 
    
});


$(document).ready(function () {
    //solicitar path para diretório de destino dos modelos gerados

    $.get("http://localhost:8080/ModelGen/resources/getTargetPath", function (result) {
        $("#test2").text(result);
    });
});